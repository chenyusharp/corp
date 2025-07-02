package com.xiazhenyu.corn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
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
public class ThreadPoolMonitor {

    public static void scheduleMonitoring(ThreadPoolExecutor executor, String poolName, long period, TimeUnit unit) {
        ScheduledExecutorService monitorScheduler = Executors.newSingleThreadScheduledExecutor(
                ThreadFactoryBuilder.create().setNameFormat("pool-monitor-" + poolName).get());
//                newNamedThreadFactory("pool-monitor-" + poolName, true) // Daemon thread for monitor

        monitorScheduler.scheduleAtFixedRate(() -> {
            if (executor.isShutdown() || executor.isTerminated()) {
                System.out.println(String.format("Pool [%s] is shutdown/terminated. Stopping monitor.", poolName));
                monitorScheduler.shutdown(); // Stop monitoring if target pool is down
                return;
            }

            System.out.println(String.format(
                    "Pool [%s] Stats: \n" +
                            "  CoreSize: %d, MaxSize: %d, CurrentSize: %d, ActiveThreads: %d, LargestSize: %d\n" +
                            "  QueueSize: %d, QueueRemainingCapacity: %d\n" +
                            "  TaskCount: %d, CompletedTasks: %d\n" +
                            "  IsShutdown: %s, IsTerminating: %s, IsTerminated: %s",
                    poolName,
                    executor.getCorePoolSize(),
                    executor.getMaximumPoolSize(),
                    executor.getPoolSize(),
                    executor.getActiveCount(),
                    executor.getLargestPoolSize(),
                    executor.getQueue().size(),
                    executor.getQueue().remainingCapacity(), // careful if queue is unbounded
                    executor.getTaskCount(),
                    executor.getCompletedTaskCount(),
                    executor.isShutdown(),
                    executor.isTerminating(),
                    executor.isTerminated()
            ));
        }, 0, period, unit);
    }


    public static void main(String[] args) {
        ThreadPoolExecutor myCustomPool=new ThreadPoolExecutor(
                2, 4, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), // Bounded queue
                ThreadFactoryBuilder.create().setNameFormat("my-worker-pool").get()
        );

        scheduleMonitoring(myCustomPool, "MyWorkerPool", 5, TimeUnit.SECONDS);

        // Submit some tasks to see monitoring in action
        for (int i=0; i < 15; i++) {
            final int taskId= i;
            myCustomPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
                try {
                    Thread.sleep(2000 + ThreadLocalRandom.current().nextInt(3000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            try {
                Thread.sleep(500); // Stagger submissions
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Thread.sleep(30000); // Let it run for a while
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GracefulShutdownExample.shutdownExecutorService(myCustomPool, "MyWorkerPool", 10);
        System.out.println("Main thread finished monitoring example.");
    }

}