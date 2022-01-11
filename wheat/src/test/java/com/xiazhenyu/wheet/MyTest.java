package com.xiazhenyu.wheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 2022/1/11
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyTest {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer[] result = new Integer[3];
        System.out.println(list.toArray(result));

    }

}