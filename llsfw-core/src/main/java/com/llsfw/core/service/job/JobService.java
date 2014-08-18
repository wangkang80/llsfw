/**
 * JobService.java
 * Created at 2014年3月7日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.service.job;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.SystemParam;
import com.llsfw.core.mapper.standard.TtAppLogMapper;
import com.llsfw.core.mapper.standard.TtScheduledLogMapper;
import com.llsfw.core.mapper.standard.TtScheduledTriggerLogMapper;
import com.llsfw.core.model.standard.TtAppLogCriteria;
import com.llsfw.core.model.standard.TtScheduledLogCriteria;
import com.llsfw.core.model.standard.TtScheduledTriggerLogCriteria;
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

    @Autowired
    private TtAppLogMapper talm;

    @Autowired
    private TtScheduledLogMapper tslm;

    @Autowired
    private TtScheduledTriggerLogMapper tstlm;

    /**
     * <p>
     * Description: 清理系统异常日志
     * </p>
     */
    public void clearAppLog() {

        //获取参数
        String appLogKeepTime = this.getPs().getServerParamter(SystemParam.APP_LOG_KEEP_TIME.name());

        //计算归档日期
        Calendar cd = null;
        cd = Calendar.getInstance();
        cd.setTime(new Date());
        cd.add(Calendar.DATE, 0 - Integer.parseInt(appLogKeepTime));

        //条件
        TtAppLogCriteria tac = null;
        tac = new TtAppLogCriteria();
        tac.createCriteria().andCreateDateLessThanOrEqualTo(cd.getTime());

        //删除
        this.talm.deleteByExample(tac);
    }

    /**
     * <p>
     * Description: 清理计划任务日志
     * </p>
     */
    public void clearScheduledLog() {

        //获取参数
        String scheduledLogKeepTime = this.getPs().getServerParamter(SystemParam.SCHEDULED_LOG_KEEP_TIME.name());

        //计算归档日期
        Calendar cd = null;
        cd = Calendar.getInstance();
        cd.setTime(new Date());
        cd.add(Calendar.DATE, 0 - Integer.parseInt(scheduledLogKeepTime));

        //条件
        TtScheduledLogCriteria tac = null;
        tac = new TtScheduledLogCriteria();
        tac.createCriteria().andCreateDateLessThanOrEqualTo(cd.getTime());

        //删除
        this.tslm.deleteByExample(tac);
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

        //计算归档日期
        Calendar cd = null;
        cd = Calendar.getInstance();
        cd.setTime(new Date());
        cd.add(Calendar.DATE, 0 - Integer.parseInt(scheduledTriggerLogKeepTime));

        //条件
        TtScheduledTriggerLogCriteria tac = null;
        tac = new TtScheduledTriggerLogCriteria();
        tac.createCriteria().andCreateDateLessThanOrEqualTo(cd.getTime());

        //删除
        this.tstlm.deleteByExample(tac);
    }
}
