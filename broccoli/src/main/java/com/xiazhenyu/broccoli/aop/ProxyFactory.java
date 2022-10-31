package com.xiazhenyu.broccoli.aop;

import java.io.Serializable;

/**
 * Date: 2022/10/29
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class ProxyFactory implements Serializable {


    private static final long serialVersionUID = -8250579164419470617L;


    public <T> T proxy(T target, Class<? extends Aspect> aspectClass) {
        return proxy(target, ReflectUtil.newInstanceIfPossible(aspectClass));
    }


    public abstract <T> T proxy(T target, Aspect aspect);


    public static <T> T createProxy(T target, Class<? extends Aspect> aspectClass) {
        return createProxy(target, ReflectUtil.newInstance(aspectClass));
    }


    public static <T> T createProxy(T target, Aspect aspect) {
        return create().proxy(target, aspect);
    }


    public static ProxyFactory create() {
        return ServiceLoaderUtil.loadFirstAvailable(ProxyFactory.class);

    }


}