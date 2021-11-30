package com.xiazhenyu.corn.notify;

/**
 * Date: 2021/11/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyQueue {

    private String[] data = new String[10];
    private int getIndex = 0;
    private int putIndex = 0;
    private int size = 0;

    public synchronized void put(String element) {
        if (size == data.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data[putIndex] = element;
        ++putIndex;
        if (putIndex == data.length) {
            putIndex = 0;
        }
        ++size;
        notify();
    }


    public synchronized String get() {
        if (size == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String result = data[getIndex];
        ++getIndex;
        if (getIndex == data.length) {
            getIndex = 0;
        }
        --size;
        notify();
        return result;
    }






}