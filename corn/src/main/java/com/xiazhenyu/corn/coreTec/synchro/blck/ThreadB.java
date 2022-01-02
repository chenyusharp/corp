package com.xiazhenyu.corn.coreTec.synchro.blck;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadB extends Thread {

    private ObjectService objectService;

    public ThreadB(ObjectService objectService) {
        this.objectService = objectService;
    }

    @Override
    public void run() {
        objectService.serviceMethod();
    }
}