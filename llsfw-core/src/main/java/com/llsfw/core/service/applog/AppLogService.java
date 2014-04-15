/**
 * AppLogService.java
 * Created at 2013-12-17
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.applog;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.mapper.standard.TtAppLogMapper;
import com.llsfw.core.mapper.standard.TtScheduledLogMapper;
import com.llsfw.core.model.standard.TtAppLog;
import com.llsfw.core.model.standard.TtScheduledLog;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: AppLogService
 * </p>
 * <p>
 * Description: 日志服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月17日
 * </p>
 */
@Service
public class AppLogService extends BaseService {

    /**
     * <p>
     * Field talm: 日志服务
     * </p>
     */
    @Autowired
    private TtAppLogMapper talm;

    /**
     * <p>
     * Field tslm: 计划任务日志服务
     * </p>
     */
    @Autowired
    private TtScheduledLogMapper tslm;

    /**
     * <p>
     * Description: 保存记录(此方法吃掉所有异常,避免异常日志切换形成递归.)
     * </p>
     * 
     * @param msg 信息
     */
    public void saveScheduledLog(String msg) {
        try {

            // 获得UUID
            String uuid = null;
            uuid = UUID.randomUUID().toString();

            //设置
            TtScheduledLog tsl = new TtScheduledLog();
            tsl.setLogid(uuid);
            tsl.setCreateDate(new Date());
            tsl.setMsg(msg);

            //保存
            this.tslm.insert(tsl);

        } catch (Throwable e) {
            this.log.error("AppLogService.saveScheduledLog保存日志异常:", e);
        }
    }

    /**
     * <p>
     * Description: 保存记录(此方法吃掉所有异常,避免异常日志切换形成递归.)
     * </p>
     * 
     * @param className 类名
     * @param method 方法名
     * @param loglevel 级别
     * @param msg 信息
     */
    public void saveAppLog(String className, String method, String loglevel, String msg) {
        try {

            // 获得UUID
            String uuid = null;
            uuid = UUID.randomUUID().toString();

            //设置
            TtAppLog tal = null;
            tal = new TtAppLog();
            tal.setLogid(uuid);
            tal.setClassName(className);
            tal.setMethod(method);
            tal.setLoglevel(loglevel);
            tal.setCreateDate(new Date());
            tal.setMsg(msg);

            //保存
            this.talm.insert(tal);

        } catch (Throwable e) {
            this.log.error("AppLogService.saveAppLog保存日志异常:", e);
        }
    }
}
