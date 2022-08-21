package com.xiazhenyu.sesame.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Date: 2022/8/21
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Component
public class B {

    @Autowired
    private A a;

    public void f() {
        System.out.println("BBBBBBB");
    }


}