package com.eptison.job;

import java.util.Date;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * Date: 2025/4/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class QuartzTest {


    public static void main(String[] args) {

        try {
            //任务调度器
            Scheduler scheduler= StdSchedulerFactory.getDefaultScheduler();

            //创建触发器:定义了任务调度的时机

            //SimpleScheduleBuilder对象表示任务的触发规则
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(3).withRepeatCount(10);

            CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder
                    .cronSchedule("0/5 * * * * ?");

            // simpleTrigger
            SimpleTrigger simpleTrigger= TriggerBuilder.newTrigger().withIdentity("simpleTrigger","group")
                    .startAt(new Date(System.currentTimeMillis()+5000))
                    .withSchedule(simpleScheduleBuilder)
                    .build();


            //cronTrigger
            CronTrigger cronTrigger= (CronTrigger) TriggerBuilder.newTrigger()
                    .withIdentity("crontrigger","group")
                    .startAt(DateBuilder.futureDate(5, IntervalUnit.SECOND))
                    .withSchedule(cronScheduleBuilder)
//                    .endAt(DateBuilder.dateOf(14,55,0))
                    .build();

            //创建相关的监听器
            //jobListener
            //监听调度器上的所有任务
//            scheduler.getListenerManager().addJobListener(new MyJobListener());
            //监听某一个group下的任务
//            scheduler.getListenerManager().addJobListener(new MyJobListener(), GroupMatcher.jobGroupEquals("group"));
            //监听某一个特定的job
//            scheduler.getListenerManager().addJobListener(new MyJobListener(), KeyMatcher.keyEquals(new JobKey("myjob","group")));
            //triggerListener
            scheduler.getListenerManager().addTriggerListener(new MyTriggerListener(),KeyMatcher.keyEquals(new TriggerKey("","")));



            //创建任务
            JobDetail myJob= JobBuilder.newJob(MyJob.class)
                    .withIdentity("myjob","group")
                    .usingJobData("age","10")
                    .build();

            //将任务触发器绑定到任务调度
//            scheduler.scheduleJob(myJob, simpleTrigger);
            scheduler.scheduleJob(myJob, cronTrigger);




            //启动调度器
            scheduler.start();


            //关闭调度器
//            scheduler.shutdown(true);

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }


    }

}