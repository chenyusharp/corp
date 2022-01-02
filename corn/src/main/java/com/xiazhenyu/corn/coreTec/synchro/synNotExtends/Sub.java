package com.xiazhenyu.corn.coreTec.synchro.synNotExtends;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Sub extends Main {

    @Override
    public  void serviceMethod() {
        try {
            System.out.println(
                    "from sub ,begin threadName = " + Thread.currentThread().getName() + " time = " + System
                            .currentTimeMillis());
            Thread.sleep(3000);
            System.out.println(
                    "from sub,end threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());

            super.serviceMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}