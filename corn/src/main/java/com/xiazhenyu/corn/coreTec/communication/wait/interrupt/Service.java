package com.xiazhenyu.corn.coreTec.communication.wait.interrupt;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Service {


    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait()");
                lock.wait();
                System.out.println("end wait()");
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("counter exception, wait status  thread was interrupted");
        }


    }

}