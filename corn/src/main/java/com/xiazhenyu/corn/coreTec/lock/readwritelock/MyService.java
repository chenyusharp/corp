package com.xiazhenyu.corn.coreTec.lock.readwritelock;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Date: 2022/1/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyService {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private String username = "xiazhenyu";


    public void testMethod1() {
        try {
            lock.readLock().lock();
            System.out.println("begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            System.out.println("print service " + username);
            Thread.sleep(5000);
            System.out.println(" end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}