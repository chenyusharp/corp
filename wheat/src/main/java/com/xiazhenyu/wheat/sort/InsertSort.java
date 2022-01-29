package com.xiazhenyu.wheat.sort;

/**
 * Date: 2022/1/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class InsertSort {


    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        int[] nums = new int[]{3, 4, 7, 8, 1, 2, 5, 6};
        insertSort.solution2(nums);
        System.out.println(nums);
    }


    /*--第一种插入排序实现--*/
    public void solution1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j >= 0; j--) {
                if (j >= 1 && nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }


    /*--第二种插入排序实现算法--*/
    public void solution2(int[] nums) {
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

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}