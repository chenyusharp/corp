package com.xiazhenyu.wheat.palindrome;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: 2021/10/5
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FindLongestPalindrome {

    public static void main(String[] args) {
        String  string="xiazhenyuxiayu";
        System.out.println(find(string));
    }


    /***
     *
     * @return
     */
    private  static  int find(String originStr) {
        if ("".equals(originStr) || null == originStr) {
            return 0;
        }
        char[] originArray = originStr.toCharArray();
        Set<Character> hashSet = new HashSet<>();
        int count = 0;
        for (char c : originArray) {
            if (hashSet.contains(c)) {
                hashSet.remove(c);
                count++;
            } else {
                hashSet.add(c);
            }
        }
        return hashSet.isEmpty() ? count * 2 : count * 2 + 1;

    }

}