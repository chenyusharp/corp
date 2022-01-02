package com.xiazhenyu.corn.coreTec.communication.wait.notify;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Thread2 extends Thread {


    private Object lock;


    public Thread2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(" notify start");
            lock.notify();
            System.out.println(" notify end");
        }
    }
}