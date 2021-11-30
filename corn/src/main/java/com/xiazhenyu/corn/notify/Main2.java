package com.xiazhenyu.corn.notify;

/**
 * Date: 2021/11/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Main2 {

    public static void main(String[] args) {
        MyQueue2 myQueue2 = new MyQueue2();
        for (int i = 0; i < 3; i++) {
            new ConsumerThread(myQueue2).start();
        }
        for (int i = 0; i < 5; i++) {
            new ProducerThread(myQueue2).start();
        }
    }

}