/**
 * JobService.java
 * Created at 2014年3月7日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.service.job;

import org.springframework.stereotype.Service;

import com.llsfw.core.common.SystemParam;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: JobService
 * </p>
 * <p>
 * Description: 计划任务相关操作
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月7日
 * </p>
 */
@Service
public class JobService extends BaseService {

    /**
     * <p>
     * Description: 清理系统异常日志
     * </p>
     */
    public void clearAppLog() {

        //获取参数
        String appLogKeepTime = this.getPs().getServerParamter(SystemParam.APP_LOG_KEEP_TIME.name());

        //SQL
        String sql = "DELETE TT_APP_LOG WHERE CREATE_DATE<=SYSDATE-" + appLogKeepTime;

        //执行
        this.getImqm().delete(sql);
    }

    /**
     * <p>
     * Description: 清理计划任务日志
     * </p>
     */
    public void clearScheduledLog() {

        //获取参数
        String scheduledLogKeepTime = this.getPs().getServerParamter(SystemParam.SCHEDULED_LOG_KEEP_TIME.name());

        //SQL
        String sql = "DELETE TT_SCHEDULED_LOG WHERE CREATE_DATE<=SYSDATE-" + scheduledLogKeepTime;

        //执行
        this.getImqm().delete(sql);
    }

    /**
     * <p>
     * Description: 清理计划任务执行日志
     * </p>
     */
    public void clearScheduledTriggerLog() {

        //获取参数
        String scheduledTriggerLogKeepTime = this.getPs().getServerParamter(
                SystemParam.SCHEDULED_TRIGGER_LOG_KEEP_TIME.name());

        //SQL
        String sql = "DELETE TT_SCHEDULED_TRIGGER_LOG WHERE CREATE_DATE<=SYSDATE-" + scheduledTriggerLogKeepTime;

        //执行
        this.getImqm().delete(sql);
    }
}
