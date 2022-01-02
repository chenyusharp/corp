package com.xiazhenyu.corn.coreTec.communication.wait.notify;

import jdk.management.resource.internal.inst.ThreadRMHooks;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) {

        try {
            Object lock = new Object();

            Thread1 thread1 = new Thread1(lock);
            thread1.start();

            Thread.sleep(2000);
            Thread2 thread2 = new Thread2(lock);
            thread2.start();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}