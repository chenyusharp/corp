package com.xiazhenyu.corn.coreTec.lock.condition;

/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        threadA.setName("A");
        threadA.start();


        ThreadA threadA1 = new ThreadA(myService);
        threadA1.setName("A1");
        threadA1.start();

        ThreadA threadA2 = new ThreadA(myService);
        threadA2.setName("A2");
        threadA2.start();


        //invoke signal method
        ThreadB threadB=new ThreadB(threadA);
        threadB.setName("B");
        threadB.start();

        ThreadB threadB1=new ThreadB(threadA1);
        threadB1.setName("B1");
        threadB1.start();

        ThreadB threadB2=new ThreadB(threadA2);
        threadB2.setName("B2");
        threadB2.start();

        myService.signalMethod();


    }

}