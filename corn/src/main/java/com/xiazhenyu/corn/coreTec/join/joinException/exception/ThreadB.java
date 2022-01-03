
package com.xiazhenyu.corn.coreTec.join.joinException.exception;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadB extends Thread {


    @Override
    synchronized public void run() {
        try {
            System.out.println("begin  B thread name =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(500);
            System.out.println("end B  thread name =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}