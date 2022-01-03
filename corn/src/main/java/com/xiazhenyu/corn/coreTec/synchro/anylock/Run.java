package com.xiazhenyu.corn.coreTec.synchro.anylock;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) {

        Service service = new Service();

        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        threadA.start();

        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
        threadB.start();
    }


}