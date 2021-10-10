package com.xiazhenyu.corn;

/**
 * Date: 2021/10/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadFactoryTest {

    public static void main(String[] args) {
        final int COUNT_BITS = Integer.SIZE - 3;
        final int RUNNING = -1 << COUNT_BITS;
        final int SHUTDOWN = 0 << COUNT_BITS;
        final int STOP = 1 << COUNT_BITS;
        final int TIDYING = 2 << COUNT_BITS;
        final int TERMINATED = 3 << COUNT_BITS;
        int c = 10;
        final int CAPACITY = (1 << COUNT_BITS) - 1;
        int workerCountOf = c & CAPACITY;
        System.out.println("RUNNING:" + RUNNING);
        System.out.println("SHUTDOWN:" + SHUTDOWN);
        System.out.println("STOP:" + STOP);
        System.out.println("TIDYING:" + TIDYING);
        System.out.println("TERMINATED:" + TERMINATED);
        System.out.println("CAPACITY:"+CAPACITY);
        System.out.println("workerCountOf:"+workerCountOf);
    }

}