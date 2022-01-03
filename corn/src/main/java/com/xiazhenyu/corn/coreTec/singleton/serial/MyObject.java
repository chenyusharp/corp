package com.xiazhenyu.corn.coreTec.singleton.serial;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import java.io.Serializable;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyObject implements Serializable {

    private static final long serialVersionUID = 6153707770347585028L;

    public static UserInfo userInfo = new UserInfo();
    private static MyObject myObject = new MyObject();

    private MyObject() {

    }

    public static MyObject getInstance() {
        return myObject;
    }

    //反序列化的时候，会调用这个方法。
    //可以参考{https://juejin.cn/post/6844904142402486285}这个文章
    protected Object readResolve() {
        System.out.println("call readResolve方法");
        return MyObject.myObject;
    }


}