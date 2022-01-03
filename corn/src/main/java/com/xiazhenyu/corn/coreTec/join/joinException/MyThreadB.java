package com.xiazhenyu.corn.coreTec.join.joinException;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThreadB extends Thread {

    @Override
    public void run() {
        try {
            MyThreadA myThreadA = new MyThreadA();
            myThreadA.start();
            myThreadA.join();
            System.out.println("thread b run end");
        } catch (InterruptedException e) {
            System.out.println("thread b counter exception");
            e.printStackTrace();
        }
    }
}