package com.xiazhenyu.corn.coreTec.timer;

import java.util.Date;
import java.util.Timer;

/**
 * Date: 2022/1/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        System.out.println("当前时间为:" + time);

        long scheduleTime = time + 10000;
        System.out.println("计划时间为：" + scheduleTime);

        MyTask myTask = new MyTask();
        Timer timer1 = new Timer();
        Thread.sleep(1000);
        timer1.schedule(myTask, new Date(scheduleTime));



    }

}