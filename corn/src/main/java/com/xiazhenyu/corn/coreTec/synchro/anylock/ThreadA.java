package com.xiazhenyu.corn.coreTec.synchro.anylock;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {


    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.getUserNamePassword("a", "aa");

    }
}