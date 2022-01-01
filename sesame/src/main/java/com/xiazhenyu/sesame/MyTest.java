package com.xiazhenyu.sesame;

import org.junit.Test;

/**
 * Date: 2021/12/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyTest {


    int a = 0;
    boolean flag;


    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myTest.A();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myTest.B();
            }
        }).start();

        System.out.println("this is end");
    }


    @Test
    public void play() {
        MyTest myTest = new MyTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myTest.A();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myTest.B();
            }
        }).start();

        System.out.println("this is end");


    }


    public void A() {
        a = 1;
        flag = true;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void B() {
        if (flag) {
            System.out.println("flag=true " + a);
        } else if (!flag) {
            System.out.println("flag=false " + a);
        }
    }

}