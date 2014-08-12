/**
 * Constants.java
 * Created at 2013-11-29
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    /**
     * <p>
     * Field DEF_STATUS_0: 状态0
     * </p>
     */
    public static final String DEF_STATUS_0 = "0";

    /**
     * <p>
     * Field DEF_STATUS_1: 状态1
     * </p>
     */
    public static final String DEF_STATUS_1 = "1";

    /**
     * <p>
     * Field DEF_CHARACTER_SET_ENCODING: 默认字符集编码
     * </p>
     */
    public static final String DEF_CHARACTER_SET_ENCODING = "UTF-8";

    /**
     * <p>
     * Field IO_BUFFERED: 缓冲区大小
     * </p>
     */
    public static final int IO_BUFFERED = 1024;

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

    public static final String BACKSLASH = "/";
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
     * Field CURRENT_LOGIN_NAME: 当前登陆的用户名
     * </p>
     */
    public static final String CURRENT_LOGIN_NAME = "CURRENT_LOGIN_NAME";

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

    /**
     * 
     * 读取返回的信息
     * 
     * @param in 输入流
     * 
     * @return 数据
     */
    public static String getData(InputStream in) {
        String result = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * Description: 根据文件名返回文件类型
     * </p>
     * 
     * @param returnFileName 文件名
     * @return 文件类型
     */
    public static String setContentType(String returnFileName) {

        String contentType = "application/octet-stream";
        if (returnFileName.lastIndexOf(".") < 0) {
            return contentType;
        }

        returnFileName = returnFileName.toLowerCase();
        returnFileName = returnFileName.substring(returnFileName.lastIndexOf(".") + 1);

        if (returnFileName.equals("html") || returnFileName.equals("htm") || returnFileName.equals("shtml")) {
            contentType = "text/html";
        } else if (returnFileName.equals("css")) {
            contentType = "text/css";
        } else if (returnFileName.equals("xml")) {
            contentType = "text/xml";
        } else if (returnFileName.equals("gif")) {
            contentType = "image/gif";
        } else if (returnFileName.equals("jpeg") || returnFileName.equals("jpg")) {
            contentType = "image/jpeg";
        } else if (returnFileName.equals("js")) {
            contentType = "application/x-javascript";
        } else if (returnFileName.equals("atom")) {
            contentType = "application/atom+xml";
        } else if (returnFileName.equals("rss")) {
            contentType = "application/rss+xml";
        } else if (returnFileName.equals("mml")) {
            contentType = "text/mathml";
        } else if (returnFileName.equals("txt")) {
            contentType = "text/plain";
        } else if (returnFileName.equals("jad")) {
            contentType = "text/vnd.sun.j2me.app-descriptor";
        } else if (returnFileName.equals("wml")) {
            contentType = "text/vnd.wap.wml";
        } else if (returnFileName.equals("htc")) {
            contentType = "text/x-component";
        } else if (returnFileName.equals("png")) {
            contentType = "image/png";
        } else if (returnFileName.equals("tif") || returnFileName.equals("tiff")) {
            contentType = "image/tiff";
        } else if (returnFileName.equals("wbmp")) {
            contentType = "image/vnd.wap.wbmp";
        } else if (returnFileName.equals("ico")) {
            contentType = "image/x-icon";
        } else if (returnFileName.equals("jng")) {
            contentType = "image/x-jng";
        } else if (returnFileName.equals("bmp")) {
            contentType = "image/x-ms-bmp";
        } else if (returnFileName.equals("svg")) {
            contentType = "image/svg+xml";
        } else if (returnFileName.equals("jar") || returnFileName.equals("var") || returnFileName.equals("ear")) {
            contentType = "application/java-archive";
        } else if (returnFileName.equals("doc")) {
            contentType = "application/msword";
        } else if (returnFileName.equals("pdf")) {
            contentType = "application/pdf";
        } else if (returnFileName.equals("rtf")) {
            contentType = "application/rtf";
        } else if (returnFileName.equals("xls")) {
            contentType = "application/vnd.ms-excel";
        } else if (returnFileName.equals("ppt")) {
            contentType = "application/vnd.ms-powerpoint";
        } else if (returnFileName.equals("7z")) {
            contentType = "application/x-7z-compressed";
        } else if (returnFileName.equals("rar")) {
            contentType = "application/x-rar-compressed";
        } else if (returnFileName.equals("swf")) {
            contentType = "application/x-shockwave-flash";
        } else if (returnFileName.equals("rpm")) {
            contentType = "application/x-redhat-package-manager";
        } else if (returnFileName.equals("der") || returnFileName.equals("pem") || returnFileName.equals("crt")) {
            contentType = "application/x-x509-ca-cert";
        } else if (returnFileName.equals("xhtml")) {
            contentType = "application/xhtml+xml";
        } else if (returnFileName.equals("zip")) {
            contentType = "application/zip";
        } else if (returnFileName.equals("mid") || returnFileName.equals("midi") || returnFileName.equals("kar")) {
            contentType = "audio/midi";
        } else if (returnFileName.equals("mp3")) {
            contentType = "audio/mpeg";
        } else if (returnFileName.equals("ogg")) {
            contentType = "audio/ogg";
        } else if (returnFileName.equals("m4a")) {
            contentType = "audio/x-m4a";
        } else if (returnFileName.equals("ra")) {
            contentType = "audio/x-realaudio";
        } else if (returnFileName.equals("3gpp") || returnFileName.equals("3gp")) {
            contentType = "video/3gpp";
        } else if (returnFileName.equals("mp4")) {
            contentType = "video/mp4";
        } else if (returnFileName.equals("mpeg") || returnFileName.equals("mpg")) {
            contentType = "video/mpeg";
        } else if (returnFileName.equals("mov")) {
            contentType = "video/quicktime";
        } else if (returnFileName.equals("flv")) {
            contentType = "video/x-flv";
        } else if (returnFileName.equals("m4v")) {
            contentType = "video/x-m4v";
        } else if (returnFileName.equals("mng")) {
            contentType = "video/x-mng";
        } else if (returnFileName.equals("asx") || returnFileName.equals("asf")) {
            contentType = "video/x-ms-asf";
        } else if (returnFileName.equals("wmv")) {
            contentType = "video/x-ms-wmv";
        } else if (returnFileName.equals("avi")) {
            contentType = "video/x-msvideo";
        }
        return contentType;
    }

}
