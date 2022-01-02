package com.xiazhenyu.corn.coreTec.communication.wait.wait;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyService {

    public void testMethod() {

        try {
            synchronized (this) {
                System.out.println("wait begin");
                wait(5000);
                System.out.println("wait end");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    synchronized public void longTImeSync() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}