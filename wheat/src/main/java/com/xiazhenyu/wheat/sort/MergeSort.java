package com.xiazhenyu.wheat.sort;

/**
 * Date: 2022/1/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MergeSort {


    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = new int[]{3, 4, 7, 8, 1, 2, 5, 6};
        mergeSort.solution(nums);
        System.out.println(nums);
    }


    public void solution(int[] nums) {
//        sort(nums, 0, nums.length - 1);
        mergeSort(nums, 0, nums.length - 1);
    }


    private void mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        mergeOfTwoSortedArray(nums, left, mid, right);
    }


    private void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right) {
        int[] temp = copy(nums, left, right);
        int i = 0;
        int j = mid - left + 1;
        for (int k = 0; k < right - left + 1; k++) {
            if (i == mid - left + 1) {
                nums[left + k] = temp[j];
                j++;
            } else if (j == right - left + 1) {
                nums[left + k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[left + k] = temp[i];
                i++;
            } else {
                nums[left + k] = temp[j];
                j++;
            }
        }
    }


    public void sort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        //进行排序
        //左边部分进行排序
//        sort(nums, left, (right + left) / 2);
        //右边部分进行排序
//        sort(nums, (right + left) / 2 + 1, right);
        //左边和右边都排好了
        //拷贝左边的数组
        int[] leftPart = copy(nums, left, (right + left) / 2);
        //拷贝右边的数组
        int[] rightPart = copy(nums, (right + left) / 2 + 1, right);
        //进行比较
        int i = 0, j = 0, k = left;
        while (i < leftPart.length && j < rightPart.length && k <= right - 1) {
            if (leftPart[i] > rightPart[j]) {
                if (j < rightPart.length - 1) {
                    nums[k] = rightPart[j];
                    j++;
                } else {
                    nums[k] = leftPart[i];
                    i++;
                }
            } else {
                if (i < leftPart.length - 1) {
                    nums[k] = leftPart[i];
                    i++;
                } else if (i < j) {
                    nums[k] = rightPart[j];
                    j++;
                }
            }
            k++;
        }
        nums[right] = Math.max(leftPart[leftPart.length - 1], rightPart[rightPart.length - 1]);
    }


    //拷贝原始数组
    private int[] copy(int[] original, int left, int right) {
        int[] ans = new int[right - left + 1];
        for (int i = left; i <= right; i++) {
            ans[i - left] = original[i];
        }
        return ans;
    }


}