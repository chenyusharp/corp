package com.xiazhenyu.sesame.classLoader;

/**
 * Date: 2022/1/8
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ConstantClass {


    static {
        System.out.println("ConstantClass init!");
    }

    public static final String HELLOWORLD = "hello world";


}