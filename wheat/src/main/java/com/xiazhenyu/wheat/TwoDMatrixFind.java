package com.xiazhenyu.wheat;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 12:23 上午 2021/6/2
 **/
public class TwoDMatrixFind {


    public boolean find(int target, int[][] array) {
        int row = array.length - 1;
        int column = 0;

        while ((row >= 0) && (column < array[0].length)) {
            if (array[row][column] > target) {
                row--;
            } else if (array[row][column] < target) {
                column++;
            } else {
                return true;
            }
        }
        return false;
    }

}
