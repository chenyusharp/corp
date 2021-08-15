package com.xiazhenyu.wheat.palindrome;

/**
 * Date: 2021/8/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FindLongestSubPalindrome {

    public static int index, len;

    public static String longestPalindromeSubSeq(String s) {
        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            PalindromeHelper(s, i, i);
            PalindromeHelper(s, i, i + 1);
        }
        return s.substring(index, index + len);
    }

    private  static  void PalindromeHelper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (len < r - l - 1) {
            index = l + 1;
            len = r - l - 1;
        }
    }


    public static void main(String[] args) {
        String s="abdeedffdsdd";
        System.out.println(longestPalindromeSubSeq(s));
    }
}