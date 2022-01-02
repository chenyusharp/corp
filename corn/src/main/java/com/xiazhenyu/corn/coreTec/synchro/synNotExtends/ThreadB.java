package com.xiazhenyu.corn.coreTec.synchro.synNotExtends;

import com.xiazhenyu.corn.coreTec.synchro.exception.Service;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadB extends Thread {

    private Sub sub;

    public ThreadB(Sub sub) {
        this.sub = sub;
    }

    @Override
    public void run() {
        sub.serviceMethod();
    }
}