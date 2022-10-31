package com.xiazhenyu.broccoli.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Date: 2022/10/29
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public final class ProxyUtil {


    public static <T> T proxy(T target, Class<? extends Aspect> aspectClass) {
        return ProxyFactory.createProxy(target, aspectClass);
    }


    public static <T> T newProxyInstance(ClassLoader classLoader, InvocationHandler invocationHandler, Class<?>... interfaces) {
        return (T) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }


    public static <T> T newProxyInstance(InvocationHandler invocationHandler, Class<?>... interfaces) {
        return newProxyInstance(ClassUtil.getClassLoader(), invocationHandler, interfaces);
    }


}