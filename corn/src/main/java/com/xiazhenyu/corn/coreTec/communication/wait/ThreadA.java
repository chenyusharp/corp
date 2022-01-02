package com.xiazhenyu.corn.coreTec.communication.wait;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {


    private MyList myList;


    public ThreadA(MyList myList) {
        this.myList = myList;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                myList.add();
                System.out.println("add one element");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}