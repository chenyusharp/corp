package com.xiazhenyu.sesame.proxy;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Date: 2022/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Component
@Aspect
public class Aop {


    @Pointcut("execution(* com.xiazhenyu.sesame.proxy..*.*(..))")
    private void pointcut() {
    }

    @After("pointcut()")
    public void advice() {
        System.out.println("之后增强---------");
    }


}


