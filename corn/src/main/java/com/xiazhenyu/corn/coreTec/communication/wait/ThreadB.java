package com.xiazhenyu.corn.coreTec.communication.wait;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadB extends Thread {


    private MyList myList;


    public ThreadB(MyList myList) {
        this.myList = myList;
    }


    @Override
    public void run() {
        try {
            while (true) {
                if (myList.size() == 5) {
                    System.out.println("thread B exit");
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}