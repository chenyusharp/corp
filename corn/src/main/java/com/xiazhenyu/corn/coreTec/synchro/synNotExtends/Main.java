package com.xiazhenyu.corn.coreTec.synchro.synNotExtends;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Main {

    synchronized public void serviceMethod() {

        try {
            System.out.println("from main,begin threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("from main,end threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}