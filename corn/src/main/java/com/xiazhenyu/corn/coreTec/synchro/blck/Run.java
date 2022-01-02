package com.xiazhenyu.corn.coreTec.synchro.blck;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {

    public static void main(String[] args) {
//        runForTask();
        runForObjectService();
    }


    public static void runForTask() {
        Task task = new Task();
        MyThread1 myThread1 = new MyThread1(task);
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(task);
        myThread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long beginTime = Long.max(CommonUtil.beginTime1, CommonUtil.beginTime2);
        long endTime = Long.max(CommonUtil.endTime1, CommonUtil.endTime2);
        System.out.println("use time:" + (endTime - beginTime));
    }


    public static void runForObjectService() {
        ObjectService objectService = new ObjectService();
        ThreadA threadA = new ThreadA(objectService);
        threadA.setName("a");
        threadA.start();

        ThreadB threadB = new ThreadB(objectService);
        threadB.setName("b");
        threadB.start();
    }


}