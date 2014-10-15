/**
 * QuartzController.java
 * Created at 2013-12-20
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.quartz;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.jdbcjobstore.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.service.quartz.QuartzService;

/**
 * <p>
 * ClassName: QuartzController
 * </p>
 * <p>
 * Description: 计划任务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月20日
 * </p>
 */
@Controller
@RequestMapping("quartzController")
public class QuartzController extends BaseController {

    /**
     * <p>
     * Field qs: 计划任务服务
     * </p>
     */
    @Autowired
    private QuartzService qs;

    /**
     * <p>
     * Description: 获得作业的dataMap
     * </p>
     * 
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @return dataMap
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("getJobDetailDataMap")
    @ResponseBody
    public Map<String, Object> getJobDetailDataMap(String jName, String jGroup) throws SchedulerException {
        return this.qs.getJobDetailDataMap(jName, jGroup);
    }

    /**
     * <p>
     * Description: 跳转到cron生成器界面
     * </p>
     * 
     * @return crom生成器界面
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("toCronTriggersGeneratePage")
    public String toCronTriggersGeneratePage() {
        return "llsfw/quartz/cronTriggersGenerate";
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("getCronTriggerList")
    @ResponseBody
    public List<Map<String, Object>> getCronTriggerList(String sName, String jName, String jGroup) {
        return this.qs.getCronTriggerList(sName, jName, jGroup);
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("addCronTrigger")
    @ResponseBody
    public Map<String, Object> addCronTrigger(String jName, String jGroup, String tName, String tGroup,
            String cronExpression, int priority,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date triggerStartTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date triggerEndTime) throws Exception {
        return this.qs.addCronTrigger(jName, jGroup, tName, tGroup, cronExpression, priority, triggerStartTime,
                triggerEndTime);
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("schedulerOp")
    @ResponseBody
    public Map<String, Object> schedulerOp(String op, String jn, String jg, String tn, String tg) throws Exception {
        return this.qs.schedulerOp(op, jn, jg, tn, tg);
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
     * @param jobDurability 是否持久
     * @param jobDetailDataMapHid dataMap
     * @return 操作结果
     * @throws Exception 计划任务异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("addJobDetail")
    @ResponseBody
    public Map<String, Object> addJobDetail(String jName, String jGroup, String jClass, String jDesc,
            boolean jobShouldRecover, boolean jobDurability, String jobDetailDataMapHid) throws Exception {
        return this.qs.addJobDetail(jName, jGroup, jClass, jDesc, jobShouldRecover, jobDurability, jobDetailDataMapHid);
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("schedulerClear")
    @ResponseBody
    public Map<String, Object> schedulerClear(String pswd) throws SchedulerException {
        return this.qs.schedulerClear(pswd);
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("getSimpleTriggerList")
    @ResponseBody
    public List<Map<String, Object>> getSimpleTriggerList(String sName, String jName, String jGroup) {
        return this.qs.getSimpleTriggerList(sName, jName, jGroup);
    }

    /**
     * <p>
     * Description: 返回触发器组别列表
     * </p>
     * 
     * @return 触发器组别列表
     * @throws SchedulerException 计划任务异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("getTriggerGroupNames")
    @ResponseBody
    public List<Map<String, Object>> getTriggerGroupNames() throws SchedulerException {
        return this.qs.getTriggerGroupNames();
    }

    /**
     * <p>
     * Description: 校验类是否存在,并且是否实现org.quartz.Job接口
     * </p>
     * 
     * @param jClass 类型
     * @return 是否通过 true:通过,false:不通过
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("jClassCheck")
    @ResponseBody
    public boolean jClassCheck(String jClass) {
        try {
            Job j = null;
            j = (Job) Class.forName(jClass).newInstance();
            this.log.debug(j.getClass().toString());
            return true;
        } catch (Throwable e) {
            return false;
        }

    }

    /**
     * <p>
     * Description: 返回作业组别列表
     * </p>
     * 
     * @return 组别列表
     * @throws SchedulerException 计划任务异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("getJobGroupNames")
    @ResponseBody
    public List<Map<String, Object>> getJobGroupNames() throws SchedulerException {
        return this.qs.getJobGroupNames();
    }

    /**
     * <p>
     * Description: 返回作业列表
     * </p>
     * 
     * @return 作业明细列表
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("getJobDetail")
    @ResponseBody
    public List<Map<String, Object>> getJobDetail() {
        return this.qs.getJobDetail();
    }

    /**
     * <p>
     * Description: 跳转到作业添加页面
     * </p>
     * 
     * @param request 请求参数
     * @return 作业添加页面
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("toAddJobDetail")
    public String toAddJobDetail(HttpServletRequest request) {
        request.setAttribute("defaultGroup", Scheduler.DEFAULT_GROUP);
        return "llsfw/quartz/addJobDetail";
    }

    /**
     * <p>
     * Description: 跳转到cron触发器作业添加页面
     * </p>
     * 
     * @param request 请求参数
     * @return simple触发器作业添加页面
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("toAddCronTrigger")
    public String toAddCronTrigger(HttpServletRequest request) {
        request.setAttribute("defaultPriority", Trigger.DEFAULT_PRIORITY);
        request.setAttribute("defaultGroup", Scheduler.DEFAULT_GROUP);
        return "llsfw/quartz/addCronTrigger";
    }

    /**
     * <p>
     * Description: 跳转到simple触发器作业添加页面
     * </p>
     * 
     * @param request 请求参数
     * @return simple触发器作业添加页面
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("toAddSimpleTrigger")
    public String toAddSimpleTrigger(HttpServletRequest request) {
        request.setAttribute("defaultPriority", Trigger.DEFAULT_PRIORITY);
        request.setAttribute("defaultGroup", Scheduler.DEFAULT_GROUP);
        request.setAttribute("REPEAT_INDEFINITELY", SimpleTrigger.REPEAT_INDEFINITELY);
        return "llsfw/quartz/addSimpleTrigger";
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("addSimpleTirgger")
    @ResponseBody
    public Map<String, Object> addSimpleTirgger(String jName, String jGroup, String tName, String tGroup,
            int triggerRepeatCount, long intervalInMilliseconds, int priority,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date triggerStartTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date triggerEndTime) throws Exception {
        return this.qs.addSimpleTirgger(jName, jGroup, tName, tGroup, triggerRepeatCount, intervalInMilliseconds,
                priority, triggerStartTime, triggerEndTime);
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("schedulerStart")
    @ResponseBody
    public Map<String, Object> schedulerStart(int seconds) throws SchedulerException {
        return this.qs.schedulerStart(seconds);
    }

    /**
     * <p>
     * Description: 计划任务待机
     * </p>
     * 
     * @return 操作结果
     * @throws SchedulerException 计划任务异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("schedulerStandby")
    @ResponseBody
    public Map<String, Object> schedulerStandby() throws SchedulerException {
        return this.qs.schedulerStandby();
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("schedulerShutdown")
    @ResponseBody
    public Map<String, Object> schedulerShutdown(boolean waitForJobsToComplete) throws SchedulerException {
        return this.qs.schedulerShutdown(waitForJobsToComplete);
    }

    /**
     * <p>
     * Description: 跳转到执行历史界面
     * </p>
     * 
     * @return 执行历史
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toExecutionHistoryPage")
    public String toExecutionHistoryPage(String triggerName, String triggerGroup, String jobName, String jonGroup,
            HttpServletRequest request) {
        request.setAttribute("triggerName", triggerName);
        request.setAttribute("triggerGroup", triggerGroup);
        request.setAttribute("jobName", jobName);
        request.setAttribute("jonGroup", jonGroup);
        return "llsfw/quartz/executionHistory";
    }

    /**
     * <p>
     * Description: 返回执行历史清单
     * </p>
     * 
     * @param page 当前页
     * @param rows 每页行数
     * @return 执行历史清单
     * @throws Exception 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("loadExecutionHistoryList")
    @ResponseBody
    public Map<String, Object> loadExecutionHistoryList(int page, int rows, String execution_history_trigger_group,
            String execution_history_trigger_name, String execution_history_job_group,
            String execution_history_job_name, String execution_status) throws Exception {
        return this.qs.loadExecutionHistoryList(page, rows, execution_history_trigger_group,
                execution_history_trigger_name, execution_history_job_group, execution_history_job_name,
                execution_status);
    }

    /**
     * <p>
     * Description: 跳转到操作历史界面
     * </p>
     * 
     * @return 操作历史界面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toSchedulerLogPage")
    public String toSchedulerLogPage() {
        return "llsfw/quartz/schedulerLog";
    }

    @RequiresPermissions("quartzController:view")
    @RequestMapping("loadSchedulerLogList")
    @ResponseBody
    public Map<String, Object> loadSchedulerLogList(int page, int rows) throws Exception {
        return qs.loadSchedulerLogList(page, rows);
    }

    /**
     * <p>
     * Description: 返回计划任务明细
     * </p>
     * 
     * @param request 请求对象
     * @param sName 计划任务名称
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @param tName 触发器名称
     * @param tGroup 触发器组别
     * @param tType 触发器类型(simple,cron)
     * @return 计划任务明细
     * @throws SchedulerException 计划任务异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getSchedulerDetail")
    public String getSchedulerDetail(HttpServletRequest request, String sName, String jName, String jGroup,
            String tName, String tGroup, String tType) throws SchedulerException {
        //计划任务状态
        request.setAttribute("schedulerStateList", this.qs.getschedulerState());

        //SchedulerRunningStatus计划任务运行状态
        request.setAttribute("SchedulerRunningStatus", this.qs.getSchedulerRunningStatus());

        //触发器明细
        if (!StringUtils.isEmpty(sName) && !StringUtils.isEmpty(tName) && !StringUtils.isEmpty(tGroup)) {

            //如果触发器类型为空,则获取触发器类型
            if (StringUtils.isEmpty(tType)) {
                tType = this.qs.getTriggerType(sName, tName, tGroup);
            } else {
                if (tType.equals(Constants.TTYPE_SIMPLE)) {
                    request.setAttribute("simpleTriggerDetail", this.qs.getSimpleTriggerDetail(sName, tName, tGroup));
                } else if (tType.equals(Constants.TTYPE_CRON)) {
                    request.setAttribute("cronTriggerDetail", this.qs.getCronTriggerDetail(sName, tName, tGroup));
                }
            }
        }

        //作业明细
        if (!StringUtils.isEmpty(sName) && !StringUtils.isEmpty(jName) && !StringUtils.isEmpty(jGroup)) {
            request.setAttribute("jobDetail", this.qs.getJobDetail(sName, jName, jGroup));
        }

        return "llsfw/quartz/triggerDetail";
    }

    /**
     * <p>
     * Description: 获取当前正在执行的任务列表
     * </p>
     * 
     * @return 正在执行的任务列表
     * @throws SchedulerException 计划任务异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getFiredTriggers")
    @ResponseBody
    public List<Map<String, Object>> getFiredTriggers() throws SchedulerException {
        return this.qs.getFiredTriggers();
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
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getTriggers")
    @ResponseBody
    public List<Map<String, Object>> getTriggers(String tNameSearch, String tGroupSearch, String jNameSearch,
            String jGroupSearch, String sort, String order) {
        return this.qs.getTriggers(tNameSearch, tGroupSearch, jNameSearch, jGroupSearch, sort, order);
    }

    /**
     * <p>
     * Description: 初始化
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("init")
    public String init() {
        return "llsfw/quartz/quartzMain";
    }
}
