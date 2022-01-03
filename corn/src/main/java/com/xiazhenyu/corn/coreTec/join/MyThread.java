package com.xiazhenyu.corn.coreTec.join;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThread extends Thread {


    @Override
    public void run() {
        try {
            int secondValue = (int) (Math.random() * 100);
            System.out.println(secondValue);
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}