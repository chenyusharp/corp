package com.xiazhenyu.corn.coreTec.join.joinException;


/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadC extends Thread {

    MyThreadB threadB = new MyThreadB();

    public ThreadC(MyThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        threadB.interrupt();
    }
}