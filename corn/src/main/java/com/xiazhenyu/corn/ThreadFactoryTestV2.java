package com.xiazhenyu.corn;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2025/6/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadFactoryTestV2 {

    static ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("my-pool-%d")
            .setUncaughtExceptionHandler((thread, throwable) -> {
                System.err.println("Uncaught exception in thread " + thread.getName() + ": " + throwable.getMessage());
                // 在这里可以进行告警、记录更详细的上下文信息等
            })
            .build(); // Guava's ThreadFactoryBuilder, or implement ThreadFactory manually


    static int corePoolSize = 3;
    static int maximumPoolSize = 5;
    static int keepAliveTime = 0;
    static TimeUnit unit = TimeUnit.SECONDS;
    static LinkedBlockingQueue workQueue = new LinkedBlockingQueue();

    static ExecutorService executor = new ThreadPoolExecutor(
            corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy()) {

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (t == null && r instanceof Future<?>) {
                try {
                    Future<?> future = (Future<?>) r;

                    if (future.isDone() && !future.isCancelled()) {
                        //获取执行结果
                        future.get();
                    }
                } catch (CancellationException ce) {
                    t = ce;
                } catch (ExecutionException ee) {
                    t = ee.getCause();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    t = ie;
                }
            }
            if (t != null) {
                System.err.println(t.getMessage());
            }
        }
    };


    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int i = 100;
                int j = 0;
                final int trade = i / j;
            }
        };

        Callable<String> cal = new Callable<String>() {
            @Override
            public String call() throws Exception {
                int i = 100;
                int j = 0;
                final int trade = i / j;
                return "计算完成";
            }
        };
//        executor.execute(r);
        executor.submit(cal);
        executor.shutdown();
    }

}