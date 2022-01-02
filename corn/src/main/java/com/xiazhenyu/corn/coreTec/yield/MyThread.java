package com.xiazhenyu.corn.coreTec.yield;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThread extends Thread {


    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 50000000; i++) {

            count += (i + 1);
        }
        Thread.yield();
        long endTime = System.currentTimeMillis();
        System.out.println("use time:" + (endTime - beginTime) + "毫秒!");

    }
}