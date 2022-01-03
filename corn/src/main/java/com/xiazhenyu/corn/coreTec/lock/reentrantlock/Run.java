package com.xiazhenyu.corn.coreTec.lock.reentrantlock;

/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) {
        MyService myService = new MyService();
        for (int i = 0; i < 5; i++) {
            MyThread myThread = new MyThread(myService);
            myThread.start();
        }

    }

}