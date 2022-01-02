package com.xiazhenyu.corn.coreTec.synchro.exception;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {

    public static void main(String[] args) {
        try {
            Service service = new Service();
            ThreadA threadA = new ThreadA(service);
            threadA.setName("a");
            threadA.start();

            Thread.sleep(500);

            ThreadB threadB = new ThreadB(service);
            threadB.setName("b");
            threadB.start();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}