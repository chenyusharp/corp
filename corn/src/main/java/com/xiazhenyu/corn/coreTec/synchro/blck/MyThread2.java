package com.xiazhenyu.corn.coreTec.synchro.blck;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThread2 extends Thread {

    private Task task;

    public MyThread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        CommonUtil.beginTime2 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtil.endTime2 = System.currentTimeMillis();
    }
}