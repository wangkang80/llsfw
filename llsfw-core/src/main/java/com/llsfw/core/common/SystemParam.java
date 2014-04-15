/**
 * SYSTEM_PARAM_ENUM.java
 * Created at 2014年3月7日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

/**
 * <p>
 * ClassName: SYSTEM_PARAM_ENUM
 * </p>
 * <p>
 * Description: 系统参数枚举
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月7日
 * </p>
 */
public enum SystemParam {
    /**
     * <p>
     * Field PAGE_SIZE: 分页大小
     * </p>
     */
    PAGE_SIZE, /**
     * <p>
     * Field SESSION_NAME: 全局SESSION名称
     * </p>
     */
    SESSION_NAME, /**
     * <p>
     * Field SYSTEM_DEF_NAME: 系统默认用户名(SYSTEM)
     * </p>
     */
    SYSTEM_DEF_NAME, /**
     * <p>
     * Field SYSTEM_DEF_PSWD: 系统初始化密码
     * </p>
     */
    SYSTEM_DEF_PSWD, /**
     * <p>
     * Field TRIGGER_SEARCH_INTERVAL: 触发器轮询间隔(毫秒)
     * </p>
     */
    TRIGGER_SEARCH_INTERVAL,
    /**
     * <p>
     * Field DETAILED_RECORDS: 是否记录详细的执行历史
     * </p>
     */
    DETAILED_RECORDS, /**
     * <p>
     * Field SCHEDULED_LOG_KEEP_TIME: 计划任务日志保留天数
     * </p>
     */
    SCHEDULED_LOG_KEEP_TIME,
    /**
     * <p>
     * Field SCHEDULED_TRIGGER_LOG_KEEP_TIME: 计划任务执行日志保留天数
     * </p>
     */
    SCHEDULED_TRIGGER_LOG_KEEP_TIME,
    /**
     * <p>
     * Field APP_LOG_KEEP_TIME: 系统异常日志
     * </p>
     */
    APP_LOG_KEEP_TIME
}
