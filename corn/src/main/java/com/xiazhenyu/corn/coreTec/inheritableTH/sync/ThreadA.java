package com.xiazhenyu.corn.coreTec.inheritableTH.sync;

/**
 * Date: 2022/1/4
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
                UserInfo userInfo = Tools.t1.get();
                System.out.println("get userInfo from threadA,value=" + userInfo.getUserName() + " " + userInfo.hashCode());
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}