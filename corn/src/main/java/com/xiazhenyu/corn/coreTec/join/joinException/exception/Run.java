package com.xiazhenyu.corn.coreTec.join.joinException.exception;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {

    public static void main(String[] args) {
        try {

            ThreadB threadB = new ThreadB();
            ThreadA threadA = new ThreadA(threadB);
            threadA.start();
            threadB.start();
            threadB.join(200);
            System.out.println("main end");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}