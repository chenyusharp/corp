package com.xiazhenyu.corn.notify;

/**
 * Date: 2021/11/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Main {


    public static void main(String[] args) {
//        MyQueue myQueue = new MyQueue();
//        ProducerThread producerThread = new ProducerThread(myQueue);
//        ConsumerThread consumerThread = new ConsumerThread(myQueue);
//        producerThread.start();
//        consumerThread.start();

//        MyThread myThread=new MyThread();
//        myThread.start();

        MyDaemonThread myDaemonThread = new MyDaemonThread();
        myDaemonThread.setDaemon(true);

        myDaemonThread.start();

        new MyNotDaemonThread().start();


    }
}