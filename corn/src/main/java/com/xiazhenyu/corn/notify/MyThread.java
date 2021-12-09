package com.xiazhenyu.corn.notify;

/**
 * Date: 2021/12/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThread extends Thread {


    @Override
    public void run() {
        while (true) {
            boolean interrupted = isInterrupted();
            System.out.println("中断标记：" + interrupted);

        }
    }

}