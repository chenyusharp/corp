package com.xiazhenyu.corn.coreTec.lock.reentrantlock;

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


    public void testMethod() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName() + " " + (i + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}