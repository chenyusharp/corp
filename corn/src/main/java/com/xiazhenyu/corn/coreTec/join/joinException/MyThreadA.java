package com.xiazhenyu.corn.coreTec.join.joinException;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThreadA extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String newString = new String();
            Math.random();
        }
    }
}