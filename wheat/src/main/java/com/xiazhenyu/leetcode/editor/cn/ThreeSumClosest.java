//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
//
// 返回这三个数的和。 
//
// 假定每组输入只存在恰好一个解。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0], target = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 1000 
// -1000 <= nums[i] <= 1000 
// -10⁴ <= target <= 10⁴ 
// 
// Related Topics 数组 双指针 排序 👍 1003 👎 0

package com.xiazhenyu.leetcode.editor.cn;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        Solution solution = new ThreeSumClosest().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int threeSumClosest(int[] nums, int target) {
            //对原有的数组进行排序
            Arrays.sort(nums);
            if (nums.length < 3) {
                return -1;
            }
            int sum = 0;
            int before = 10000000;
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                //定义连个指针
                int L = i + 1, R = nums.length - 1;
                while (L < R) {
                    sum = nums[i] + nums[L] + nums[R];
                    if (sum == target) {
                        return target;
                    }
                    if (Math.abs(sum - target) < Math.abs(before - target)) {
                        before = sum;
                    }
                    if (sum < target) {
                        while ((L + 1) < R && nums[L] == nums[L + 1]) {
                            L++;
                        }
                        L++;
                    }

                    if (sum > target) {
                        while ((R - 1) > L && nums[R] == nums[R - 1]) {
                            R--;
                        }
                        R--;
                    }
                }
            }
            return before;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}