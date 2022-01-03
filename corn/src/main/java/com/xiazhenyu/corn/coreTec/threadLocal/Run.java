package com.xiazhenyu.corn.coreTec.threadLocal;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    private static ThreadLocalExt ext = new ThreadLocalExt();

    public static void main(String[] args) {

        if (ext.get() == null) {
            System.out.println("从未放过值");
            ext.set("我的值");
        }
        System.out.println(ext.get());
        System.out.println(ext.get());
    }

}