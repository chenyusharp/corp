package com.xiazhenyu.sesame;

import org.openjdk.jol.info.ClassLayout;

/**
 * Date: 2021/12/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class JOLTest {


    public static void main(String[] args) {

        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        System.out.println();

        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());

        System.out.println();
        ClassLayout layout2 = ClassLayout.parseInstance(new A());
        System.out.println(layout2.toPrintable());


    }


   public static class A{
        int id;
        String name;
        byte b;
        Object o;
    }

}