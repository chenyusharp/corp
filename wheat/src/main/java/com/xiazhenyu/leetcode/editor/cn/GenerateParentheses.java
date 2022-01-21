//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 👍 2306 👎 0

package com.xiazhenyu.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<String> generateParenthesis(int n) {

            List<String> ans = new ArrayList<>();
            if (n == 0) {
                return ans;
            }
            dfs("", n, n, ans);
            return ans;
        }

        private void dfs(String currStr, int left, int right, List<String> ans) {
            if (left == 0 && right == 0) {
                ans.add(currStr);
                return;
            }
            if (left > right) {
                return;
            }
            if (left > 0) {
                dfs(currStr + "(", left - 1, right, ans);
            }
            if (right > 0) {
                dfs(currStr + ")", left, right - 1, ans);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}