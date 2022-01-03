package com.xiazhenyu.corn.coreTec.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyService {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private boolean hasValue = false;


    public void set() {
        lock.lock();
        try {
            System.out.println("set method ,hasValue=" + hasValue + " thread name=" + Thread.currentThread().getName());
            if (hasValue) {
                condition.await();
            }
            System.out.println("★");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void get() {
        lock.lock();
        try {
            System.out.println("get method,hasValue=" + hasValue + " thread name=" + Thread.currentThread().getName());
            if (!hasValue) {
                condition.await();
            }
            System.out.println("☆");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


}