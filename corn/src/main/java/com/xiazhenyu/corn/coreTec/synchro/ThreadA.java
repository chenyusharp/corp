package com.xiazhenyu.corn.coreTec.synchro;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {


    private MyObject myObject;

    public ThreadA(MyObject myObject) {
        super();
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        myObject.method();
    }
}