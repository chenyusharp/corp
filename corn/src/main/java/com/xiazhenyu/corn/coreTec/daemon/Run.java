package com.xiazhenyu.corn.coreTec.daemon;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.setDaemon(true);
        myThread.start();

        Thread.sleep(5000);

        System.out.println("daemon  thread is end");


    }

}