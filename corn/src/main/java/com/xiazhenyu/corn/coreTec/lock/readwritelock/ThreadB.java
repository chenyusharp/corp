package com.xiazhenyu.corn.coreTec.lock.readwritelock;


/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadB extends Thread {

    private MyService myService;

    public ThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod1();
    }
}