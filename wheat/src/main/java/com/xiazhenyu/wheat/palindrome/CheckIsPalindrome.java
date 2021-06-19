package com.xiazhenyu.wheat.palindrome;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 4:36 下午 2021/5/5
 **/
public class CheckIsPalindrome {


    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            } else {
                if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                    return false;
                }
                l++;
                r--;
            }
        }

        return true;

    }


}
