package com.xiazhenyu.corn.coreTec.threadLocal;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {

    public static void main(String[] args) {
//        try {

//            for (int i = 0; i < 10; i++) {
//                if (getInstance().get() == null) {
//                    //threadLocal不能实现值继承。main线程中放入的值不能传递给ThreadA中。
//                    getInstance().set("main thead set this value");
//                }
//                System.out.println("get value  from main,value=" + getInstance().get());
//                Thread.sleep(100);
//            }
//            Thread.sleep(5000);
//            ThreadA threadA = new ThreadA();
//            threadA.start();
        for (int i = 0; i < 10; i++) {
            ThreadA threadA = new ThreadA();
            threadA.start();
        }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

}