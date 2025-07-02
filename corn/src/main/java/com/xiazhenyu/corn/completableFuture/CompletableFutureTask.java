package com.xiazhenyu.corn.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import jodd.util.concurrent.ThreadFactoryBuilder;

/**
 * Date: 2025/6/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CompletableFutureTask {


    public static void main(String[] args) {

        // Executor for primary tasks (Task A type)
        int poolSize = 5, maxPoolSize = 10;
        ExecutorService primaryExecutor = new ThreadPoolExecutor(
                poolSize, poolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100), // 队列大小也可能影响
                ThreadFactoryBuilder.create().setNameFormat("primaryExecutor-pool-").get());
        ;
// Executor for secondary tasks (Task B type), can be different/larger
        ExecutorService secondaryExecutor = new ThreadPoolExecutor(
                poolSize, poolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100), // 队列大小也可能影响
                ThreadFactoryBuilder.create().setNameFormat("secondaryExecutor-pool-").get());

        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
                    // Task A's initial work
                    System.out.println(Thread.currentThread().getName() + ": Task A part 1 running");
                    return "Data from A1";
                }, primaryExecutor)
                .thenComposeAsync(dataFromA1 -> { // Task B (or equivalent)
                    return CompletableFuture.supplyAsync(() -> {
                        System.out.println(Thread.currentThread().getName() + ": Task B running with " + dataFromA1);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) { /* ... */ }
                        return "Result from B based on " + dataFromA1;
                    }, secondaryExecutor); // Crucially, Task B runs on secondaryExecutor
                }, primaryExecutor) // thenComposeAsync itself can also specify an executor for its composition logic
                .thenApplyAsync(resultFromB -> {
                    // Task A's final work using result from B
                    System.out.println(Thread.currentThread().getName() + ": Task A part 2 running with " + resultFromB);
                    return "Final A: " + resultFromB;
                }, primaryExecutor);

        try {
            futureA.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }

}