package com.xiazhenyu.corn.coreTec.synchro.synNotExtends;

import com.xiazhenyu.corn.coreTec.synchro.exception.Service;
import sun.security.provider.Sun;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {

    private Sub sub;

    public ThreadA(Sub sub) {
        this.sub = sub;
    }

    @Override
    public void run() {
        sub.serviceMethod();
    }
}