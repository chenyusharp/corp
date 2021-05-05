package com.xiazhenyu.wheat.BM;

import java.util.Arrays;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 11:50 下午 2021/5/4
 **/
public class MaxPreMatch {


    public static String replaceSpace(String[] strs) {

        if (!checkStrs(strs)) {
            return "";
        }
        int len = strs.length;
        ;
        StringBuilder res = new StringBuilder();
        Arrays.sort(strs);

        int m = strs[0].length();
        int n = strs[len - 1].length();

        int num = Math.min(m, n);

        for (int i = 0; i < num; i++) {
            if (strs[0].charAt(i) == strs[len - 1].charAt(i)) {
                res.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return res.toString();


    }

    private static boolean checkStrs(String[] strs) {
        boolean flag = false;
        if (strs != null) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] != null && strs[i].length() != 0) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }


    public static void main(String[] args) {
        String[] strs = {"customer", "cuar", "cuat"};

        System.out.println(MaxPreMatch.replaceSpace(strs));
    }

}
