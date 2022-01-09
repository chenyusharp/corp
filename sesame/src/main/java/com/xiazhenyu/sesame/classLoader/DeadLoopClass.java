package com.xiazhenyu.sesame.classLoader;

/**
 * Date: 2022/1/9
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DeadLoopClass {


    static {
//        if (true) {
            System.out.println(Thread.currentThread().getName() + " init DeadLoopClass");
//            while (true) {
//            }
//        }


    }


    public static void main(String[] args) {

        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread().getName() + " run over");
            }
        };
        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();

    }

}