/**
 * ClearScheduledLog.java
 * Created at 2014年3月7日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.llsfw.core.service.job.JobService;

/**
 * <p>
 * ClassName: ClearScheduledLog
 * </p>
 * <p>
 * Description: 清理计划任务日志
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月7日
 * </p>
 */
@DisallowConcurrentExecution
public class ClearScheduledLog extends AbstractBaseJob {
    /**
     * <p>
     * Field applicationContext: spring上下文
     * </p>
     */
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobService js = this.applicationContext.getBean(JobService.class);
        js.clearScheduledLog();
        this.log.info("clearScheduledLog executeInternal end");
    }

}
