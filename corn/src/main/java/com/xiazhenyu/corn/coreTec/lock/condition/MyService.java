package com.xiazhenyu.corn.coreTec.lock.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyService {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void awaitMethod(long awaitTime) {
        lock.lock();
        try {
            System.out.println("before await,thread name=" + Thread.currentThread().getName());
            //await的作用：是当前的线程在接受到通知或被中断之前一直处于等待wait状态
            condition.await(awaitTime, TimeUnit.SECONDS);
            System.out.println("end await,thread name=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("unlock");
        }
    }


    public void signalMethod() {
        lock.lock();
        try {
            System.out.println("before signal,thread name=" + Thread.currentThread().getName());
            condition.signal();
            System.out.println("end signal,thread name=" + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }


    public void signalMethodAll() {
        lock.lock();
        try {
            System.out.println("before signal,thread name=" + Thread.currentThread().getName());
            condition.signalAll();
            System.out.println("end signal,thread name=" + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }


}