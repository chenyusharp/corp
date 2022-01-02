package com.xiazhenyu.corn.coreTec.singleton.lazyLoad;

import com.xiazhenyu.corn.lock.SynchonizedTest.A;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyObject {


    //为什么设置成volatile，防止代码的重排序。因为myObject = new MyObject()这个操作在JVM中是分成3步的，
    //有可能会出现myObject的这个引用不是null，但是其对应的内存中的实例对象中的变量还没有初始化，还都数据类型的默认值。会出现NPE
    public static volatile MyObject myObject;


    private MyObject() {
    }

    /**
     *
     */
    public static MyObject getInstance() {
        if (myObject == null) {
            //synchronized同步代码块是为了解决多线程环境下，同步整个方法的获取性能问题
            synchronized (MyObject.class) {
                // DCL双重检查，避免多线程情况下，重复获取实例的过程（Double Check Lock）
                if (myObject == null) {
                    myObject = new MyObject();
                }
            }
            return myObject;
        }
        return myObject;


    }

}