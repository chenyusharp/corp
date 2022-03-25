package com.xiazhenyu.sesame;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2022/3/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyNewTest {


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {
            {
                add("hello");
                add("world");
            }
        };
        System.out.println(list);
    }

}