package com.xiazhenyu.sesame;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Date: 2022/3/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyNewTest {


    public static void main(String[] args) {
        /*List<String> list = new ArrayList<String>() {
            {
                add("hello");
                add("world");
            }
        };
        System.out.println(list);*/
         final Pattern EXCHANGE_RATE_PATTERN = Pattern.compile("^(([1-9][0-9]{1,9}(\\.[0-9]{0,4})?)|(0\\.[0-9]?[1-9][0-9]{0,2}))$");

        Pattern ss = Pattern.compile("^0(\\.[0-9]{0,4})?$");
        System.out.println(EXCHANGE_RATE_PATTERN.matcher("11213000000.0000").find());
    }

}