/**
 * TriggerListener.java
 * Created at 2014年2月8日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.scheduler.Listener;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.service.quartz.TriggerListenerService;

/**
 * <p>
 * ClassName: TriggerListener
 * </p>
 * <p>
 * Description: 全局触发器监听
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年2月8日
 * </p>
 */
public class TriggerListener implements org.quartz.TriggerListener {

    /**
     * <p>
     * Field tls: 触发器监听服务
     * </p>
     */
    @Autowired
    private TriggerListenerService tls;

    @Override
    public String getName() {
        return "triggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) { //2
        try {
            tls.saveTriggerFired(trigger, context);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) { //3
        boolean vetoed = false;
        try {
            vetoed = tls.saveVetoJobExecution(trigger, context);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return vetoed;
    }

    @Override
    public void triggerMisfired(Trigger trigger) { //1
        tls.saveTriggerMisfired(trigger);
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context,
            CompletedExecutionInstruction triggerInstructionCode) { //7
        try {
            tls.saveTriggerComplete(trigger, context, triggerInstructionCode);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
