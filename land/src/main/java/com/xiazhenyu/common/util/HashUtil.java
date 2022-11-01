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


    /**
     * ELF算法
     *
     * @param str 字符串
     * @return hash值
     */
    public static int elfHash(String str) {
        int hash = 0;
        int x;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 4) + str.charAt(i);
            if ((x = (int) (hash & 0xF0000000L)) != 0) {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }

        return hash & 0x7FFFFFFF;
    }


    /**
     * 改进的32位FNV算法1
     *
     * @param data 字符串
     * @return hash结果
     */
    public static int fnvHash(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }


    /**
     * HF Hash算法
     *
     * @param data 字符串
     * @return hash结果
     * @since 5.8.0
     */
    public static long hfHash(String data) {
        int length = data.length();
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += (long) data.charAt(i) * 3 * i;
        }
        if (hash < 0) {
            hash = -hash;
        }
        return hash;
    }


}
