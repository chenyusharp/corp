package com.xiazhenyu.corn.coreTec.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 2022/6/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class InterruptiblyExample {


    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println("线程1：获取到锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    System.out.println("线程 2：尝试获取锁");
                    lock.lockInterruptibly();
                    System.out.println("线程 2：获取锁成功.");
                } catch (Exception e) {
                    System.out.println("线程 2:执行已被中断.");
                }
            }
        });
        t2.start();

        Thread.sleep(2000);

        if (t2.isAlive()) {
            System.out.println("执行线程中断");
            t2.interrupt();
            System.out.println("调用线程中断结束");
        } else {
            System.out.println("线程 2： 执行完成");
        }

    }
}