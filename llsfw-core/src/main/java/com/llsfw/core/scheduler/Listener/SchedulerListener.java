/**
 * SchedulerListener.java
 * Created at 2014年1月24日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.scheduler.Listener;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.common.Constants;
import com.llsfw.core.service.applog.AppLogService;

/**
 * <p>
 * ClassName: SchedulerListener
 * </p>
 * <p>
 * Description: 计划任务监听器
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月24日
 * </p>
 */
public class SchedulerListener implements org.quartz.SchedulerListener {
    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Description: 返回日志类
     * </p>
     * 
     * @return
     */
    public Logger getLog() {
        return this.log;
    }

    /**
     * <p>
     * Field as: 日志服务
     * </p>
     */
    @Autowired
    private AppLogService als;

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        als.saveScheduledLog("(triggerPaused)" + triggerKey.getName() + "/" + triggerKey.getGroup() + "被暂停了");
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        als.saveScheduledLog("(triggerResumed)" + triggerKey.getName() + "/" + triggerKey.getGroup() + "被恢复了");
    }

    @Override
    public void jobScheduled(Trigger trigger) {
        als.saveScheduledLog("(jobScheduled)" + "作业" + trigger.getJobKey().getName() + "/"
                + trigger.getJobKey().getGroup() + "被触发器" + trigger.getKey().getName() + "/"
                + trigger.getKey().getGroup() + "触发了");
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        als.saveScheduledLog("(jobUnscheduled)" + triggerKey.getName() + "/" + triggerKey.getGroup() + "被移除了");
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        als.saveScheduledLog("(triggerFinalized)" + "作业" + trigger.getJobKey().getName() + "/"
                + trigger.getJobKey().getGroup() + ",触发器" + trigger.getKey().getName() + "/"
                + trigger.getKey().getGroup() + "已经执行完成,后续将不会继续触发");
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        if (StringUtils.isEmpty(triggerGroup)) {
            als.saveScheduledLog("(triggersPaused)" + "触发器组全部被暂停了");
        } else {
            als.saveScheduledLog("(triggersPaused)" + "触发器组" + triggerGroup + "被暂停了");
        }

    }

    @Override
    public void triggersResumed(String triggerGroup) {
        if (StringUtils.isEmpty(triggerGroup)) {
            als.saveScheduledLog("(triggersResumed)" + "触发器组全部被恢复了");
        } else {
            als.saveScheduledLog("(triggersResumed)" + "触发器组" + triggerGroup + "被恢复了");
        }
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        als.saveScheduledLog("(jobAdded)" + "作业" + jobDetail.getKey().getName() + "/" + jobDetail.getKey().getGroup()
                + "被添加了");
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        als.saveScheduledLog("(jobDeleted)" + "作业" + jobKey.getName() + "/" + jobKey.getGroup() + "被删除了");
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        als.saveScheduledLog("(jobPaused)" + "作业" + jobKey.getName() + "/" + jobKey.getGroup() + "被暂停了");
    }

    @Override
    public void jobsPaused(String jobGroup) {
        if (StringUtils.isEmpty(jobGroup)) {
            als.saveScheduledLog("(jobsPaused)" + "作业全部被暂停了");
        } else {
            als.saveScheduledLog("(jobsPaused)" + "作业组" + jobGroup + "被暂停了");
        }

    }

    @Override
    public void jobResumed(JobKey jobKey) {
        als.saveScheduledLog("(jobResumed)" + "作业" + jobKey.getName() + "/" + jobKey.getGroup() + "被恢复了");
    }

    @Override
    public void jobsResumed(String jobGroup) {
        if (StringUtils.isEmpty(jobGroup)) {
            als.saveScheduledLog("(jobsResumed)" + "作业全部被恢复了");
        } else {
            als.saveScheduledLog("(jobsResumed)" + "作业组" + jobGroup + "被恢复了");
        }
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        //获得异常详细信息
        ByteArrayOutputStream buf = null;
        buf = new ByteArrayOutputStream();
        cause.printStackTrace(new PrintWriter(buf, true));
        String expMessage = null;
        expMessage = buf.toString();
        String exceptionDetail = null;
        exceptionDetail = expMessage; // 异常详细信息
        if (exceptionDetail.length() > Constants.EXCEPTION_MSG_LENGTH) {
            exceptionDetail = exceptionDetail.substring(0, Constants.EXCEPTION_MSG_LENGTH);
        }
        als.saveScheduledLog("(schedulerError)" + "计划任务出错:" + msg + "\n" + exceptionDetail);
    }

    @Override
    public void schedulerInStandbyMode() {
        als.saveScheduledLog("(schedulerInStandbyMode)" + "计划任务为待机状态");
    }

    @Override
    public void schedulerStarted() {
        als.saveScheduledLog("(schedulerStarted)" + "计划任务已经启动");
    }

    @Override
    public void schedulerStarting() {
        als.saveScheduledLog("(schedulerStarting)" + "计划任务正在启动中");
    }

    @Override
    public void schedulerShutdown() {
        als.saveScheduledLog("(schedulerShutdown)" + "计划任务已关闭");
    }

    @Override
    public void schedulerShuttingdown() {
        als.saveScheduledLog("(schedulerShuttingdown)" + "计划任务正在关闭中");
    }

    @Override
    public void schedulingDataCleared() {
        als.saveScheduledLog("(schedulingDataCleared)" + "计划任务数据被清除");
    }

}
