package com.xiazhenyu.corn.coreTec.communication.wait.notify;

import com.xiazhenyu.corn.coreTec.synchro.syncBlock.ObjectService;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Thread1 extends Thread {


    private Object lock;


    public Thread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("wait start");
                lock.wait();
                System.out.println("wait end");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}