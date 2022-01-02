package com.xiazhenyu.corn.coreTec.suspend;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThread extends Thread {


    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }


    @Override
    public void run() {
        while (true) {
            i++;
        }
    }
}