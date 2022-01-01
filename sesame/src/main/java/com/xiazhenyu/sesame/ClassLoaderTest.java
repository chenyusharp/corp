package com.xiazhenyu.sesame;

/**
 * Date: 2021/12/11
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ClassLoaderTest {


    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());

    }

}