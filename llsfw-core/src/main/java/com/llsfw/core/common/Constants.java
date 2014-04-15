/**
 * Constants.java
 * Created at 2013-11-29
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.common;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * ClassName: Constants
 * </p>
 * <p>
 * Description: 常量
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年11月29日
 * </p>
 */
public class Constants {

    //------------------------------------------------------------------------------------

    /**
     * <p>
     * Field DEF_CHARACTER_SET_ENCODING: 默认字符集编码
     * </p>
     */
    public static final String DEF_CHARACTER_SET_ENCODING = "UTF-8";

    /**
     * <p>
     * Field IO_BUFFERED_1024: 缓冲区大小
     * </p>
     */
    public static final int IO_BUFFERED_1024 = 1024;

    /**
     * <p>
     * Field SUCCESS:成功
     * </p>
     */
    public static final String SUCCESS = "1";
    /**
     * <p>
     * Field FAIL:失败
     * </p>
     */
    public static final String FAIL = "-1";
    /**
     * <p>
     * Field CODE: code
     * </p>
     */
    public static final String CODE = "code";

    /**
     * <p>
     * Field MESSAGE: 消息
     * </p>
     */
    public static final String MESSAGE = "message";
    /**
     * <p>
     * Field SCHEDULER_CLEAR_OP_PSWD: 清除计划任务数据操作密码
     * </p>
     */
    public static final String SCHEDULER_CLEAR_OP_PSWD = "48527136";
    /**
     * <p>
     * Field COMMA: 逗号
     * </p>
     */
    public static final String COMMA = ",";
    /**
     * <p>
     * Field APP_LEVEL: 应用级别
     * </p>
     */
    public static final int APP_LEVEL = 1;

    /**
     * <p>
     * Field MENU_LEVEL: 目录级别
     * </p>
     */
    public static final int MENU_LEVEL = 2;

    /**
     * <p>
     * Field FUNCTION_LEVEL: 功能级别
     * </p>
     */
    public static final int FUNCTION_LEVEL = 3;

    /**
     * <p>
     * Field EXCEPTION_MSG_LENGTH:
     * 记录异常的长度(oracle字段长度4000,预计在异常信息中不可能出现333个汉字,则定义为3000)
     * </p>
     */
    public static final int EXCEPTION_MSG_LENGTH = 3000;

    /**
     * <p>
     * Field HTTP_STATUS_518: SESSION超时
     * </p>
     */
    public static final int HTTP_STATUS_518 = 518;

    /**
     * 获取登录用户IP地址
     * 
     * @param request 请求参数
     * @return IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = "";
        if (request != null) {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = "本地";
            }
        }
        return ip;
    }
}
