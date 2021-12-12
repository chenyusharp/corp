package com.xiazhenyu.corn.notify;

/**
 * Date: 2021/12/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyThread extends Thread {


    @Override
    public void run() {
        int i = 0;
        while (true) {
            boolean interrupted = isInterrupted();
            System.out.println("中断标记：" + interrupted);

            i++;
            if (i > 200) {
                this.interrupt();
                boolean interrupted1 = Thread.interrupted();
                System.out.println("重置中断状态: " + interrupted1);
                interrupted1 = Thread.interrupted();
                System.out.println("reset interrupted status:" + interrupted1);
                interrupted = isInterrupted();
                System.out.println("reset interrupted status:" + interrupted);

                break;
            }


        }
    }

}