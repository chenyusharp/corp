//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 👍 1495 👎 0

package com.xiazhenyu.leetcode.editor.cn;

public class NextPermutation {

    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
        int[] nums = new int[]{1};
        solution.nextPermutation(nums);
        System.out.println("end");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public void nextPermutation(int[] nums) {
            if (nums.length <= 1) {
                return;
            }
           /* int fast = nums.length - 2, slow = nums.length - 1;
            while (fast >= 0 && slow >= 0) {
                if (nums[slow] <= nums[fast]) {
                    slow--;
                } else {
                    swap(nums, fast, slow);
                    return;
                }
                fast--;
            }*/

            int i = nums.length - 2;
            //从后往前遍历，定位到顺序排列的第一个下标
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                //从末尾开始进行遍历，找到比上面定位到的下标对应的元素大的位置。
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                //交换i、j对应的元素
                swap(nums, i, j);
            }

            //进行升序排列
//            insertSort(nums);
            reverse(nums, i + 1);
        }


        private void reverse(int[] nums, int start) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }

        }


        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }


        //插入排序
        private void insertSort(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                int temp = nums[i + 1];
                for (int j = i; j >= 0; j--) {
                    if (nums[j] > temp) {
                        nums[j + 1] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}