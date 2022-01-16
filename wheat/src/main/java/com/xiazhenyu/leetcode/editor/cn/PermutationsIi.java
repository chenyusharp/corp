//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯 👍 913 👎 0

package com.xiazhenyu.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsIi {

    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
        System.out.println(solution.permuteUnique(new int[]{1, 1, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }
            Arrays.sort(nums);
            dfs(nums, len, 0, new ArrayList<>(), new boolean[len], res);
            return res;
        }

        private void dfs(int[] nums, int len, int depth, ArrayList<Integer> path, boolean[] used, List<List<Integer>> res) {
            if (depth == len) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < len; i++) {
                if (!used[i]) {
                    if (i > 0 && nums[i - 1] == nums[i] && !used[i-1]) {
                        continue;
                    }
                    path.add(nums[i]);
                    used[i] = true;
                    dfs(nums, len, depth + 1, path, used, res);
                    used[i] = false;
                    path.remove(path.size() - 1);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}