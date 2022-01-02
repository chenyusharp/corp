package com.xiazhenyu.corn.coreTec.singleton.nowLoad;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyObject {


    public static MyObject myObject = new MyObject();


    private MyObject() {
    }

    /**
     * 缺点是不能有其他实例变量，因为这个方法不是同步的
     */
    public static MyObject getInstance() {
        return myObject;
    }

}