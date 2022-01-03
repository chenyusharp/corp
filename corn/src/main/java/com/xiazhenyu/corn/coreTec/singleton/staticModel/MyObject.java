package com.xiazhenyu.corn.coreTec.singleton.staticModel;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyObject {

    private static MyObject instance = null;

    private MyObject() {
    }

    static {
        instance = new MyObject();
    }

    public static MyObject getInstance() {
        return instance;
    }

}