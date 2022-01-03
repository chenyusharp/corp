package com.xiazhenyu.corn.coreTec.join.joinException.exception;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class RunFirst {


    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA(threadB);

        threadA.start();
        threadB.start();

        System.out.println("main end");
    }

}