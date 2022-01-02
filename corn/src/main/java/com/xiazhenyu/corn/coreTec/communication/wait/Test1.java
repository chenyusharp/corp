package com.xiazhenyu.corn.coreTec.communication.wait;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test1 {

    public static void main(String[] args) {

        try {
            String newString = new String("");
            synchronized (newString) {
                System.out.println("start");
                newString.wait();
                System.out.println("end");
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}