package com.xiazhenyu.corn.coreTec.threadLocal;

import static com.xiazhenyu.corn.coreTec.threadLocal.ThreadLocalNotExtends.getInstance;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {

    public static void main(String[] args) {
        try {

            for (int i = 0; i < 10; i++) {
                if (getInstance().get() == null) {
                    getInstance().set("main thead set this value");
                }
                System.out.println("get value  from main,value=" + getInstance().get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA threadA = new ThreadA();
            threadA.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}