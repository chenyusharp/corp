package com.xiazhenyu.corn.coreTec.synchro;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyObject {

    synchronized public void method() {

        System.out.println("begin method threadName=" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}