package com.xiazhenyu.corn.coreTec.singleton.lazyLoad;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(MyObject.getInstance().hashCode())).start();
        }


    }

}