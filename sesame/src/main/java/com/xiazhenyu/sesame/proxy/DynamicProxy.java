package com.xiazhenyu.sesame.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

/**
 * Date: 2022/8/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DynamicProxy implements InvocationHandler {

    private OrderApi order = null;


    public OrderApi getProxyInterface(Order orderApi) {
        this.order = orderApi;
        OrderApi orderApi1 = (OrderApi) Proxy.newProxyInstance(orderApi.getClass().getClassLoader(),
                orderApi.getClass().getInterfaces(),
                this);
        return orderApi1;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            if ("xiazhenyu".equals(order.getOrderName())) {
                return method.invoke(order, args);
            } else {
                throw new Exception();
            }
        } else {
            return method.invoke(order, args);
        }
    }

    public static void main(String[] args) {
        BigDecimal a=BigDecimal.ZERO;
        BigDecimal  b=null;
        System.out.println(a.multiply(b));
    }


}