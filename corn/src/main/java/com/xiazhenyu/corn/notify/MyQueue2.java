package com.xiazhenyu.corn.notify;

/**
 * Date: 2021/11/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyQueue2 extends MyQueue {


    private String[] data = new String[10];
    private int getIndex = 0;
    private int putIndex = 0;
    private int size = 0;

    @Override
    public synchronized void put(String element) {
        if (size == data.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            put(element);
        } else {
            put0(element);
            notify();
        }
    }

    private void put0(String element) {
        data[putIndex] = element;
        ++putIndex;
        if (putIndex == data.length) {
            putIndex = 0;
        }
        ++size;
    }


    @Override
    public synchronized String get() {
        if (size == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return get();
        } else {
            String result = get0();
            notify();
            return result;
        }
    }

    private String get0() {
        String result = data[getIndex];
        ++getIndex;
        if (getIndex == data.length) {
            getIndex = 0;
        }
        --size;
        return result;
    }
}