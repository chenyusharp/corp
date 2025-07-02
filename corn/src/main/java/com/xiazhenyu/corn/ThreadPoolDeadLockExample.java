package com.xiazhenyu.corn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import jodd.util.concurrent.ThreadFactoryBuilder;

/**
 * Date: 2025/6/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadPoolDeadLockExample {


    public static void main(String[] args) {

        // 创建一个单线程的线程池，更容易复现死锁
        // ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(
        // new NamedThreadFactory("deadlock-pool-single"));

        // 或者一个容量较小的固定大小线程池
        int poolSize = 2; // 尝试用1, 2, 3等不同大小测试
        ExecutorService smallFixedPool = new ThreadPoolExecutor(
                poolSize, poolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(poolSize * 1), // 队列大小也可能影响
                ThreadFactoryBuilder.create().setNameFormat("deadlock-pool-fixed-" + poolSize).get());

        ExecutorService executorToTest = smallFixedPool; // 选择要测试的池

        Callable<String> taskB = () -> {
            System.out.println(Thread.currentThread().getName() + ": Task B starting...");
            Thread.sleep(1000); // Simulate work
            System.out.println(Thread.currentThread().getName() + ": Task B finished.");
            return "Result from B";
        };

        Callable<String> taskA = () -> {
            System.out.println(Thread.currentThread().getName() + ": Task A starting...");
            System.out.println(Thread.currentThread().getName() + ": Task A submitting Task B and waiting for its result...");

            Future<String> futureB = executorToTest.submit(taskB); // Task B提交到同一个池

            try {
                String resultB = futureB.get(); // Task A阻塞等待Task B
                System.out.println(Thread.currentThread().getName() + ": Task A received result from B: " + resultB);
            } catch (Exception e) {
                System.err.println(Thread.currentThread().getName() + ": Task A failed to get result from B: " + e);
                throw e;
            }
            System.out.println(Thread.currentThread().getName() + ": Task A finished.");
            return "Result from A (feat. " + "Result from B" + ")";
        };

        // 提交多个Task A类型任务，以占满线程池
        List<Future<String>> futuresA = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) { // 提交与池大小相同数量的Task A
            System.out.println("Submitting Task A instance " + (i + 1));
            futuresA.add(executorToTest.submit(taskA));
        }


        // 等待所有Task A完成 (如果死锁，这里会一直卡住)
        for (Future<String> future : futuresA) {
            try {
                // 设置一个超时，避免无限等待，便于演示
                System.out.println("Main thread trying to get result: " + future.get(10, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                System.err.println("Main thread: Future timed out! Deadlock likely occurred.");
                // 在发生超时后，打印线程dump会很有用
                // dumpThreads(); // 辅助方法，打印所有线程堆栈
                break;
            } catch (Exception e) {
                System.err.println("Main thread: Future failed: " + e);
            }
        }






    }


}