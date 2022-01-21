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

    public static class Student {

        private int age;
        private String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static void main(String[] args) {
        /*MyTest myTest = new MyTest();
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

        System.out.println("this is end");*/

        int a = 10;
        int b = 100;
        int c = 30;
        a = b;
        b = c;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);

        Student A = new Student(10, "jack");

        Student B = new Student(20, "lily");

        Student C = new Student(40, "had");

        A = B;
        B = C;
        System.out.println(A.getAge());
        System.out.println(B.getAge());
        System.out.println(C.getAge());

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