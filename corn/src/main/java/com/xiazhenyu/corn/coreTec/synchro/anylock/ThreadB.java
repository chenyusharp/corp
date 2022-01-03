package com.xiazhenyu.corn.coreTec.synchro.anylock;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadB extends Thread {


    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.getUserNamePassword("b", "bb");

    }
}