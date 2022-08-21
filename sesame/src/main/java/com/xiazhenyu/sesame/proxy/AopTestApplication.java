package com.xiazhenyu.sesame.proxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Date: 2022/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class AopTestApplication {

    public static void main(String[] args) {
        A a;
        try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class)) {
            a = ac.getBean(A.class);
        }
        a.f();
    }

}