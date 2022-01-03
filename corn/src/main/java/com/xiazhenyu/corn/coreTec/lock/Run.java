package com.xiazhenyu.corn.coreTec.lock;

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
        threadA.start();
        ThreadB threadB = new ThreadB(myService);
        threadB.start();
    }

}