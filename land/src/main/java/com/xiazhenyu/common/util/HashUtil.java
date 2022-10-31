package com.xiazhenyu.common.util;


/**
 * Hash算法大全<br> 推荐使用FNV1算法
 *
 * @author Goodzzp, Looly
 */
public class HashUtil {

    /**
     * JAVA自己带的算法
     *
     * @param str 字符串
     * @return hash值
     */
    public static int javaDefaultHash(String str) {
        int h = 0;
        int off = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            h = 31 * h + str.charAt(off++);
        }
        return h;
    }

}
