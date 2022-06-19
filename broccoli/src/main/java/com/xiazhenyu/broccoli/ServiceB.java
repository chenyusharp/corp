package com.xiazhenyu.broccoli;

/**
 * Date: 2022/6/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ServiceB implements ServiceInterface {

    @Override
    public void test() {
        System.out.println("ServiceB");
    }
}