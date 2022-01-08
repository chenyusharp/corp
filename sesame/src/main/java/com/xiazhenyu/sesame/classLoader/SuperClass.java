package com.xiazhenyu.sesame.classLoader;

/**
 * Date: 2022/1/8
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SuperClass {


    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

}