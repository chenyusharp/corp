package com.xiazhenyu.wheat.palindrome;

import java.util.HashSet;

/**
 * @Author xiazhenyu
 * @Description 最长回文串,该算法的前提是该字符串确定是回文字符串了
 * 思路：
 * 1；首先将字符串转变为字符数组；
 * 2；遍历该数组，判断对应的字符是否在hashset中，如果不在就加进去，如果在就让count++，并从hashset中移除该字符
 * @Date 4:26 下午 2021/5/5
 **/
public class Palindrome {


    public  static int longestPalindrome(String s) {
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


    public static void main(String[] args) {
        String palindrom="xiazhexiazhe";
        System.out.println(longestPalindrome(palindrom));

    }

}
