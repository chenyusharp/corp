package com.xiazhenyu.bean;

import java.util.List;
import lombok.Data;

/**
 * Date: 2023/12/13
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
//@Data
public class ResultVO {

    private List<User> item;


    private int total;

    public List<User> getItem() {
        return item;
    }

    public void setItem(List<User> item) {
        this.item = item;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}