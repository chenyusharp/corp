//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 👍 1960 👎 0

package com.xiazhenyu.leetcode.editor.cn;

import com.sun.org.apache.regexp.internal.RE;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String longestCommonPrefix(String[] strs) {

            if (strs == null || strs.length == 0) {
                return "";
            } else {
                return longestCommonPrefix(strs, 0, strs.length - 1);
            }

        }

        public String longestCommonPrefix(String[] strs, int start, int end) {
            if (start == end) {
                return strs[start];
            } else {
                //数组取中，而且这个中是在整个数组中的；
                int mid = (end - start) / 2 + start;
                String lcpLeft = longestCommonPrefix(strs, start, mid);
                String lcpRight = longestCommonPrefix(strs, mid + 1, end);
                return commonPrefix(lcpLeft, lcpRight);
            }
        }


        public String commonPrefix(String lcpLeft, String lcpRight) {
            int minLength = Math.min(lcpLeft.length(), lcpRight.length());
            for (int i = 0; i < minLength; i++) {
                if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                    return lcpLeft.substring(0, i);
                }
            }
            return lcpLeft.substring(0, minLength);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}