package com.xiazhenyu.corn.notify;

import java.util.Random;

/**
 * Date: 2021/11/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ConsumerThread extends Thread {


    private final MyQueue myQueue;
    private final Random random = new Random();

    public ConsumerThread(MyQueue myQueue) {
        this.myQueue = myQueue;
    }


    @Override
    public void run() {
        while (true){
            String s=myQueue.get();
            System.out.println("\t\t 消费元素:"+s);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}