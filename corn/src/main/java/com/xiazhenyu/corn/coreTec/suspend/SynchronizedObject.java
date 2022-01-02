package com.xiazhenyu.corn.coreTec.suspend;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SynchronizedObject {


    synchronized public void printString() {
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("thread is suspend");
            Thread.currentThread().suspend();
        }
    }

}