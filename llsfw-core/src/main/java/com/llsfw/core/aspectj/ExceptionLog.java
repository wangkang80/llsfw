/**
 * ExceptionLog.java
 * Created at 2013-12-17
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.aspectj;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.common.Constants;
import com.llsfw.core.service.applog.AppLogService;

/**
 * <p>
 * ClassName: ExceptionLog
 * </p>
 * <p>
 * Description: 全局异常监控
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月17日
 * </p>
 */
public class ExceptionLog {
    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field as: 日志服务
     * </p>
     */
    @Autowired
    private AppLogService als;

    /**
     * <p>
     * Description: 异常切面
     * </p>
     * 
     * @param jp 目标
     * @param throwable 异常
     */
    public void afterThrowing(JoinPoint jp, Throwable throwable) {
        //获得异常详细信息
        ByteArrayOutputStream buf = null;
        buf = new ByteArrayOutputStream();
        throwable.printStackTrace(new PrintWriter(buf, true));
        String expMessage = null;
        expMessage = buf.toString();

        // 归纳异常信息
        String targetMethodName = null;
        targetMethodName = jp.getSignature().getName(); // 产生异常的方法名称
        String targetClassName = null;
        targetClassName = jp.getTarget().getClass().getName(); // 代理对象
        String exceptionDetail = null;
        exceptionDetail = expMessage; // 异常详细信息
        if (exceptionDetail.length() > Constants.EXCEPTION_MSG_LENGTH) {
            exceptionDetail = exceptionDetail.substring(0, Constants.EXCEPTION_MSG_LENGTH);
        }

        //保存
        this.als.saveAppLog(targetClassName, targetMethodName, "error", exceptionDetail);
    }
}
