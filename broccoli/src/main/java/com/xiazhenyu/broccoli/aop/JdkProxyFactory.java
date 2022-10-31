package com.xiazhenyu.broccoli.aop;

/**
 * Date: 2022/10/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class JdkProxyFactory extends ProxyFactory {

    private static final long serialVersionUID = 4274699762930879709L;

    @Override
    public <T> T proxy(T target, Aspect aspect) {

        return ProxyUtil.newProxyInstance(target.getClass().getClassLoader(),
                new JdkInterceptor(target, aspect),
                target.getClass().getInterfaces());
    }
}