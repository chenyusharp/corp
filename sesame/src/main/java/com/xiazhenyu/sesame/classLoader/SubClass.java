package com.xiazhenyu.sesame.classLoader;

/**
 * Date: 2022/1/8
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }


}