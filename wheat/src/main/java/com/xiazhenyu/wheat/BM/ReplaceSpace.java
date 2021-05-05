package com.xiazhenyu.wheat.BM;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 11:41 下午 2021/5/4
 **/
public class ReplaceSpace {


    public static String replaceSpace(StringBuffer str) {

        int length = str.length();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char b = str.charAt(i);
            if (String.valueOf(b).equals(" ")) {
                result.append("%20");
            } else {
                result.append(b);
            }
        }
        return result.toString();

    }


    public static String replaceSpace2(StringBuffer str) {
        return str.toString().replaceAll("\\s", "%20");
    }


}
