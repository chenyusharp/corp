package com.xiazhenyu.sesame.classLoader;

/**
 * Date: 2022/1/9
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ClinitTest {


    static int i = 1;

    static {
        i = 0;
        System.out.println(i);
    }

    //提示非法向前引用
//    static int i = 1;


    public static void main(String[] args) {
        System.out.println(ClinitTest.i);
    }


}