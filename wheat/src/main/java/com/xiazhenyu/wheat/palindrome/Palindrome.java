package com.xiazhenyu.wheat.palindrome;

import java.util.HashSet;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 4:26 下午 2021/5/5
 **/
public class Palindrome {


    public int longestPalindrome(String s) {
        if (s.length() == 0) {
            return 0;
        }

        HashSet<Character> hashSet = new HashSet<>();
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!hashSet.contains(chars[i])) {
                hashSet.add(chars[i]);
            } else {
                hashSet.remove(chars[i]);
                count++;
            }
        }
        return hashSet.isEmpty() ? count * 2 : count * 2 + 1;

    }

}
