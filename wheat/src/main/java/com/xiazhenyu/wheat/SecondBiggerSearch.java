package com.xiazhenyu.wheat;

import java.sql.Array;

/**
 * Date: 2022/1/9
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SecondBiggerSearch {

    public static int findSecondBiggerSearch(int[] array, int item) {

        if (array[array.length - 1] < item) {
            return -1;
        }
        int result = 0;
        int startPos = 0, endPos = array.length - 1;

        for (; ; ) {
            int current = array[(endPos + startPos) / 2];
            if (current > item) {
//                endPos = (endPos - startPos) / 2 - 1;
                result = current;
                break;
            } else {
                startPos = (endPos + startPos) / 2 + 1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(findSecondBiggerSearch(new int[]{-6, -3, 1, 4, 4, 8}, 4));
    }


}