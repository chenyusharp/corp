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

    /**
     * HFIP Hash算法
     *
     * @param data 字符串
     * @return hash结果
     * @since 5.8.0
     */
    public static long hfIpHash(String data) {
        int length = data.length();
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += data.charAt(i % 4) ^ data.charAt(i);
        }
        return hash;
    }


    /**
     * JS算法
     *
     * @param str 字符串
     * @return hash值
     */
    public static int jsHash(String str) {
        int hash = 1315423911;
        for (int i = 0; i < str.length(); i++) {
            hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
        }
        return Math.abs(hash) & 0x7FFFFFFF;
    }


    /**
     * PJW算法
     *
     * @param str 字符串
     * @return hash值
     */
    public static int pjwHash(String str) {
        int bitsInUnsignedInt = 32;
        int threeQuarters = (bitsInUnsignedInt * 3) / 4;
        int oneEighth = bitsInUnsignedInt / 8;
        int highBits = 0xFFFFFFFF << (bitsInUnsignedInt - oneEighth);
        int hash = 0;
        int test;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << oneEighth) + str.charAt(i);

            if ((test = hash & highBits) != 0) {
                hash = ((hash ^ (test >> threeQuarters)) & (~highBits));
            }
        }
        return hash & 0x7FFFFFFF;
    }


    /**
     * RS算法hash
     *
     * @param str 字符串
     * @return hash值
     */
    public static int rsHash(String str) {
        int b = 378551;
        int a = 63689;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        return hash & 0x7FFFFFFF;
    }

    /**
     * SDBM算法
     *
     * @param str 字符串
     * @return hash值
     */
    public static int sdbmHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return hash & 0x7FFFFFFF;
    }

    /**
     * TianL Hash算法
     *
     * @param str 字符串
     * @return Hash值
     */
    public static long tianlHash(String str) {
        long hash;

        int iLength = str.length();
        if (iLength == 0) {
            return 0;
        }

        if (iLength <= 256) {
            hash = 16777216L * (iLength - 1);
        } else {
            hash = 4278190080L;
        }

        int i;

        char ucChar;

        if (iLength <= 96) {
            for (i = 1; i <= iLength; i++) {
                ucChar = str.charAt(i - 1);
                if (ucChar <= 'Z' && ucChar >= 'A') {
                    ucChar = (char) (ucChar + 32);
                }
                hash += (3L * i * ucChar * ucChar + 5L * i * ucChar + 7L * i + 11 * ucChar) % 16777216;
            }
        } else {
            for (i = 1; i <= 96; i++) {
                ucChar = str.charAt(i + iLength - 96 - 1);
                if (ucChar <= 'Z' && ucChar >= 'A') {
                    ucChar = (char) (ucChar + 32);
                }
                hash += (3L * i * ucChar * ucChar + 5L * i * ucChar + 7L * i + 11 * ucChar) % 16777216;
            }
        }
        if (hash < 0) {
            hash *= -1;
        }
        return hash;
    }


}
