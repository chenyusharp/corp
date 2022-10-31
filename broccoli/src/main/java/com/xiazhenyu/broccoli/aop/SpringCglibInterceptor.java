package com.xiazhenyu.broccoli.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Date: 2022/10/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SpringCglibInterceptor implements MethodInterceptor, Serializable {


    private static final long serialVersionUID = 4943550181071159260L;


    private final Object target;

    private final Aspect aspect;


    public SpringCglibInterceptor(Object target, Aspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }

    public Object getTarget() {
        return target;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        final Object target = this.target;
        Object result = null;
        if (aspect.before(target, method, args)) {
            try {
                result = methodProxy.invoke(target, args);
            } catch (Exception e) {
                if (aspect.after(target, method, args, e)) {
                    throw e;
                }
            }
        }

        if (aspect.after(target, method, args, result)) {
            return result;
        }
        return null;
    }


}