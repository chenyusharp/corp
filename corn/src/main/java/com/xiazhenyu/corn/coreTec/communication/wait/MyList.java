package com.xiazhenyu.corn.coreTec.communication.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyList {

    volatile private List list = new ArrayList();

    public void add() {
        list.add("1");
    }


    public int size() {
        return list.size();
    }


}