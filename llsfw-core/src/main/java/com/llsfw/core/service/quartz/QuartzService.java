/**
 * QuartzService.java
 * Created at 2013-12-20
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.quartz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.jdbcjobstore.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.llsfw.core.model.expand.PageResult;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: QuartzService
 * </p>
 * <p>
 * Description: 计划任务服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月20日
 * </p>
 */
@Service
public class QuartzService extends BaseService {

    /**
     * <p>
     * Field s: Scheduler服务
     * </p>
     */
    @Autowired
    @Qualifier("clusterQuartzScheduler")
    private SchedulerFactoryBean s;

    /**
     * <p>
     * Description: 返回执行历史清单
     * </p>
     * 
     * @param page 当前页
     * @param rows 每页行数
     * @param execution_status 状态
     * @return 执行历史清单
     * @throws Exception 异常
     */
    public Map<String, Object> loadExecutionHistoryList(int page, int rows, String execution_history_trigger_group,
            String execution_history_trigger_name, String execution_history_job_group,
            String execution_history_job_name, String execution_status) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT  ");
        sb.append(" A.LOGID,  ");
        sb.append(" A.SCHEDULED_FIRE_TIME,  ");
        sb.append(" A.FIRE_TIME,  ");
        sb.append(" A.END_TIME,  ");
        sb.append(" A.JOB_RUN_TIME,  ");
        sb.append(" A.STATUS,  ");
        sb.append(" A.RESULT,  ");
        sb.append(" A.ERROR_MSG,  ");
        sb.append(" A.TRIGGER_NAME,  ");
        sb.append(" A.TRIGGER_GROUP,  ");
        sb.append(" A.JOB_NAME,  ");
        sb.append(" A.JOB_GROUP,  ");
        sb.append(" A.JOB_CLASS,  ");
        sb.append(" A.THREAD_GROUP_NAME,  ");
        sb.append(" A.THREAD_ID,  ");
        sb.append(" A.THREAD_PRIORITY,  ");
        sb.append(" A.SCHEDULED_ID,  ");
        sb.append(" A.SCHEDULED_NAME,  ");
        sb.append(" A.CREATE_DATE  ");
        sb.append(" FROM TT_SCHEDULED_TRIGGER_LOG A WHERE 1=1  ");
        if (!StringUtils.isEmpty(execution_history_trigger_group)) {
            sb.append(" AND A.TRIGGER_GROUP='" + execution_history_trigger_group + "' ");
        }
        if (!StringUtils.isEmpty(execution_history_trigger_name)) {
            sb.append(" AND A.TRIGGER_NAME='" + execution_history_trigger_name + "' ");
        }
        if (!StringUtils.isEmpty(execution_history_job_group)) {
            sb.append(" AND A.JOB_GROUP='" + execution_history_job_group + "' ");
        }
        if (!StringUtils.isEmpty(execution_history_job_name)) {
            sb.append(" AND A.JOB_NAME='" + execution_history_job_name + "' ");
        }
        if (!StringUtils.isEmpty(execution_status)) {
            sb.append(" AND A.STATUS='" + execution_status + "' ");
        }
        sb.append(" ORDER BY A.CREATE_DATE DESC  ");
        PageResult pr = this.getPrs().pageQuery(sb.toString(), rows, page);
        Map<String, Object> rv = new HashMap<String, Object>();
        rv.put("total", pr.getTotalRecords());
        rv.put("rows", pr.getRecords());
        return rv;
    }

    /**
     * <p>
     * Description: 返回操作历史清单
     * </p>
     * 
     * @return 操作历史清单
     * @throws Exception 异常
     */
    public Map<String, Object> loadSchedulerLogList(int page, int rows) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT LOGID,MSG,CREATE_DATE FROM TT_SCHEDULED_LOG  ");
        sb.append(" ORDER BY CREATE_DATE DESC   ");
        PageResult pr = this.getPrs().pageQuery(sb.toString(), rows, page);
        Map<String, Object> rv = new HashMap<String, Object>();
        rv.put("total", pr.getTotalRecords());
        rv.put("rows", pr.getRecords());
        return rv;
    }

    /**
     * <p>
     * Description: 计划任务操作
     * </p>
     * 
     * @param op 操作
     * @param jn 作业名称
     * @param jg 作业组别
     * @param tn 触发器名称
     * @param tg 触发器组别
     * @return 操作结果
     * @throws Exception 计划任务异常
     */
    public Map<String, Object> schedulerOp(String op, String jn, String jg, String tn, String tg) throws Exception {

        //返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();

        //获得计划任务管理器 
        Scheduler sch = null;
        sch = this.s.getScheduler();

        //设置job_detail_key
        JobKey jk = null;
        if (!StringUtils.isEmpty(jn)) {
            jk = new JobKey(jn, jg);
        }

        //设置trigger_key
        TriggerKey tk = null;
        if (!StringUtils.isEmpty(tn)) {
            tk = new TriggerKey(tn, tg);
        }

        if ("unscheduleJob".equals(op)) { //移除trigger
            sch.unscheduleJob(tk);
        } else if ("deleteJob".equals(op)) { //移除job
            sch.deleteJob(jk);
        } else if ("pauseAll".equals(op)) { //暂停所有
            sch.pauseAll();
        } else if ("pauseJob".equals(op)) { //暂停job
            sch.pauseJob(jk);
        } else if ("pauseTrigger".equals(op)) { //暂停trigger
            sch.pauseTrigger(tk);
        } else if ("resumeAll".equals(op)) { //恢复所有
            sch.resumeAll();
        } else if ("resumeJob".equals(op)) { //恢复job
            sch.resumeJob(jk);
        } else if ("resumeTrigger".equals(op)) { //恢复trigger
            sch.resumeTrigger(tk);
        } else if ("triggerJob".equals(op)) { //触发job
            sch.triggerJob(jk);
        } else {
            rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.FAIL);
            rv.put(com.llsfw.core.common.Constants.MESSAGE, "未知操作");
            return rv;
        }
        rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.SUCCESS);
        return rv;
    }

    /**
     * <p>
     * Description: 清除计划任务数据
     * </p>
     * 
     * @param pswd 操作密码
     * @return 操作结果
     * @throws SchedulerException 计划任务异常
     */
    public Map<String, Object> schedulerClear(String pswd) throws SchedulerException {
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        if (com.llsfw.core.common.Constants.SCHEDULER_CLEAR_OP_PSWD.equals(pswd)) {
            this.s.getScheduler().clear();
            rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.SUCCESS);
        } else {
            rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.FAIL);
            rv.put("message", "密码错误");
        }

        return rv;
    }

    /**
     * <p>
     * Description: 返回触发器组别列表
     * </p>
     * 
     * @return 触发器组别列表
     * @throws SchedulerException 计划任务异常
     */
    public List<Map<String, Object>> getTriggerGroupNames() throws SchedulerException {
        List<String> l = null;
        l = this.s.getScheduler().getTriggerGroupNames();
        List<Map<String, Object>> rv = null;
        rv = new ArrayList<Map<String, Object>>();
        if (!CollectionUtils.isEmpty(l)) {
            for (String item : l) {
                Map<String, Object> m = null;
                m = new HashMap<String, Object>();
                m.put("TRIGGER_GROUP_NAME", item);
                rv.add(m);
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 返回作业组别列表
     * </p>
     * 
     * @return 组别列表
     * @throws SchedulerException 计划任务异常
     */
    public List<Map<String, Object>> getJobGroupNames() throws SchedulerException {
        List<String> l = null;
        l = this.s.getScheduler().getJobGroupNames();
        List<Map<String, Object>> rv = null;
        rv = new ArrayList<Map<String, Object>>();
        if (!CollectionUtils.isEmpty(l)) {
            for (String item : l) {
                Map<String, Object> m = null;
                m = new HashMap<String, Object>();
                m.put("JOB_GROUP_NAME", item);
                rv.add(m);
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 启动任务
     * </p>
     * 
     * @param seconds 延迟时间(秒),大于0,则为延迟启动,小于等于0,则为立即启动
     * @return 操作结果
     * @throws SchedulerException 计划任务异常
     */
    public Map<String, Object> schedulerStart(int seconds) throws SchedulerException {
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        if (seconds > 0) {
            this.s.getScheduler().startDelayed(seconds);
        } else {
            this.s.getScheduler().start();
        }
        rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.SUCCESS);
        return rv;
    }

    /**
     * <p>
     * Description: 添加作业
     * </p>
     * 
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @param jClass 作业类
     * @param jDesc 作业描述
     * @param jobShouldRecover 遗漏恢复
     * @param jobDurability 是否耐用
     * @return 操作结果
     * @throws Exception 计划任务异常
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> addJobDetail(String jName, String jGroup, String jClass, String jDesc,
            boolean jobShouldRecover, boolean jobDurability) throws Exception {
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();

        //获得计划任务管理器 
        Scheduler sch = null;
        sch = this.s.getScheduler();

        //设置jobkey
        JobKey jk = null;
        jk = new JobKey(jName, jGroup);

        //初始化jobdetail
        JobDetail jobDetail = null;
        jobDetail = JobBuilder.newJob((Class<Job>) Class.forName(jClass)).withIdentity(jk).withDescription(jDesc)
                .requestRecovery(jobShouldRecover).storeDurably(jobDurability).build();

        //添加job
        sch.addJob(jobDetail, true, !jobDurability);

        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 添加cron触发器
     * </p>
     * 
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @param tName 触发器名称
     * @param tGroup 触发器组别
     * @param cronExpression cron表达式
     * @param priority 优先级
     * @param triggerStartTime 开始日期
     * @param triggerEndTime 结束日期
     * @return 操作结果
     * @throws Exception 计划任务异常
     */
    public Map<String, Object> addCronTrigger(String jName, String jGroup, String tName, String tGroup,
            String cronExpression, int priority, Date triggerStartTime, Date triggerEndTime) throws Exception {
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();

        //获得计划任务管理器
        Scheduler sch = null;
        sch = this.s.getScheduler();

        //设置jobkey
        JobKey jk = null;
        jk = new JobKey(jName, jGroup);

        //判断jobDetail
        if (!sch.checkExists(jk)) {
            rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.FAIL);
            rv.put("message", "作业不存在");
            return rv;
        }

        //设置triggerkey
        TriggerKey tk = null;
        tk = new TriggerKey(tName, tGroup);

        //获得simpleTrigger
        Trigger cronTrigger = null;
        cronTrigger = TriggerBuilder.newTrigger()
        //触发器标识
                .withIdentity(tk)
                //优先级
                .withPriority(priority)
                //如果没有开始时间则为当前时间
                .startAt(triggerStartTime == null ? new Date() : triggerStartTime)
                //结束时间,可以为空
                .endAt(triggerEndTime)
                //针对的作业
                .forJob(jk)
                //cron触发器详情
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                //构建
                .build();

        if (sch.checkExists(tk)) {
            Trigger t = sch.getTrigger(tk);
            if (jk.getName().equals(t.getJobKey().getName()) && jk.getGroup().equals(t.getJobKey().getGroup())) {
                sch.rescheduleJob(tk, cronTrigger);
            } else {
                rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.FAIL);
                rv.put("message", "此触发器已经关联至其他的作业,无法修改");
                return rv;
            }
        } else {
            sch.scheduleJob(cronTrigger);
        }

        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 添加简单触发器
     * </p>
     * 
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @param tName 触发器名称
     * @param tGroup 触发器组别
     * @param triggerRepeatCount 重复次数
     * @param intervalInMilliseconds 重复间隔
     * @param priority 优先级
     * @param triggerStartTime 开始时间
     * @param triggerEndTime 结束时间
     * @return 操作结果
     * @throws Exception 计划任务异常
     */
    public Map<String, Object> addSimpleTirgger(String jName, String jGroup, String tName, String tGroup,
            int triggerRepeatCount, long intervalInMilliseconds, int priority, Date triggerStartTime,
            Date triggerEndTime) throws Exception {
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();

        //获得计划任务管理器
        Scheduler sch = null;
        sch = this.s.getScheduler();

        //设置jobkey
        JobKey jk = null;
        jk = new JobKey(jName, jGroup);

        //判断jobDetail
        if (!sch.checkExists(jk)) {
            rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.FAIL);
            rv.put("message", "作业不存在");
            return rv;
        }

        //设置triggerkey
        TriggerKey tk = null;
        tk = new TriggerKey(tName, tGroup);

        //获得simpleTrigger
        Trigger simpleTrigger = null;
        simpleTrigger = TriggerBuilder.newTrigger()
        //触发器标识
                .withIdentity(tk)
                //优先级
                .withPriority(priority)
                //如果没有开始时间则为当前时间
                .startAt(triggerStartTime == null ? new Date() : triggerStartTime)
                //结束时间,可以为空
                .endAt(triggerEndTime)
                //针对的作业
                .forJob(jk)
                //简单触发器详情
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                //重复次数,-1为无限重复
                        .withRepeatCount(triggerRepeatCount)
                        //重复间隔(毫秒)
                        .withIntervalInMilliseconds(intervalInMilliseconds))
                //构建
                .build();

        if (sch.checkExists(tk)) {
            Trigger t = sch.getTrigger(tk);
            if (jk.getName().equals(t.getJobKey().getName()) && jk.getGroup().equals(t.getJobKey().getGroup())) {
                sch.rescheduleJob(tk, simpleTrigger);
            } else {
                rv.put(com.llsfw.core.common.Constants.CODE, com.llsfw.core.common.Constants.FAIL);
                rv.put("message", "此触发器已经关联至其他的作业,无法修改");
                return rv;
            }
        } else {
            sch.scheduleJob(simpleTrigger);
        }

        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 计划任务待机
     * </p>
     * 
     * @return 操作结果
     * @throws SchedulerException 计划任务异常
     */
    public Map<String, Object> schedulerStandby() throws SchedulerException {
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        this.s.getScheduler().standby();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 停止计划任务
     * </p>
     * 
     * @param waitForJobsToComplete 是否等待作业完成 true:等待,false:不等待
     * @return 操作结果
     * @throws SchedulerException 计划任务异常
     */
    public Map<String, Object> schedulerShutdown(boolean waitForJobsToComplete) throws SchedulerException {
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        this.s.getScheduler().shutdown(waitForJobsToComplete);
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 返回计划任务当前状态
     * </p>
     * 
     * @return 计划任务状态
     * @throws SchedulerException 计划任务异常
     */
    public Map<String, Object> getSchedulerRunningStatus() throws SchedulerException {
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("isShutdown", this.s.getScheduler().isShutdown());
        rv.put("isInStandbyMode", this.s.getScheduler().isInStandbyMode());
        return rv;
    }

    /**
     * <p>
     * Description: 返回触发器类型
     * </p>
     * 
     * @param sName 计划任务名称
     * @param tName 触发器名称
     * @param tGroup 触发器组别
     * @return 触发器类型
     */
    public String getTriggerType(String sName, String tName, String tGroup) {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT    ");
        sql.append(Constants.COL_TRIGGER_TYPE);
        sql.append(" FROM   ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_TRIGGERS);
        sql.append("  WHERE " + Constants.COL_SCHEDULER_NAME + "= '" + sName + "'  ");
        sql.append(" AND " + Constants.COL_TRIGGER_NAME + "   = '" + tName + "'     ");
        sql.append("  AND  " + Constants.COL_TRIGGER_GROUP + "  = '" + tGroup + "'    ");
        return this.getImqm().queryOneRowOneValue(sql.toString());
    }

    /**
     * <p>
     * Description: 返回作业列表
     * </p>
     * 
     * @return 作业明细列表
     */
    public List<Map<String, Object>> getJobDetail() {
        final StringBuffer SQL = new StringBuffer("");
        SQL.append(" SELECT  ");
        SQL.append(Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        SQL.append(Constants.COL_JOB_NAME + com.llsfw.core.common.Constants.COMMA);
        SQL.append(Constants.COL_JOB_GROUP + com.llsfw.core.common.Constants.COMMA);
        SQL.append(Constants.COL_DESCRIPTION + com.llsfw.core.common.Constants.COMMA);
        SQL.append(Constants.COL_JOB_CLASS + com.llsfw.core.common.Constants.COMMA);
        SQL.append(Constants.COL_IS_DURABLE + com.llsfw.core.common.Constants.COMMA);
        SQL.append(Constants.COL_IS_NONCONCURRENT + com.llsfw.core.common.Constants.COMMA);
        SQL.append(Constants.COL_IS_UPDATE_DATA + com.llsfw.core.common.Constants.COMMA);
        SQL.append(Constants.COL_REQUESTS_RECOVERY);
        //SQL.append(Constants.COL_JOB_DATAMAP);
        SQL.append(" FROM  ");
        SQL.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_JOB_DETAILS);
        return this.getImqm().queryMap(SQL.toString());
    }

    /**
     * <p>
     * Description: 返回作业明细
     * </p>
     * 
     * @param sName 计划任务名称
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @return 作业明细
     */
    public Map<String, Object> getJobDetail(String sName, String jName, String jGroup) {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT  ");
        sql.append(Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_JOB_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_JOB_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_DESCRIPTION + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_JOB_CLASS + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_IS_DURABLE + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_IS_NONCONCURRENT + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_IS_UPDATE_DATA + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_REQUESTS_RECOVERY);
        //sql.append(Constants.COL_JOB_DATAMAP);
        sql.append(" FROM  ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_JOB_DETAILS);
        sql.append(" WHERE " + Constants.COL_SCHEDULER_NAME + "= '" + sName + "'  ");
        sql.append("  AND  " + Constants.COL_JOB_NAME + "  = '" + jName + "'    ");
        sql.append("   AND " + Constants.COL_JOB_GROUP + " =  '" + jGroup + "'    ");
        List<Map<String, Object>> list = null;
        list = this.getImqm().queryMap(sql.toString());
        Map<String, Object> rv = null;
        rv = CollectionUtils.isEmpty(list) ? null : list.get(0);
        return rv;
    }

    /**
     * <p>
     * Description: 返回Cron触发器明细
     * </p>
     * 
     * @param sName 计划任务名称
     * @param tName 触发器名称
     * @param tGroup 触发器组别
     * @return Cron触发器明细
     */
    public Map<String, Object> getCronTriggerDetail(String sName, String tName, String tGroup) {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT  ");
        sql.append(Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_CRON_EXPRESSION + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TIME_ZONE_ID);
        sql.append(" FROM   ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_CRON_TRIGGERS);
        sql.append(" WHERE " + Constants.COL_SCHEDULER_NAME + "= '" + sName + "'  ");
        sql.append(" AND " + Constants.COL_TRIGGER_NAME + " = '" + tName + "'   ");
        sql.append(" AND " + Constants.COL_TRIGGER_GROUP + " = '" + tGroup + "'   ");
        List<Map<String, Object>> list = null;
        list = this.getImqm().queryMap(sql.toString());
        Map<String, Object> rv = null;
        rv = CollectionUtils.isEmpty(list) ? null : list.get(0);
        return rv;
    }

    /**
     * <p>
     * Description: 返回cron触发器列表
     * </p>
     * 
     * @param sName 计划任务名称
     * @param jName 作业名称
     * @param jGroup 作业组别
     * 
     * @return cron触发器列表
     */
    public List<Map<String, Object>> getCronTriggerList(String sName, String jName, String jGroup) {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT  ");
        sql.append("A." + Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_TRIGGER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_TRIGGER_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_CRON_EXPRESSION + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_TIME_ZONE_ID + "");
        sql.append(" FROM  ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_CRON_TRIGGERS + " .A");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_TRIGGERS + " .B");
        sql.append(" WHERE  ");
        sql.append(" A." + Constants.COL_SCHEDULER_NAME + "=B." + Constants.COL_SCHEDULER_NAME + "  ");
        sql.append(" AND A." + Constants.COL_TRIGGER_NAME + "=B." + Constants.COL_TRIGGER_NAME + "  ");
        sql.append(" AND A." + Constants.COL_TRIGGER_GROUP + "=B." + Constants.COL_TRIGGER_GROUP + "  ");
        sql.append(" AND B." + Constants.COL_SCHEDULER_NAME + "='" + sName + "'  ");
        sql.append(" AND B." + Constants.COL_JOB_NAME + "='" + jName + "'  ");
        sql.append(" AND B." + Constants.COL_JOB_GROUP + "='" + jGroup + "'  ");
        sql.append(" AND B." + Constants.COL_TRIGGER_TYPE + "='CRON'  ");
        return this.getImqm().queryMap(sql.toString());
    }

    /**
     * <p>
     * Description: 根据作业返回简单触发器清单
     * </p>
     * 
     * @param sName 计划任务名称
     * @param jName 作业名称
     * @param jGroup 作业组别
     * 
     * @return 简单触发器清单
     */
    public List<Map<String, Object>> getSimpleTriggerList(String sName, String jName, String jGroup) {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT ");
        sql.append("A." + Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_TRIGGER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_TRIGGER_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_REPEAT_COUNT + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_REPEAT_INTERVAL + com.llsfw.core.common.Constants.COMMA);
        sql.append("A." + Constants.COL_TIMES_TRIGGERED + "");
        sql.append(" FROM ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_SIMPLE_TRIGGERS + " A,");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_TRIGGERS + " B,");
        sql.append(" WHERE  ");
        sql.append(" A." + Constants.COL_SCHEDULER_NAME + "=B." + Constants.COL_SCHEDULER_NAME + "  ");
        sql.append(" AND A." + Constants.COL_TRIGGER_NAME + "=B." + Constants.COL_TRIGGER_NAME + "  ");
        sql.append(" AND A." + Constants.COL_TRIGGER_GROUP + "=B." + Constants.COL_TRIGGER_GROUP + "  ");
        sql.append(" AND B." + Constants.COL_SCHEDULER_NAME + "='" + sName + "'  ");
        sql.append(" AND B." + Constants.COL_JOB_NAME + "='" + jName + "'  ");
        sql.append(" AND B." + Constants.COL_JOB_GROUP + "='" + jGroup + "'  ");
        sql.append(" AND B." + Constants.COL_TRIGGER_TYPE + "='SIMPLE'  ");
        return this.getImqm().queryMap(sql.toString());
    }

    /**
     * <p>
     * Description: 返回简单触发器明细
     * </p>
     * 
     * @param sName 计划任务名称
     * @param tName 触发器名称
     * @param tGroup 触发器组别
     * @return 简单触发器明细
     */
    public Map<String, Object> getSimpleTriggerDetail(String sName, String tName, String tGroup) {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT ");
        sql.append(Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_REPEAT_COUNT + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_REPEAT_INTERVAL + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TIMES_TRIGGERED + "");
        sql.append(" FROM ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_SIMPLE_TRIGGERS);
        sql.append(" WHERE " + Constants.COL_SCHEDULER_NAME + " = '" + sName + "'   ");
        sql.append(" AND " + Constants.COL_TRIGGER_NAME + " = '" + tName + "'   ");
        sql.append(" AND " + Constants.COL_TRIGGER_GROUP + " = '" + tGroup + "'   ");
        List<Map<String, Object>> list = null;
        list = this.getImqm().queryMap(sql.toString());
        Map<String, Object> rv = null;
        rv = CollectionUtils.isEmpty(list) ? null : list.get(0);
        return rv;
    }

    /**
     * <p>
     * Description: 返回计划任务状态列表
     * </p>
     * 
     * @return 计划任务状态列表
     */
    public List<Map<String, Object>> getschedulerState() {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT ");
        sql.append(Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_INSTANCE_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_LAST_CHECKIN_TIME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_CHECKIN_INTERVAL);
        sql.append(" FROM ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_SCHEDULER_STATE);
        List<Map<String, Object>> rv = this.getImqm().queryMap(sql.toString());
        if (!CollectionUtils.isEmpty(rv)) {
            for (int i = 0; i < rv.size(); i++) {
                Object lct = rv.get(i).get(Constants.COL_LAST_CHECKIN_TIME);
                if (lct instanceof BigDecimal) {
                    BigDecimal last_ckeckin_time = (BigDecimal) lct;
                    rv.get(i)
                            .put(Constants.COL_LAST_CHECKIN_TIME,
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(last_ckeckin_time
                                            .longValue())));
                } else if (lct instanceof Long) {
                    Long last_ckeckin_time = (Long) lct;
                    rv.get(i).put(Constants.COL_LAST_CHECKIN_TIME,
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(last_ckeckin_time)));
                }

            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获取当前正在执行的任务列表
     * </p>
     * 
     * @return 正在执行的任务列表
     */
    public List<Map<String, Object>> getFiredTriggers() {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT ");
        sql.append(Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_ENTRY_ID + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_INSTANCE_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_FIRED_TIME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_SCHED_TIME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_PRIORITY + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_ENTRY_STATE + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_JOB_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_JOB_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_IS_NONCONCURRENT + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_REQUESTS_RECOVERY);
        sql.append(" FROM ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_FIRED_TRIGGERS);
        return this.getImqm().queryMap(sql.toString());
    }

    /**
     * <p>
     * Description: 获取计划任务列表
     * </p>
     * 
     * @param tNameSearch 触发器名称
     * @param tGroupSearch 触发器组别
     * @param jNameSearch 作业名称
     * @param jGroupSearch 作业组别
     * @return 计划任务列表
     */
    public List<Map<String, Object>> getTriggers(String tNameSearch, String tGroupSearch, String jNameSearch,
            String jGroupSearch, String sort, String order) {
        StringBuffer sql = null;
        sql = new StringBuffer("");
        sql.append(" SELECT ");
        sql.append(Constants.COL_SCHEDULER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_JOB_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_JOB_GROUP + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_DESCRIPTION + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_NEXT_FIRE_TIME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_PREV_FIRE_TIME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_PRIORITY + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_STATE + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_TRIGGER_TYPE + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_START_TIME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_END_TIME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_CALENDAR_NAME + com.llsfw.core.common.Constants.COMMA);
        sql.append(Constants.COL_MISFIRE_INSTRUCTION);
        //sql.append(Constants.COL_JOB_DATAMAP);
        sql.append(" FROM ");
        sql.append(Constants.DEFAULT_TABLE_PREFIX + Constants.TABLE_TRIGGERS);
        sql.append(" WHERE 1=1 ");
        if (!StringUtils.isEmpty(tNameSearch)) {
            sql.append(" AND UPPER(" + Constants.COL_TRIGGER_NAME + ") LIKE UPPER('%" + tNameSearch + "%') ");
        }
        if (!StringUtils.isEmpty(tGroupSearch)) {
            sql.append(" AND UPPER(" + Constants.COL_TRIGGER_GROUP + ") LIKE UPPER('%" + tGroupSearch + "%') ");
        }
        if (!StringUtils.isEmpty(jNameSearch)) {
            sql.append(" AND UPPER(" + Constants.COL_JOB_NAME + ") LIKE UPPER('%" + jNameSearch + "%') ");
        }
        if (!StringUtils.isEmpty(jGroupSearch)) {
            sql.append(" AND UPPER(" + Constants.COL_JOB_GROUP + ") LIKE UPPER('%" + jGroupSearch + "%') ");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            sql.append(" ORDER BY " + sort + " " + order + " ");
        }
        return this.getImqm().queryMap(sql.toString());
    }
}
