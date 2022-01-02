package com.xiazhenyu.corn.coreTec.communication.wait.wait;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {

    public static void main(String[] args) {
        MyService myService = new MyService();

        for (int i = 0; i < 10; i++) {
            MyThreadA myThreadA = new MyThreadA(myService);
            myThreadA.start();
        }

        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadB.start();
    }

}