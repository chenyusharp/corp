package com.xiazhenyu.corn.lambda;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2021/8/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@SuppressWarnings("AlibabaThreadPoolCreation")
public class ThreadPoolTest {

    public static void main(String[] args) {
        /**int a=10;
         while (10==a){
         System.out.println("hello ");
         a++;
         a--;
         }
         a++;
         System.out.println("end ");**/
        //创建一个固定大小（核心线程数、最大线程数）的线程池，队列是LinkedBlockingQueue。缺点：容易造成OOM；
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        //核心线程数是0，最大线程数是Integer.MAX_VALUE，队列是SynchronousQueue的线程池。SynchronousQueue不进行线程的保存，直接进行转发。容易造成OOM；
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //核心线程是是1个、最大线程是1个，队列是LinkedBlockingQueue（无限大）的线程池，容易造成OOM；
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        //核心线程数固定大小的延迟执行的一个线程池，最大线程池大小是Integer.MAX_VALUE,队列是延迟队列：DelayedWorkQueue。缺点：容易造成OOM；
        Executors.newScheduledThreadPool(5);
        //自定义线程池,IO密集型和CPU密集型的区别
        int coreThreads = 10;
        int maxThreads = 100;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(coreThreads, maxThreads,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue(500));

    }

}