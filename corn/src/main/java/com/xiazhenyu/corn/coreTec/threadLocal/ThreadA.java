package com.xiazhenyu.corn.coreTec.threadLocal;

import com.xiazhenyu.corn.coreTec.threadLocal.ThreadLocalNotExtends.Tools;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {


    @Override
    public void run() {

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("get value from thread:" + ThreadLocalNotExtends.getInstance().get());
                Thread.sleep(100);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}