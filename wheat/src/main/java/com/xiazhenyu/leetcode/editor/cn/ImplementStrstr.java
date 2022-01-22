//实现 strStr() 函数。
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。 
//
// 
//
// 说明： 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "hello", needle = "ll"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
// 
//
// 示例 3： 
//
// 
//输入：haystack = "", needle = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= haystack.length, needle.length <= 5 * 10⁴ 
// haystack 和 needle 仅由小写英文字符组成 
// 
// Related Topics 双指针 字符串 字符串匹配 👍 1218 👎 0

package com.xiazhenyu.leetcode.editor.cn;

public class ImplementStrstr {

    public static void main(String[] args) {
        Solution solution = new ImplementStrstr().new Solution();
        System.out.println(solution.strStr("bbaa", "aab"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int strStr(String haystack, String needle) {
            /*-- 第二种解法--*/
            /*if (needle.length() == 0) {
                return 0;
            }

            int fast = 0, slow = 0, count = 0;
            while (fast < haystack.length() && slow < needle.length()) {
                if (haystack.charAt(fast) == needle.charAt(slow)) {
                    slow++;
                    count++;
                    if (count == needle.length()) {
                        break;
                    }
                } else {
                    fast=fast-count;
                    count = 0;
                    slow = 0;
                }
                fast++;
            }
            return count == needle.length() ? fast - needle.length() + 1 : -1;*/


            /*-- 第二种解法--*/
           /* if (needle.length() == 0) {
                return 0;
            }
            if (haystack.length() < needle.length()) {
                return -1;
            }

            int slow = 0, count = 0;
            for (slow = 0; slow < haystack.length(); slow++) {
                int fast = slow;
                while ((fast - slow < needle.length()) && fast < haystack.length()) {
                    if (haystack.charAt(fast) != needle.charAt(fast - slow)) {
                        count = 0;
                        break;
                    } else {
                        count++;
                    }
                    fast++;
                }
                if (count == needle.length()) {
                    return slow;
                }else {
                    count=0;
                }
            }
            return -1;*/


            /*--KMP算法--*/
            int n = haystack.length(), m = needle.length();
            if (m == 0) {
                return 0;
            }
            int[] pi = new int[m];
            for (int i = 1, j = 0; i < m; i++) {
                while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                    j = pi[j - 1];
                }
                if (needle.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                pi[i] = j;
            }
            for (int i = 0, j = 0; i < n; i++) {
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    j = pi[j - 1];
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                if (j == m) {
                    return i - m + 1;
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}