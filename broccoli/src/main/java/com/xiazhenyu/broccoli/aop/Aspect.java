package com.xiazhenyu.broccoli.aop;

import java.lang.reflect.Method;

/**
 * Date: 2022/10/29
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Aspect {


    boolean before(Object target, Method method, Object[] args);


    boolean after(Object target, Method method, Object[] args, Object returnVal);


    boolean afterException(Object target, Method method, Object[] args, Throwable e);


}
