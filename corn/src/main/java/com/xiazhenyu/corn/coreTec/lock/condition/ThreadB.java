package com.xiazhenyu.corn.coreTec.lock.condition;

/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadB extends Thread {

    private ThreadA threadA;

    public ThreadB(ThreadA threadA) {
        this.threadA = threadA;
    }

    @Override
    public void run() {
        threadA.tongzhi();
    }
}