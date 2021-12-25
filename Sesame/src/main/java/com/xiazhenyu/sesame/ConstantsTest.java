package com.xiazhenyu.sesame;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/12/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ConstantsTest {


    private int value = 1;
    public String s = "abc";
    public final static int f = 0x101;

    public void setValue(int v) {
        final int temp = 3;
        this.value = temp + v;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
//        String s1="java";
        String s2 = new StringBuilder("ja").append("va").toString();
//        String s3="java";
//        System.out.println(s1==s2);
//        System.out.println(s1.equals(s3));
//        System.out.println(s1==s3);

        String s0 = "hello world";
        String s1 = new String("hello world");

        System.out.println(s2.intern() == (s2));
        System.out.println(s2.intern().hashCode());
        System.out.println(s2.hashCode());

        System.out.println(s0 == s1);
        System.out.println(s1.equals(s0));

        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }


    }


}