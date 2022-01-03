package com.xiazhenyu.corn.coreTec.inheritableTH.sync;

/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {


    public static void main(String[] args) throws InterruptedException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("xiazhenyu");
        Tools.t1.set(userInfo);
        ThreadA threadA = new ThreadA();
        threadA.start();
        Thread.sleep(2000);
        Tools.t1.get().setUserName("chenyusharp");
    }

}