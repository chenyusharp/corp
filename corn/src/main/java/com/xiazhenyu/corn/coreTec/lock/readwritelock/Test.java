package com.xiazhenyu.corn.coreTec.lock.readwritelock;

/**
 * Date: 2022/1/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {

    public static void main(String[] args) {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        ThreadB threadB = new ThreadB(myService);
        threadA.start();
        threadB.start();
    }

}