package com.xiazhenyu.corn.coreTec.communication.wait.interrupt;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {


    public static void main(String[] args) {
        try {

            Object object = new Object();
            ThreadA threadA = new ThreadA(object);
            threadA.start();
            Thread.sleep(1000);
            threadA.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}