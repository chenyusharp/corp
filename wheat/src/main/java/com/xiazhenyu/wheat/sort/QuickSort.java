package com.xiazhenyu.wheat.sort;

/**
 * Date: 2022/1/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class QuickSort {


    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new int[]{3, 4, 7, 8, 1, 2, 5, 6};
        quickSort.solution(nums);
        System.out.println(nums);
    }


    public void solution(int[] nums) {
        quickSort(nums, 0, nums.length - 1);

    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);

    }

    private int partition(int[] nums, int left, int right) {
        int privot = nums[left];
        int lt = left;
        for (int i = left+1; i <= right; i++) {
            if (nums[i] < privot) {
                //进行交换
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}