package com.xiazhenyu.corn.coreTec.synchro.blck;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Task {

    private String getData1;
    private String getData2;


    public synchronized void doLongTimeTask() {
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            getData1 = "返回值1";
            getData2 = "返回值2";
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}