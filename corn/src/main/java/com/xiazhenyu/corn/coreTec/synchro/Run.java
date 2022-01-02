package com.xiazhenyu.corn.coreTec.synchro;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) {
//        runForMyObject();
//        runForMyThread();
        runForMySubThread();
    }


    public static void runForMyObject() {
        MyObject myObject = new MyObject();
        ThreadA threadA = new ThreadA(myObject);
        threadA.setName("a");
        ThreadB threadB = new ThreadB(myObject);
        threadB.setName("b");
        threadA.start();
        threadB.start();
    }


    public static void runForMyThread() {
        MyThread myThread = new MyThread();
        myThread.start();
    }


    public static void runForMySubThread() {
        MySubThread mySubThread = new MySubThread();
        mySubThread.start();
    }

}