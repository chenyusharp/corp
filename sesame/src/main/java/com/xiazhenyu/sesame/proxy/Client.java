package com.xiazhenyu.sesame.proxy;

/**
 * Date: 2022/8/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Client {


    public static void main(String[] args) {
        Order order = new Order("xiazhenyu1", 1, "xzy");
        DynamicProxy dynamicProxy = new DynamicProxy();

        OrderApi orderApi = dynamicProxy.getProxyInterface(order);

        orderApi.setOrderName("xiazhenyu", "zhangsan");

    }

}