package com.xiazhenyu.corn.coreTec.lock.condition;

/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {

    private MyService myService;

    public ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.awaitMethod(2);
    }


    public void tongzhi() {
        myService.signalMethod();
    }

}