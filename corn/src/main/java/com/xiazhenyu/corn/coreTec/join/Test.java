package com.xiazhenyu.corn.coreTec.join;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {

    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            myThread.join();
            System.out.println("myThread is done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}