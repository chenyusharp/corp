package com.xiazhenyu.corn.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * Date: 2021/10/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SynchonizedTest {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }



    public  static  class A {

        public A() {
        }

        public A(boolean flag) {
            this.flag = flag;
        }

        boolean flag = false;
    }
}