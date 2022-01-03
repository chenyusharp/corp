package com.xiazhenyu.corn.coreTec.singleton.staticHandler;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyObject {

    private MyObject() {
    }

    private static class MyObjectHandler {

        private static MyObject myObject = new MyObject();
    }


    public static MyObject getInstance() {
        return MyObjectHandler.myObject;
    }

}