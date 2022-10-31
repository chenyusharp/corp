package com.xiazhenyu.broccoli.aop;


import org.springframework.cglib.proxy.Enhancer;

/**
 * Date: 2022/10/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SpringCglibProxyFactory extends ProxyFactory {

    private static final long serialVersionUID = 4939949496325242955L;

    @Override
    public <T> T proxy(T target, Aspect aspect) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new SpringCglibInterceptor(target, aspect));
        return (T) enhancer.create();
    }
}