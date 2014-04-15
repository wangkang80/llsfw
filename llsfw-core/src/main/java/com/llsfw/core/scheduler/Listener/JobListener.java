/**
 * JobListener.java
 * Created at 2014年2月8日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.scheduler.Listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.service.quartz.TriggerListenerService;

/**
 * <p>
 * ClassName: JobListener
 * </p>
 * <p>
 * Description: 作业监听器
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年2月8日
 * </p>
 */
public class JobListener implements org.quartz.JobListener {

    /**
     * <p>
     * Field tls: 触发器监听服务
     * </p>
     */
    @Autowired
    private TriggerListenerService tls;

    @Override
    public String getName() {
        return "jobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) { //4
        try {
            tls.saveJobToBeExecuted(context);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) { //5
        try {
            tls.saveJobExecutionVetoed(context);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) { //6
        try {
            tls.saveJobWasExecuted(context, jobException);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
