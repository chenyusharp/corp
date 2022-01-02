package com.xiazhenyu.corn.coreTec.synchro.synNotExtends;

import com.xiazhenyu.corn.coreTec.synchro.exception.Service;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {

    public static void main(String[] args) {
        Sub sub = new Sub();
        ThreadA threadA = new ThreadA(sub);
        threadA.setName("A");
        threadA.start();

        ThreadB threadB = new ThreadB(sub);
        threadB.setName("B");
        threadB.start();

    }

}