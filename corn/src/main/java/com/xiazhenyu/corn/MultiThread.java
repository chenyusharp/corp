package com.xiazhenyu.corn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Date: 2021/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MultiThread {



    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        AtomicBoolean a = new AtomicBoolean(false);
        AtomicBoolean b = new AtomicBoolean(false);
        Thread ta = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                a.set(true);
                lock.notifyAll();
            }
        }, "A");
        Thread tb = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                b.set(true);
                lock.notifyAll();
            }
        }, "B");
        ta.start();
        tb.start();
        synchronized (lock) {
            lock.wait();
        }
        if (a.get()) {
            System.out.println("A ok");
        }
        if (b.get()) {
            System.out.println("B ok");
        }
    }

}