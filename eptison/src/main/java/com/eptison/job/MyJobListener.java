package com.eptison.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Date: 2025/4/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyJobListener implements JobListener {

    @Override
    public String getName() {
        return "myJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("job开始执行");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("job执行失效");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("job执行成功");
    }
}