/**
 * TriggerListenerService.java
 * Created at 2014年2月8日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.service.quartz;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.Constants;
import com.llsfw.core.common.SystemParam;
import com.llsfw.core.mapper.standard.TtScheduledTriggerLogMapper;
import com.llsfw.core.model.standard.TtScheduledTriggerLog;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: TriggerListenerService
 * </p>
 * <p>
 * Description: 触发器监听服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年2月8日
 * </p>
 */
@Service
public class TriggerListenerService extends BaseService {

    /**
     * <p>
     * Field tstlm: 触发器监听mapper
     * </p>
     */
    @Autowired
    private TtScheduledTriggerLogMapper tstlm;

    /**
     * <p>
     * Description: 保存未正常触发的记录
     * </p>
     * 
     * @param trigger 触发器
     */
    public void saveTriggerMisfired(Trigger trigger) { //1
        TtScheduledTriggerLog tstl = new TtScheduledTriggerLog();
        tstl.setLogid(UUID.randomUUID().toString());
        tstl.setScheduledFireTime(null);
        tstl.setFireTime(null);
        tstl.setEndTime(null);
        tstl.setJobRunTime(null);
        tstl.setStatus("misfired");
        tstl.setResult(null);
        tstl.setErrorMsg(null);
        tstl.setTriggerName(trigger.getKey().getName());
        tstl.setTriggerGroup(trigger.getKey().getGroup());
        tstl.setJobName(trigger.getJobKey().getName());
        tstl.setJobGroup(trigger.getJobKey().getGroup());
        tstl.setJobClass(null);
        tstl.setThreadGroupName(Thread.currentThread().getThreadGroup().getName());
        tstl.setThreadId(Thread.currentThread().getId() + "");
        tstl.setThreadName(Thread.currentThread().getName());
        tstl.setThreadPriority(Thread.currentThread().getPriority() + "");
        tstl.setScheduledId(null);
        tstl.setScheduledName(null);
        tstl.setCreateDate(new Date());
        tstlm.insertSelective(tstl);
    }

    /**
     * <p>
     * Description: 准备触发
     * </p>
     * 
     * @param trigger 触发器
     * @param context 执行上下文
     * @throws SchedulerException 计划任务异常
     */
    public void saveTriggerFired(Trigger trigger, JobExecutionContext context) throws SchedulerException { //2

        //是否详细记录执行历史(true记录,false不记录)
        String dr = this.getPs().getServerParamter(SystemParam.DETAILED_RECORDS.name());
        boolean detailedRecords = null == dr ? false : new Boolean(dr);
        if (!detailedRecords) {
            return;
        }

        //获得计划任务实例
        Scheduler s = context.getScheduler();

        //删除当前ID已经有的记录
        tstlm.deleteByPrimaryKey(context.getFireInstanceId());

        //写入新的记录
        TtScheduledTriggerLog tstl = new TtScheduledTriggerLog();
        tstl.setLogid(context.getFireInstanceId());
        tstl.setScheduledFireTime(context.getScheduledFireTime());
        tstl.setFireTime(context.getFireTime());
        tstl.setEndTime(null);
        tstl.setJobRunTime(null);
        tstl.setStatus("triggering");
        tstl.setResult(null);
        tstl.setErrorMsg(null);
        tstl.setTriggerName(context.getTrigger().getKey().getName());
        tstl.setTriggerGroup(context.getTrigger().getKey().getGroup());
        tstl.setJobName(context.getJobDetail().getKey().getName());
        tstl.setJobGroup(context.getJobDetail().getKey().getGroup());
        tstl.setJobClass(context.getJobDetail().getJobClass().getName());
        tstl.setThreadGroupName(Thread.currentThread().getThreadGroup().getName());
        tstl.setThreadId(Thread.currentThread().getId() + "");
        tstl.setThreadName(Thread.currentThread().getName());
        tstl.setThreadPriority(Thread.currentThread().getPriority() + "");
        tstl.setScheduledId(s.getSchedulerInstanceId());
        tstl.setScheduledName(s.getSchedulerName());
        tstl.setCreateDate(new Date());
        tstlm.insertSelective(tstl);
    }

    /**
     * <p>
     * Description: 判断是否否决
     * </p>
     * 
     * @param trigger 触发器
     * @param context 上下文
     * @return 是否否决
     * @throws SchedulerException 计划任务异常
     */
    public boolean saveVetoJobExecution(Trigger trigger, JobExecutionContext context) throws SchedulerException { //3
        //是否否决
        boolean vetoed = false;

        //是否详细记录执行历史(true记录,false不记录)
        String dr = this.getPs().getServerParamter(SystemParam.DETAILED_RECORDS.name());
        boolean detailedRecords = null == dr ? false : new Boolean(dr);
        if (!detailedRecords) {
            return vetoed;
        }

        //获得计划任务实例
        Scheduler s = context.getScheduler();

        //删除当前ID已经有的记录
        tstlm.deleteByPrimaryKey(context.getFireInstanceId());

        //写入新的记录
        TtScheduledTriggerLog tstl = new TtScheduledTriggerLog();
        tstl.setLogid(context.getFireInstanceId());
        tstl.setScheduledFireTime(context.getScheduledFireTime());
        tstl.setFireTime(context.getFireTime());
        tstl.setEndTime(null);
        tstl.setJobRunTime(null);
        tstl.setStatus("vetoed(" + vetoed + ")");
        tstl.setResult(null);
        tstl.setErrorMsg(null);
        tstl.setTriggerName(context.getTrigger().getKey().getName());
        tstl.setTriggerGroup(context.getTrigger().getKey().getGroup());
        tstl.setJobName(context.getJobDetail().getKey().getName());
        tstl.setJobGroup(context.getJobDetail().getKey().getGroup());
        tstl.setJobClass(context.getJobDetail().getJobClass().getName());
        tstl.setThreadGroupName(Thread.currentThread().getThreadGroup().getName());
        tstl.setThreadId(Thread.currentThread().getId() + "");
        tstl.setThreadName(Thread.currentThread().getName());
        tstl.setThreadPriority(Thread.currentThread().getPriority() + "");
        tstl.setScheduledId(s.getSchedulerInstanceId());
        tstl.setScheduledName(s.getSchedulerName());
        tstl.setCreateDate(new Date());
        tstlm.insertSelective(tstl);

        return vetoed;
    }

    /**
     * <p>
     * Description: 准备执行作业
     * </p>
     * 
     * @param context 上下文
     * @throws SchedulerException 计划任务异常
     */
    public void saveJobToBeExecuted(JobExecutionContext context) throws SchedulerException { //4

        //是否详细记录执行历史(true记录,false不记录)
        String dr = this.getPs().getServerParamter(SystemParam.DETAILED_RECORDS.name());
        boolean detailedRecords = null == dr ? false : new Boolean(dr);
        if (!detailedRecords) {
            return;
        }

        //获得计划任务实例
        Scheduler s = context.getScheduler();

        //删除当前ID已经有的记录
        tstlm.deleteByPrimaryKey(context.getFireInstanceId());

        //写入新的记录
        TtScheduledTriggerLog tstl = new TtScheduledTriggerLog();
        tstl.setLogid(context.getFireInstanceId());
        tstl.setScheduledFireTime(context.getScheduledFireTime());
        tstl.setFireTime(context.getFireTime());
        tstl.setEndTime(null);
        tstl.setJobRunTime(null);
        tstl.setStatus("toBeExecuted");
        tstl.setResult(null);
        tstl.setErrorMsg(null);
        tstl.setTriggerName(context.getTrigger().getKey().getName());
        tstl.setTriggerGroup(context.getTrigger().getKey().getGroup());
        tstl.setJobName(context.getJobDetail().getKey().getName());
        tstl.setJobGroup(context.getJobDetail().getKey().getGroup());
        tstl.setJobClass(context.getJobDetail().getJobClass().getName());
        tstl.setThreadGroupName(Thread.currentThread().getThreadGroup().getName());
        tstl.setThreadId(Thread.currentThread().getId() + "");
        tstl.setThreadName(Thread.currentThread().getName());
        tstl.setThreadPriority(Thread.currentThread().getPriority() + "");
        tstl.setScheduledId(s.getSchedulerInstanceId());
        tstl.setScheduledName(s.getSchedulerName());
        tstl.setCreateDate(new Date());
        tstlm.insertSelective(tstl);
    }

    /**
     * <p>
     * Description: 作业执行被否决
     * </p>
     * 
     * @param context 上下文
     * @throws SchedulerException 计划任务异常
     */
    public void saveJobExecutionVetoed(JobExecutionContext context) throws SchedulerException { //5(出口)

        //获得计划任务实例
        Scheduler s = context.getScheduler();

        //删除当前ID已经有的记录
        tstlm.deleteByPrimaryKey(context.getFireInstanceId());

        //写入新的记录
        TtScheduledTriggerLog tstl = new TtScheduledTriggerLog();
        tstl.setLogid(context.getFireInstanceId());
        tstl.setScheduledFireTime(context.getScheduledFireTime());
        tstl.setFireTime(context.getFireTime());
        tstl.setEndTime(null);
        tstl.setJobRunTime(null);
        tstl.setStatus("executionVetoed");
        tstl.setResult(null);
        tstl.setErrorMsg(null);
        tstl.setTriggerName(context.getTrigger().getKey().getName());
        tstl.setTriggerGroup(context.getTrigger().getKey().getGroup());
        tstl.setJobName(context.getJobDetail().getKey().getName());
        tstl.setJobGroup(context.getJobDetail().getKey().getGroup());
        tstl.setJobClass(context.getJobDetail().getJobClass().getName());
        tstl.setThreadGroupName(Thread.currentThread().getThreadGroup().getName());
        tstl.setThreadId(Thread.currentThread().getId() + "");
        tstl.setThreadName(Thread.currentThread().getName());
        tstl.setThreadPriority(Thread.currentThread().getPriority() + "");
        tstl.setScheduledId(s.getSchedulerInstanceId());
        tstl.setScheduledName(s.getSchedulerName());
        tstl.setCreateDate(new Date());
        tstlm.insertSelective(tstl);
    }

    /**
     * <p>
     * Description: 作业执行完毕
     * </p>
     * 
     * @param context 上下文
     * @param jobException 执行异常
     * @throws SchedulerException 异常信息
     */
    public void saveJobWasExecuted(JobExecutionContext context, JobExecutionException jobException)
            throws SchedulerException { //6

        //异常信息
        String exceptionDetail = null;

        //如果作业异常,则放入信息到result中
        if (null != jobException) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("status", "error");
            result.put("jobException", jobException);
            context.setResult(result);

            //获得异常信息
            ByteArrayOutputStream buf = null;
            buf = new ByteArrayOutputStream();
            jobException.printStackTrace(new PrintWriter(buf, true));
            String expMessage = null;
            expMessage = buf.toString();
            exceptionDetail = expMessage; // 异常详细信息
            if (exceptionDetail.length() > Constants.EXCEPTION_MSG_LENGTH) {
                exceptionDetail = exceptionDetail.substring(0, Constants.EXCEPTION_MSG_LENGTH);
            }
        }

        //是否详细记录执行历史(true记录,false不记录)
        String dr = this.getPs().getServerParamter(SystemParam.DETAILED_RECORDS.name());
        boolean detailedRecords = null == dr ? false : new Boolean(dr);
        if (!detailedRecords) {
            return;
        }

        //获得计划任务实例
        Scheduler s = context.getScheduler();

        //删除当前ID已经有的记录
        tstlm.deleteByPrimaryKey(context.getFireInstanceId());

        //写入新的记录
        TtScheduledTriggerLog tstl = new TtScheduledTriggerLog();
        tstl.setLogid(context.getFireInstanceId());
        tstl.setScheduledFireTime(context.getScheduledFireTime());
        tstl.setFireTime(context.getFireTime());
        tstl.setEndTime(new Date());
        tstl.setJobRunTime(context.getJobRunTime());
        tstl.setStatus("executed");
        tstl.setResult(null);
        tstl.setErrorMsg(exceptionDetail);
        tstl.setTriggerName(context.getTrigger().getKey().getName());
        tstl.setTriggerGroup(context.getTrigger().getKey().getGroup());
        tstl.setJobName(context.getJobDetail().getKey().getName());
        tstl.setJobGroup(context.getJobDetail().getKey().getGroup());
        tstl.setJobClass(context.getJobDetail().getJobClass().getName());
        tstl.setThreadGroupName(Thread.currentThread().getThreadGroup().getName());
        tstl.setThreadId(Thread.currentThread().getId() + "");
        tstl.setThreadName(Thread.currentThread().getName());
        tstl.setThreadPriority(Thread.currentThread().getPriority() + "");
        tstl.setScheduledId(s.getSchedulerInstanceId());
        tstl.setScheduledName(s.getSchedulerName());
        tstl.setCreateDate(new Date());
        tstlm.insertSelective(tstl);
    }

    /**
     * <p>
     * Description: 触发完成
     * </p>
     * 
     * @param trigger 触发器
     * @param context 上下文
     * @param triggerInstructionCode 状态
     * @throws SchedulerException 计划任务异常
     */
    @SuppressWarnings("unchecked")
    public void saveTriggerComplete(Trigger trigger, JobExecutionContext context,
            CompletedExecutionInstruction triggerInstructionCode) throws SchedulerException { //7(出口)

        //状态和异常信息
        String status = "complete";
        String exceptionDetail = null;

        //获得result
        Object o = context.getResult();
        if (null != o && o instanceof Map) {
            Map<String, Object> result = (Map<String, Object>) o;
            status = result.get("status").toString();
            JobExecutionException jobException = (JobExecutionException) result.get("jobException");

            //获得异常信息
            ByteArrayOutputStream buf = null;
            buf = new ByteArrayOutputStream();
            jobException.printStackTrace(new PrintWriter(buf, true));
            String expMessage = null;
            expMessage = buf.toString();
            exceptionDetail = expMessage;
            if (exceptionDetail.length() > Constants.EXCEPTION_MSG_LENGTH) {
                exceptionDetail = exceptionDetail.substring(0, Constants.EXCEPTION_MSG_LENGTH);
            }
        }

        //获得计划任务实例
        Scheduler s = context.getScheduler();

        //删除当前ID已经有的记录
        tstlm.deleteByPrimaryKey(context.getFireInstanceId());

        //写入新的记录
        TtScheduledTriggerLog tstl = new TtScheduledTriggerLog();
        tstl.setLogid(context.getFireInstanceId());
        tstl.setScheduledFireTime(context.getScheduledFireTime());
        tstl.setFireTime(context.getFireTime());
        tstl.setEndTime(new Date());
        tstl.setJobRunTime(context.getJobRunTime());
        tstl.setStatus(status);
        tstl.setResult(triggerInstructionCode.toString());
        tstl.setErrorMsg(exceptionDetail);
        tstl.setTriggerName(context.getTrigger().getKey().getName());
        tstl.setTriggerGroup(context.getTrigger().getKey().getGroup());
        tstl.setJobName(context.getJobDetail().getKey().getName());
        tstl.setJobGroup(context.getJobDetail().getKey().getGroup());
        tstl.setJobClass(context.getJobDetail().getJobClass().getName());
        tstl.setThreadGroupName(Thread.currentThread().getThreadGroup().getName());
        tstl.setThreadId(Thread.currentThread().getId() + "");
        tstl.setThreadName(Thread.currentThread().getName());
        tstl.setThreadPriority(Thread.currentThread().getPriority() + "");
        tstl.setScheduledId(s.getSchedulerInstanceId());
        tstl.setScheduledName(s.getSchedulerName());
        tstl.setCreateDate(new Date());
        tstlm.insertSelective(tstl);
    }
}
