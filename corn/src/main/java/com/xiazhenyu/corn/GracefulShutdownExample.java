package com.xiazhenyu.corn;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2025/6/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class GracefulShutdownExample {


    public static void shutdownExecutorService(ExecutorService executor, String poolName, long awaitTimeoutSeconds) {
        System.out.println("Attempting to gracefully shutdown executor: " + poolName);
        executor.shutdown(); // Disable new tasks from being submitted

        try {
            // Wait a while for existing tasks to terminate
            if (!executor.awaitTermination(awaitTimeoutSeconds, TimeUnit.SECONDS)) {
                System.err.println(poolName + ": Timeout occurred while waiting for tasks to complete. Forcing shutdown...");
                List<Runnable> droppedTasks = executor.shutdownNow(); // Cancel currently executing tasks and drain queue
                System.out.println(poolName + ": Tasks dropped by shutdownNow(): " + droppedTasks.size());

                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(awaitTimeoutSeconds / 2, TimeUnit.SECONDS)) {
                    System.err.println(poolName + ": Executor did not terminate even after shutdownNow().");
                } else {
                    System.out.println(poolName + ": Executor terminated after shutdownNow().");
                }
            } else {
                System.out.println(poolName + ": Executor terminated gracefully.");
            }
        } catch (InterruptedException ie) {
            System.err.println(poolName + ": Shutdown interrupted. Forcing shutdownNow().");
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

        if (executor.isTerminated()) {
            System.out.println(poolName + " is fully terminated.");
        } else {
            System.err.println(poolName + " failed to terminate properly.");
        }
    }


}