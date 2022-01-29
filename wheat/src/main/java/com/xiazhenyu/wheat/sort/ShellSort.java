package com.xiazhenyu.wheat.sort;

/**
 * Date: 2022/1/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ShellSort {


    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] nums = new int[]{3, 4, 7, 8, 1, 2, 5, 6};
        shellSort.solution(nums);
        System.out.println(nums);
    }

    public void solution(int[] nums) {
        int gap = nums.length / 2;
        while (gap >= 1) {
            for (int i = 0; i < nums.length - gap; i += gap) {
                //执行插入排序
                int temp = nums[i + gap];
                for (int j = i; j >= 0; j -= gap) {
                    if (nums[j] > temp) {
                        nums[j + gap] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
            gap = gap / 2;
        }
    }

}