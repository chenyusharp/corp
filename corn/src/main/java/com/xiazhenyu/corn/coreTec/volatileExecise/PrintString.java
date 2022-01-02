package com.xiazhenyu.corn.coreTec.volatileExecise;

import com.xiazhenyu.corn.coreTec.synchro.syncBlock.ThreadA;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class PrintString implements Runnable {

    private boolean isContinuePrint = true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }


    public void printStringMethod() {
        try {
            while (isContinuePrint) {
                System.out.println("run printStringMethod threadName=" + Thread.currentThread().getName());
                Thread.sleep(1000);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        printStringMethod();
    }
}