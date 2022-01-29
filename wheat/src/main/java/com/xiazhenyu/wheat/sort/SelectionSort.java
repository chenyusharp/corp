package com.xiazhenyu.wheat.sort;


/**
 * Date: 2022/1/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SelectionSort {


    public static void main(String[] args) {
        SelectionSort selectionSort=new SelectionSort();
        int[] nums=new int[]{3,4,7,8,1,2,5,6};
        selectionSort.solution(nums);
        System.out.println(nums);
    }

    public void solution(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    swap(nums, i, j);
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