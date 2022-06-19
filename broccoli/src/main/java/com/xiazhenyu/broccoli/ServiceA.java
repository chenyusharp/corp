package com.xiazhenyu.broccoli;

/**
 * Date: 2022/6/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ServiceA implements ServiceInterface {

    @Override
    public void test() {
        System.out.println("Service A");
    }
}