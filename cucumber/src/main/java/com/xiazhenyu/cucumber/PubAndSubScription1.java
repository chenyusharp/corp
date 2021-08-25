package com.xiazhenyu.cucumber;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Date: 2021/8/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class PubAndSubScription1 {


    public static void main(String[] args) {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
        Runnable producer = () -> {
            while (true) {
                try {
                    queue.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(producer).start();
        new Thread(producer).start();

        Runnable consumer = () -> {
            while (true) {
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }


}