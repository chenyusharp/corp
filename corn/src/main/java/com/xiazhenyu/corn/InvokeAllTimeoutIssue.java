package com.xiazhenyu.corn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Date: 2025/6/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class InvokeAllTimeoutIssue {


    // 模拟一个会吞掉中断并且长时间运行的 Callable
    static class ProblematicCallable implements Callable<String> {

        private final int id;
        private final long durationMillis;
        private final boolean swallowInterrupt;

        public ProblematicCallable(int id, long durationMillis, boolean swallowInterrupt) {
            this.id = id;
            this.durationMillis = durationMillis;
            this.swallowInterrupt = swallowInterrupt;
        }

        @Override
        public String call() throws Exception { // 声明抛出Exception，但内部可能不抛InterruptedException
            System.out.println("Task " + id + " started on thread " + Thread.currentThread().getName());
            long startTime = System.currentTimeMillis();
            try {
                for (long i = 0; i < durationMillis / 100; i++) { // 模拟耗时
                    if (Thread.currentThread().isInterrupted()) {
                        // 如果不希望被中断，可以忽略，但这不是好做法
                        // System.out.println("Task " + id + " was interrupted but continues...");
                        // 如果要响应中断，应该抛出InterruptedException或清理后退出
                        if (!swallowInterrupt) {
                            System.out.println("Task " + id + " detected interruption, throwing InterruptedException.");
                            throw new InterruptedException("Task " + id + " interrupted.");
                        } else {
                            System.out.println("Task " + id + " detected interruption, but swallowing it.");
                            // 清除中断状态（通常不推荐，除非明确知道自己在做什么）
                            // Thread.interrupted(); // This clears the interrupt status
                        }
                    }
                    // 模拟一些工作
                    if (i % 100 == 0) {
                        Thread.sleep(1); // sleep会响应中断
                    }
                }
                // 模拟一个不会抛出 InterruptedException 的耗时操作
                long count = 0;
                while (System.currentTimeMillis() - startTime < durationMillis) {
                    count++; // 忙等待
                    if (swallowInterrupt && Thread.currentThread().isInterrupted()) {
                        System.out.println("Task " + id + " (busy loop) detected interruption, but swallowing it.");
                        // 不退出循环，继续执行
                    } else if (!swallowInterrupt && Thread.currentThread().isInterrupted()) {
                        System.out.println("Task " + id + " (busy loop) detected interruption, throwing InterruptedException.");
                        throw new InterruptedException("Task " + id + " (busy loop) interrupted.");
                    }
                }

                System.out.println("Task " + id + " completed normally after " + (System.currentTimeMillis() - startTime) + " ms.");
                return "Result from task " + id;
            } catch (InterruptedException e) {
                if (swallowInterrupt) {
                    System.err.println("Task " + id + " caught InterruptedException and swallowed it. Thread interrupted status: " + Thread.currentThread().isInterrupted());
                    // 不再抛出，invokeAll会认为任务正常结束（可能返回null或特定值）
                    // 为了让invokeAll认为任务未完成而等待，这里不返回，继续循环或阻塞，模拟更坏情况
                    // (但实际中InterruptedException通常意味着线程应停止)
                    // 返回一个错误标记
                    return "Task " + id + " was interrupted but handled by returning a special value.";
                } else {
                    System.err.println("Task " + id + " caught InterruptedException and re-throwing it. Thread interrupted status: " + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt(); // 保持中断状态
                    throw e; // 重新抛出，Future会感知到
                }
            } catch (Throwable t) { // 捕获所有异常
                System.err.println("Task " + id + " caught Throwable: " + t.getMessage());
                // 如果不重新抛出，invokeAll也可能认为任务“正常”结束（返回null）
                return "Task " + id + " failed with other error.";
            }
        }
    }


    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3, r -> {
            Thread t = new Thread(r);
            t.setName("Worker-" + t.getId());
            return t;
        });
        Collection<Callable<String>> callables = new ArrayList<>();
        // Task 1: 正常完成
        callables.add(new ProblematicCallable(1, 1000, false));
        // Task 2: 耗时很长，会吞掉中断
        callables.add(new ProblematicCallable(2, 10000, true));
        // Task 3: 耗时中等，会正确响应中断
        callables.add(new ProblematicCallable(3, 5000, false));

        System.out.println("Submitting tasks with timeout 3 seconds...");
        long overallStartTime = System.currentTimeMillis();
        List<Future<String>> futures = null;

        try {
            // 设置超时为3秒
            futures = executor.invokeAll(callables, 3, TimeUnit.SECONDS);
            long duration = System.currentTimeMillis() - overallStartTime;
            System.out.println("invokeAll returned after " + duration + " ms.");

            for (Future<String> future : futures) {
                try {
                    if (future.isCancelled()) {
                        System.out.println("Future is cancelled.");
                    } else if (future.isDone()) {
                        System.out.println("Future is done. Result: " + future.get(1, TimeUnit.MILLISECONDS)); // 短暂get，避免阻塞
                    }
                } catch (CancellationException e) {
                    System.err.println("Future was cancelled: " + e.getMessage());
                } catch (ExecutionException e) {
                    System.err.println("Future completed with exception: " + e.getCause());
                } catch (InterruptedException e) {
                    System.err.println("Getting future result was interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                } catch (TimeoutException e) {
                    System.err.println("Timeout getting future result: " + e.getMessage());
                }
            }

        } catch (InterruptedException e) {
            System.err.println("invokeAll was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        } finally {
            System.out.println("Shutting down executor...");
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate in time, forcing shutdownNow...");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
            System.out.println("Executor shutdown complete.");
            long totalDuration = System.currentTimeMillis() - overallStartTime;
            System.out.println("Total execution time: " + totalDuration + " ms.");
        }
    }


}