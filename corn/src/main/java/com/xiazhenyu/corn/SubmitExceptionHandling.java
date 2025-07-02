package com.xiazhenyu.corn;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Date: 2025/6/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SubmitExceptionHandling {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 场景1: Callable 抛出异常
        Future<String> future1 = executor.submit(() -> {
            System.out.println("Callable task executing...");
            Thread.sleep(100); // 模拟工作
            if (true) { // 强制抛出异常
                throw new RuntimeException("Exception from Callable!");
            }
            return "Callable Result";
        });

        try {
            System.out.println("Attempting to get result from future1...");
            String result1 = future1.get(); // 这里会抛出ExecutionException
            System.out.println("Result1: " + result1);
        } catch (InterruptedException e) {
            System.err.println("future1.get() interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.err.println("future1.get() threw ExecutionException. Cause: " + e.getCause());
        }

        // 场景2: Runnable 抛出异常 (Future<?> 类型)
        Future<?> future2 = executor.submit(() -> {
            System.out.println("Runnable task executing...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) { /* in a real app, handle properly */ }
            throw new IllegalArgumentException("Exception from Runnable!");
        });

        try {
            System.out.println("Attempting to get result from future2 (Runnable)...");
            future2.get(); // 对于Runnable的Future，get()成功时返回null。如果异常，则抛ExecutionException
            System.out.println("future2 completed successfully (no exception).");
        } catch (InterruptedException e) {
            System.err.println("future2.get() interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.err.println("future2.get() threw ExecutionException. Cause: " + e.getCause());
        }

        // 场景3: 提交任务但不调用get()，异常可能被线程默认处理器捕获（或被吞）
        executor.submit(() -> {
            System.out.println("Task 3 executing and will throw an exception (not calling get)...");
            throw new IllegalStateException("Invisible exception from Task 3!");
        });
        // 如果没有设置UncaughtExceptionHandler，这个异常可能只打印到stderr，业务无感知

        executor.shutdown();


    }

}