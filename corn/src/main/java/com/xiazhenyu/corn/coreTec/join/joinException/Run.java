package com.xiazhenyu.corn.coreTec.join.joinException;


/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {

    public static void main(String[] args) {
        try {

            MyThreadB myThreadB = new MyThreadB();
            myThreadB.start();
            Thread.sleep(500);
            ThreadC threadC = new ThreadC(myThreadB);
            threadC.start();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}