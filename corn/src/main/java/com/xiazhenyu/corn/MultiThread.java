package com.xiazhenyu.corn;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 2021/12/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MultiThread {


    public static void main(String[] args) throws InterruptedException {

        final ReentrantLock putLock = new ReentrantLock();

        int c = 100;
        putLock.lockInterruptibly();
        try {
            c += 100;
            System.out.println(c);
        } catch (Exception e) {

        } finally {
            putLock.unlock();
        }


    }


}