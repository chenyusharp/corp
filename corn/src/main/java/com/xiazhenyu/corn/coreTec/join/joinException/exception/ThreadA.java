package com.xiazhenyu.corn.coreTec.join.joinException.exception;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {

    private ThreadB threadB;


    public ThreadA(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        try {
            synchronized (threadB) {
                System.out.println("begin A thread name=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(500);
                System.out.println("end A thread name=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}