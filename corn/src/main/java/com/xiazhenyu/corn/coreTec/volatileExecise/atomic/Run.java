package com.xiazhenyu.corn.coreTec.volatileExecise.atomic;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) {
//        runForMyThread();
        runForMyThread1();
    }


    public static void runForMyThread() {
        for (int i = 0; i < 100; i++) {
            MyThread myThread = new MyThread();
            myThread.start();
        }
    }


    public static void runForMyThread1() {
        for (int i = 0; i < 100; i++) {
            MyThread1 myThread = new MyThread1();
            myThread.start();
        }
    }


}