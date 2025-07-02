package com.eptison.job;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

/**
 * Date: 2025/4/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyTriggerListener implements TriggerListener {

    @Override
    public String getName() {
        return "MyTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println("监听器触发");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        trigger.getMisfireInstruction();
        System.out.println("监听器错失触发");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {
        System.out.println("监听器触发完成");
    }
}