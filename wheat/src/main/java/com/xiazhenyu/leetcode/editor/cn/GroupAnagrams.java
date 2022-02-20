//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。 
//
// 
//
// 示例 1: 
//
// 
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]] 
//
// 示例 2: 
//
// 
//输入: strs = [""]
//输出: [[""]]
// 
//
// 示例 3: 
//
// 
//输入: strs = ["a"]
//输出: [["a"]] 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 10⁴ 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 排序 👍 997 👎 0

package com.xiazhenyu.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.stylesheets.LinkStyle;

public class GroupAnagrams {

    public static void main(String[] args) {
        Solution solution = new GroupAnagrams().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<String>> groupAnagrams(String[] strs) {

           /* Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = new String(array);
                List<String> list = map.getOrDefault(key, new ArrayList<String>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<>(map.values());*/

            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                int[] counts = new int[26];
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    counts[str.charAt(i) - 'a']++;
                }
                //将每个出现次数大于0的字母和出现次数按照按顺序拼接成字符串，作为哈希表的键
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < 26; i++) {
                    if (counts[i] != 0) {
                        sb.append('a' + i);
                        sb.append(counts[i]);
                    }
                }
                String key = sb.toString();
                List<String> list = map.getOrDefault(key, new ArrayList<>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<List<String>>(map.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}