/**
 * BaseJob.java
 * Created at 2013-12-27
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.scheduler;

import java.util.Random;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * <p>
 * ClassName: BaseJob
 * </p>
 * <p>
 * Description: 计划任务父类
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月27日
 * </p>
 */
public abstract class AbstractBaseJob extends QuartzJobBean {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field applicationContext: spring上下文
     * </p>
     */
    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * <p>
     * Description: 任务执行方法
     * </p>
     * 
     * @param jobExecutionContext 任务上下文
     * @throws JobExecutionException 任务执行异常
     */
    protected abstract void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException;

    /**
     * <p>
     * Description: 休眠方法
     * </p>
     * 
     * @param name 名字
     * @param time 时间
     */
    public void sleep(String name, int time) {
        //休眠
        try {
            Random r = new Random();
            int rInt = 0;
            rInt = r.nextInt(time);
            this.log.info(name + "本次休眠时间为" + rInt + "毫秒");
            Thread.sleep(rInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
