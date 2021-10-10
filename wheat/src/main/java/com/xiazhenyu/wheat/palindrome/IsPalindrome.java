package com.xiazhenyu.wheat.palindrome;

import static java.util.regex.Pattern.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date: 2021/10/5
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class IsPalindrome {


    public static void main(String[] args) {
        String string = "A man, a plan, a canal: Panama";
        System.out.println(check(string));
    }


    private static boolean check(String s) {
        if (s.length() == 0) {
            return true;
        }
        int l = 0;
        int r = s.length() - 1;
        boolean flag = true;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            } else if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                flag = false;
                break;
            } else {
                l++;
                r--;
            }
        }
        return flag;
    }

}