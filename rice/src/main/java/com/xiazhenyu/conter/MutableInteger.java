package com.xiazhenyu.conter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Date: 2021/10/13
 * <p>
 * Description: 计数器实现
 *
 * @author xiazhenyu
 */
public final class MutableInteger {

    private int val;


    public MutableInteger(int val) {
        this.val = val;
    }


    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }


    @Override
    public String toString() {
        return Integer.toString(val);
    }


    public static Map<String, MutableInteger> count(List<String> strings) {
        HashMap<String, MutableInteger> c = new HashMap<>();
        strings.forEach(per -> {
            MutableInteger mutableInteger = new MutableInteger(1);
            MutableInteger last = c.put(per, mutableInteger);
            if (last != null) {
                mutableInteger.setVal(last.getVal() + 1);
            }
        });
        return c;
    }


    public static void main(String[] args) {
        String itemCriteria = "8000735";
        boolean result = Pattern.matches("[0-9]+", itemCriteria);
        System.out.println(result);
    }
}