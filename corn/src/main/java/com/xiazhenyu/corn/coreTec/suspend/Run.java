package com.xiazhenyu.corn.coreTec.suspend;

import lombok.SneakyThrows;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {

    public static void main(String[] args) {
//        mainForMyThread();
//        mainForSynchronizedObject();
//        lockStop();
        mainForMyObject();
    }


    public static void mainForMyThread() {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(5000);
            myThread.suspend();

            System.out.println("thread is suspend");
            Thread.sleep(3000);

            myThread.resume();

            System.out.println("thread is resume");
            myThread.stop();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void mainForSynchronizedObject() {

        try {
            final SynchronizedObject object = new SynchronizedObject();

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    object.printString();
                }
            };

            thread1.setName("a");
            thread1.start();

            Thread.sleep(2000);
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    System.out.println("thread2 init...");
                    object.printString();
                }
            };

            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void lockStop() {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(1000);
            myThread.suspend();
            System.out.println("main thread end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    protected static class MyThread extends Thread {

        private long i = 0;

        @Override
        public void run() {
            while (true) {
                i++;
                System.out.println(i);
            }
        }
    }


    @SneakyThrows
    public static void mainForMyObject() {
        final MyObject myObject = new MyObject();
        Thread thread1 = new Thread() {

            @Override
            public void run() {
                myObject.setValue("a", "aa");
            }
        };
        thread1.setName("a");
        thread1.start();

        Thread.sleep(1000);

        Thread thread2 = new Thread() {

            @Override
            public void run() {
                myObject.printUserNameAndPassword();
            }
        };

        thread2.start();


    }


}