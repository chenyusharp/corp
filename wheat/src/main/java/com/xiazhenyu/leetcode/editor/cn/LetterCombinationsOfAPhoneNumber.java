//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 👍 1680 👎 0

package com.xiazhenyu.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        System.out.println(solution.letterCombinations("23"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        HashMap<Character, String> map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkf");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        String[] phone = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if ("".equals(digits)) {
                return ans;
            }
            dfs(digits, 0, new StringBuilder());
            return ans;
        }

        private void dfs(String digits, int start, StringBuilder sb) {
            if (start == digits.length()) {
                ans.add(new String(sb));
                return;
            }
            //char相减可以得到对应的差值
            int curIdx = digits.charAt(start) - '0';
            String curPhoneNum = phone[curIdx];
            for (int i = 0; i < curPhoneNum.length(); i++) {
                sb.append(curPhoneNum.charAt(i));
                dfs(digits, start + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}