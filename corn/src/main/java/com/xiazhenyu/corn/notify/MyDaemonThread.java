package com.xiazhenyu.corn.notify;

/**
 * Date: 2021/12/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyDaemonThread extends Thread {


    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}