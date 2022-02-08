//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用 一次 。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
// Related Topics 数组 回溯 👍 820 👎 0

package com.xiazhenyu.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumIi {

    public static void main(String[] args) {
        Solution solution = new CombinationSumIi().new Solution();
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        System.out.println(solution.combinationSum2(candidates, 8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            //sort
            Arrays.sort(candidates);
            backTracing(candidates, target, 0, new ArrayList<>(), 0);
            return result;
        }

        private void backTracing(int[] candidates, int target, int start, ArrayList<Integer> path, int sum) {
            if (sum == target) {
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                if (sum + candidates[i] > target) {
                    break;
                }
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                path.add(candidates[i]);
                backTracing(candidates, target, i + 1, path, sum + candidates[i]);
                path.remove(path.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}