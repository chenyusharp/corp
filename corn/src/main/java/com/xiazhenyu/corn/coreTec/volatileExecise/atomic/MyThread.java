package com.xiazhenyu.corn.coreTec.volatileExecise.atomic;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThread extends Thread {

    volatile public static int count;


    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count=" + count);
    }


    @Override
    public void run() {
        addCount();
    }
}