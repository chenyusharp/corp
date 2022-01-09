package com.xiazhenyu.sesame.classLoader;

import java.sql.SQLOutput;
import javax.crypto.spec.OAEPParameterSpec;

/**
 * Date: 2022/1/9
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FiledResolution {


    interface Interface0 {

        int A = 0;
    }


    interface Interface1 extends Interface0 {

        int A = 1;
    }


    interface Interface2 {

        int A = 2;
    }

    static class Parent implements Interface1 {

        public static int A = 3;
    }


    static class Sub extends Parent implements Interface2 {

        public static int A = 4;
    }


    public static void main(String[] args) {
        System.out.println(Sub.A);
    }


}