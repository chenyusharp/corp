package com.xiazhenyu.wheat;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 12:27 上午 2021/6/7
 **/
public class ReOrderArray {


    public void reOrderArray(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return;
        }
        int oddCount = 0, oddBegin = 0;
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 1) {
                oddCount++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 1) {
                newArray[oddBegin++] = array[i];
            } else {
                newArray[oddCount++] = array[i];
            }

        }
        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }


    }

}
