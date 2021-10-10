package com.xiazhenyu.wheat.palindrome;

/**
 * Date: 2021/10/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FindMaxSubPalindrome {


    public static void main(String[] args) {
        String s = "abdeedffdsdd";
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome(s));
    }


    public static class Solution {

        int index;
        int length;

        public String longestPalindrome(String s) {
            if (s.length() < 2) {
                return s;
            }
            for (int i = 0; i < s.length(); i++) {
                palindromeHelper(s, i, i);
                palindromeHelper(s, i, i + 1);
            }
            return s.substring(index, index + length);

        }

        public void palindromeHelper(String s, int l, int r) {
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (this.length < r - l - 1) {
                this.index = l + 1;
                this.length = r - l - 1;
            }

        }
    }


}