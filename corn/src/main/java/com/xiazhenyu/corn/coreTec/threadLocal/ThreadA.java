package com.xiazhenyu.corn.coreTec.threadLocal;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadA extends Thread {


    @Override
    public void run() {

        try {
//            for (int i = 0; i < 10; i++) {
//                System.out.println("get value from thread:" + ThreadLocalNotExtends.getInstance().get());
//                Thread.sleep(100);
//            }
            ThreadLocal threadLocal = new ThreadLocal();
            int num = (int) (Math.random() * 3);
            threadLocal.set(num);
            Thread.sleep(200);
            System.out.println(threadLocal.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}