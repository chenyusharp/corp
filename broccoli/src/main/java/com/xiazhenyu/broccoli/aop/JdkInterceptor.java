package com.xiazhenyu.broccoli.aop;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Date: 2022/10/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class JdkInterceptor implements InvocationHandler, Serializable {


    private final Object target;

    private final Aspect aspect;


    public Object getTarget() {
        return target;
    }

    public JdkInterceptor(Object target, Aspect aspect) {

        this.target = target;
        this.aspect = aspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Object target = this.target;
        final Aspect aspect = this.aspect;
        Object result = null;
        if (aspect.before(target, method, args)) {
            ReflectUtil.setAccessible(method);
            try {
                result = method.invoke(ClassUtil.isStatic(method) ? null : target, args);

            } catch (Exception e) {
                if (aspect.afterException(target, method, args, e)) {
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