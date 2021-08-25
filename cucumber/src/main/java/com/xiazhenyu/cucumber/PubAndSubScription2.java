package com.xiazhenyu.cucumber;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 2021/8/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class PubAndSubScription2 {


    public static void main(String[] args) {
        MyBlockingQueueForCondition queue = new MyBlockingQueueForCondition(10);

        Runnable producer = () -> {
//            while (true) {
            try {
                queue.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            }
        };

        new Thread(producer).start();
        new Thread(producer).start();

        Runnable consumer = () -> {
//            while (true) {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}

class MyBlockingQueueForCondition {

    private Queue queue;
    private int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    public int i = 1;

    public MyBlockingQueueForCondition(int size) {
        this.max = size;
        queue = new LinkedList();
    }

    public void put(Object o) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
            }
            queue.add(o);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            if (i != 0) {
                queue.remove();
                queue.remove();
            }
            i--;
            while (queue.size() == 0) {
                notEmpty.await();
            }
            Object item = queue.remove();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }


}