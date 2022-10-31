package com.xiazhenyu.broccoli.aop;

import net.sf.cglib.proxy.Enhancer;

/**
 * Date: 2022/10/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CglibProxyFactory extends ProxyFactory {

    private static final long serialVersionUID = 2422776556548774431L;


    @Override
    public <T> T proxy(T target, Aspect aspect) {

        final Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new CglibInterceptor(target, aspect));

        return (T) enhancer.create();
    }
}