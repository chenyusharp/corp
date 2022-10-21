package com.xiazhenyu.sesame.proxy;

/**
 * Date: 2022/8/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Order implements OrderApi {


    private String orderName;

    private int orderNum;

    private String orderUser;

    public Order(String orderName, int orderNum, String orderUser) {
        this.orderName = orderName;
        this.orderNum = orderNum;
        this.orderUser = orderUser;
    }

    @Override
    public String getOrderName() {
        return orderName;
    }

    @Override
    public void setOrderName(String user, String orderName) {
        this.orderName = orderName;
    }
}