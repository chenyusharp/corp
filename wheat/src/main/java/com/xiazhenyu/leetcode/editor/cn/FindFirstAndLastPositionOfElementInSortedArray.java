//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 👍 1433 👎 0

package com.xiazhenyu.leetcode.editor.cn;

public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();

        int[] ans = solution.searchRange(new int[]{1, 2, 3}, 1);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) {
                return new int[]{-1, -1};
            }
            if (nums.length == 1) {
                return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
            }
            int leftIndex = binarySearch(nums, target, true);
            int rightIndex = binarySearch(nums, target, false)-1;
            if (leftIndex <= rightIndex && rightIndex<nums.length&& nums[leftIndex]==target&&nums[rightIndex]==target) {
                return new int[]{leftIndex, rightIndex};
            }
            return new int[]{-1, -1};
        }

//        private int binarySearch(int[] nums, int target, boolean lower) {
//            int n = nums.length;
//            int l = 0, r = n - 1, leftIndex = 0, rightIndex = 0, result = -1;
//            while (l <= r) {
//                int mid = (l + r) / 2;
//                if (lower) {
//                    if (nums[mid] == target) {
//                        result = mid;
//                    }
//                } else {
//                    if (nums[mid] > target) {
//                        result = mid;
//                    }
//                }
//
//                //寻找第一个大于等于target的下标
//                if (lower) {
//                    if (nums[0] <= target && target <= nums[mid]) {
//                        r = mid - 1;
//                    } else {
//                        l = mid + 1;
//                    }
//
//                } else {
//                    //寻找第一个大于target的下标
//                    if (nums[0] <= target && target <= nums[mid]) {
//                        l = mid + 1;
//                    } else {
//                        l = mid + 1;
//                    }
//                }
//            }
//            return lower ? result : result - 1;
//        }


        private int binarySearch(int[] nums, int target, boolean lower) {
            int l = 0, r = nums.length - 1, ans = nums.length;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] > target || (lower && nums[mid] >= target)) {
                    r = mid - 1;
                    ans = mid;
                } else {
                    l = mid + 1;
                }
            }
            return ans;

        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}