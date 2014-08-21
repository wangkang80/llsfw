/**
 * TestJob.java
 * Created at 2014年8月21日
 * Created by wangkang
 */
package com.llsfw.core.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * ClassName: TestJob
 * </p>
 * <p>
 * Description: 测试job
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年8月21日
 * </p>
 */
@DisallowConcurrentExecution
public class TestJob extends AbstractBaseJob {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            String json = JSON.toJSONString(jobDataMap);
            this.log.info("====开始===" + json);
            Thread.sleep(1000 * 5);
            this.log.info("====结束===" + json);
            this.log.info(" ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
