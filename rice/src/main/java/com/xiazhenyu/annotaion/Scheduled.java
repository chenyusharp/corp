package com.xiazhenyu.annotaion;

/**
 * Date: 2023/8/13
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Scheduled {


    @EpScheduled(cron = "3231", name = "scheduled1")
    @EpScheduled(cron = "3232", name = "scheduled2")
    public void testScheduled() {
        System.out.println("定时任务测试");
    }

}