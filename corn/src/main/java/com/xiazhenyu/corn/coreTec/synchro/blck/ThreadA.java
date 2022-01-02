package com.xiazhenyu.corn.coreTec.synchro.blck;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {

    private ObjectService objectService;

    public ThreadA(ObjectService objectService) {
        this.objectService = objectService;
    }

    @Override
    public void run() {
        objectService.serviceMethod();
    }
}