package com.eptison.job;

import static org.quartz.impl.jdbcjobstore.StdJDBCConstants.SELECT_NEXT_TRIGGER_TO_ACQUIRE;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Date: 2025/4/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyJob implements Job {


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String time = sdf.format(new Date());

        final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        final String age = (String) jobDataMap.get("age");

        System.out.println("执行时间:" + time);
        System.out.println("年龄:" + age);
    }


    public static void main(String[] args) {
        System.out.println(SELECT_NEXT_TRIGGER_TO_ACQUIRE);
    }
}