package com.llsfw.core.model.standard;

import java.io.Serializable;
import java.util.Date;

public class TtScheduledTriggerLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.LOGID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String logid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_FIRE_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private Date scheduledFireTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.FIRE_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private Date fireTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.END_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.JOB_RUN_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private Long jobRunTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.STATUS
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.RESULT
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.ERROR_MSG
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String errorMsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.TRIGGER_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String triggerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.TRIGGER_GROUP
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String triggerGroup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.JOB_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String jobName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.JOB_GROUP
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String jobGroup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.JOB_CLASS
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String jobClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_GROUP_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String threadGroupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_ID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String threadId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String threadName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_PRIORITY
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String threadPriority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_ID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String scheduledId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private String scheduledName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_TRIGGER_LOG.CREATE_DATE
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_SCHEDULED_TRIGGER_LOG
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.LOGID
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.LOGID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getLogid() {
        return logid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.LOGID
     *
     * @param logid the value for TT_SCHEDULED_TRIGGER_LOG.LOGID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setLogid(String logid) {
        this.logid = logid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_FIRE_TIME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_FIRE_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public Date getScheduledFireTime() {
        return scheduledFireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_FIRE_TIME
     *
     * @param scheduledFireTime the value for TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_FIRE_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setScheduledFireTime(Date scheduledFireTime) {
        this.scheduledFireTime = scheduledFireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.FIRE_TIME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.FIRE_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public Date getFireTime() {
        return fireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.FIRE_TIME
     *
     * @param fireTime the value for TT_SCHEDULED_TRIGGER_LOG.FIRE_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setFireTime(Date fireTime) {
        this.fireTime = fireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.END_TIME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.END_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.END_TIME
     *
     * @param endTime the value for TT_SCHEDULED_TRIGGER_LOG.END_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.JOB_RUN_TIME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.JOB_RUN_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public Long getJobRunTime() {
        return jobRunTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.JOB_RUN_TIME
     *
     * @param jobRunTime the value for TT_SCHEDULED_TRIGGER_LOG.JOB_RUN_TIME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setJobRunTime(Long jobRunTime) {
        this.jobRunTime = jobRunTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.STATUS
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.STATUS
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.STATUS
     *
     * @param status the value for TT_SCHEDULED_TRIGGER_LOG.STATUS
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.RESULT
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.RESULT
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.RESULT
     *
     * @param result the value for TT_SCHEDULED_TRIGGER_LOG.RESULT
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.ERROR_MSG
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.ERROR_MSG
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.ERROR_MSG
     *
     * @param errorMsg the value for TT_SCHEDULED_TRIGGER_LOG.ERROR_MSG
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.TRIGGER_NAME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.TRIGGER_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getTriggerName() {
        return triggerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.TRIGGER_NAME
     *
     * @param triggerName the value for TT_SCHEDULED_TRIGGER_LOG.TRIGGER_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.TRIGGER_GROUP
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.TRIGGER_GROUP
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getTriggerGroup() {
        return triggerGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.TRIGGER_GROUP
     *
     * @param triggerGroup the value for TT_SCHEDULED_TRIGGER_LOG.TRIGGER_GROUP
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.JOB_NAME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.JOB_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.JOB_NAME
     *
     * @param jobName the value for TT_SCHEDULED_TRIGGER_LOG.JOB_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.JOB_GROUP
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.JOB_GROUP
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.JOB_GROUP
     *
     * @param jobGroup the value for TT_SCHEDULED_TRIGGER_LOG.JOB_GROUP
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.JOB_CLASS
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.JOB_CLASS
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getJobClass() {
        return jobClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.JOB_CLASS
     *
     * @param jobClass the value for TT_SCHEDULED_TRIGGER_LOG.JOB_CLASS
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_GROUP_NAME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.THREAD_GROUP_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getThreadGroupName() {
        return threadGroupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_GROUP_NAME
     *
     * @param threadGroupName the value for TT_SCHEDULED_TRIGGER_LOG.THREAD_GROUP_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setThreadGroupName(String threadGroupName) {
        this.threadGroupName = threadGroupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_ID
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.THREAD_ID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getThreadId() {
        return threadId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_ID
     *
     * @param threadId the value for TT_SCHEDULED_TRIGGER_LOG.THREAD_ID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_NAME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.THREAD_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getThreadName() {
        return threadName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_NAME
     *
     * @param threadName the value for TT_SCHEDULED_TRIGGER_LOG.THREAD_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_PRIORITY
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.THREAD_PRIORITY
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getThreadPriority() {
        return threadPriority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.THREAD_PRIORITY
     *
     * @param threadPriority the value for TT_SCHEDULED_TRIGGER_LOG.THREAD_PRIORITY
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setThreadPriority(String threadPriority) {
        this.threadPriority = threadPriority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_ID
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_ID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getScheduledId() {
        return scheduledId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_ID
     *
     * @param scheduledId the value for TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_ID
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setScheduledId(String scheduledId) {
        this.scheduledId = scheduledId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_NAME
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public String getScheduledName() {
        return scheduledName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_NAME
     *
     * @param scheduledName the value for TT_SCHEDULED_TRIGGER_LOG.SCHEDULED_NAME
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setScheduledName(String scheduledName) {
        this.scheduledName = scheduledName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_TRIGGER_LOG.CREATE_DATE
     *
     * @return the value of TT_SCHEDULED_TRIGGER_LOG.CREATE_DATE
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_TRIGGER_LOG.CREATE_DATE
     *
     * @param createDate the value for TT_SCHEDULED_TRIGGER_LOG.CREATE_DATE
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_TRIGGER_LOG
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TtScheduledTriggerLog other = (TtScheduledTriggerLog) that;
        return (this.getLogid() == null ? other.getLogid() == null : this.getLogid().equals(other.getLogid()))
            && (this.getScheduledFireTime() == null ? other.getScheduledFireTime() == null : this.getScheduledFireTime().equals(other.getScheduledFireTime()))
            && (this.getFireTime() == null ? other.getFireTime() == null : this.getFireTime().equals(other.getFireTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getJobRunTime() == null ? other.getJobRunTime() == null : this.getJobRunTime().equals(other.getJobRunTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getErrorMsg() == null ? other.getErrorMsg() == null : this.getErrorMsg().equals(other.getErrorMsg()))
            && (this.getTriggerName() == null ? other.getTriggerName() == null : this.getTriggerName().equals(other.getTriggerName()))
            && (this.getTriggerGroup() == null ? other.getTriggerGroup() == null : this.getTriggerGroup().equals(other.getTriggerGroup()))
            && (this.getJobName() == null ? other.getJobName() == null : this.getJobName().equals(other.getJobName()))
            && (this.getJobGroup() == null ? other.getJobGroup() == null : this.getJobGroup().equals(other.getJobGroup()))
            && (this.getJobClass() == null ? other.getJobClass() == null : this.getJobClass().equals(other.getJobClass()))
            && (this.getThreadGroupName() == null ? other.getThreadGroupName() == null : this.getThreadGroupName().equals(other.getThreadGroupName()))
            && (this.getThreadId() == null ? other.getThreadId() == null : this.getThreadId().equals(other.getThreadId()))
            && (this.getThreadName() == null ? other.getThreadName() == null : this.getThreadName().equals(other.getThreadName()))
            && (this.getThreadPriority() == null ? other.getThreadPriority() == null : this.getThreadPriority().equals(other.getThreadPriority()))
            && (this.getScheduledId() == null ? other.getScheduledId() == null : this.getScheduledId().equals(other.getScheduledId()))
            && (this.getScheduledName() == null ? other.getScheduledName() == null : this.getScheduledName().equals(other.getScheduledName()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_TRIGGER_LOG
     *
     * @mbggenerated Mon May 05 11:25:40 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogid() == null) ? 0 : getLogid().hashCode());
        result = prime * result + ((getScheduledFireTime() == null) ? 0 : getScheduledFireTime().hashCode());
        result = prime * result + ((getFireTime() == null) ? 0 : getFireTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getJobRunTime() == null) ? 0 : getJobRunTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getErrorMsg() == null) ? 0 : getErrorMsg().hashCode());
        result = prime * result + ((getTriggerName() == null) ? 0 : getTriggerName().hashCode());
        result = prime * result + ((getTriggerGroup() == null) ? 0 : getTriggerGroup().hashCode());
        result = prime * result + ((getJobName() == null) ? 0 : getJobName().hashCode());
        result = prime * result + ((getJobGroup() == null) ? 0 : getJobGroup().hashCode());
        result = prime * result + ((getJobClass() == null) ? 0 : getJobClass().hashCode());
        result = prime * result + ((getThreadGroupName() == null) ? 0 : getThreadGroupName().hashCode());
        result = prime * result + ((getThreadId() == null) ? 0 : getThreadId().hashCode());
        result = prime * result + ((getThreadName() == null) ? 0 : getThreadName().hashCode());
        result = prime * result + ((getThreadPriority() == null) ? 0 : getThreadPriority().hashCode());
        result = prime * result + ((getScheduledId() == null) ? 0 : getScheduledId().hashCode());
        result = prime * result + ((getScheduledName() == null) ? 0 : getScheduledName().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }
}