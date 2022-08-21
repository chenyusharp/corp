package com.xiazhenyu.sesame.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Date: 2022/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Component
public class A {

    @Autowired
    private B b;


    public void f() {
        System.out.println("AAAA");
    }

}