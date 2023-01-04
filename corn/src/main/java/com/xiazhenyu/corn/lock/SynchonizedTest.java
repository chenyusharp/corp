package com.xiazhenyu.corn.lock;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

/**
 * Date: 2021/10/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SynchonizedTest {

    public static void main(String[] args) throws InterruptedException {
//        A a = new A();
//        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        final Thread myThread = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println(Thread.currentThread());
                    TimeUnit.SECONDS.sleep(60);
                }
            }
        };

        myThread.setName("测试线程");
        myThread.start();

        synchronized (myThread) {
            System.out.println(Thread.currentThread());
            TimeUnit.SECONDS.sleep(60);
        }


    }


    public static class A {

        public A() {
        }

        public A(boolean flag) {
            this.flag = flag;
        }

        boolean flag = false;
    }
}