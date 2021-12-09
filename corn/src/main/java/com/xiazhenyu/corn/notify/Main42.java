package com.xiazhenyu.corn.notify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2021/12/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Main42 {


    public static void main(String[] args) throws InterruptedException {

/**        List<Integer> list = new ArrayList<>(10);
 String[] array = new String[10];
 for (int i = 0; 1 < 100; i++) {
 array[i] = String.valueOf(i);
 }**/

        /**   fooA();
         fooB();**/

        Map res = new HashMap<>(16);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            res.put(String.valueOf(i),i);
        }







    }

    //stackOverFlowError
    public static String fooA() {
        fooB();
        return "A";
    }

    public static String fooB() {
        fooA();
        return "B";
    }

    //
}