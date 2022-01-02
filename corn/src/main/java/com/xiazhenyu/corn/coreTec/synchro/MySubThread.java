package com.xiazhenyu.corn.coreTec.synchro;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MySubThread extends Thread {

    @Override
    public void run() {
        Sub sub = new Sub();
        sub.operateISubMethod();
    }
}