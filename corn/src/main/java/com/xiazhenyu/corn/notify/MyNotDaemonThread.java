package com.xiazhenyu.corn.notify;

/**
 * Date: 2021/12/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyNotDaemonThread  extends  Thread{


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("not daemon thread");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}