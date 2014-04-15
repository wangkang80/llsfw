/**
 * ExceptionResponse.java
 * Created at 2014年3月8日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>
 * ClassName: ExceptionResponse
 * </p>
 * <p>
 * Description: 共用异常类
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月8日
 * </p>
 */
public class ExceptionResponse {

    private String exception;

    private String message;

    private String stackTrace;

    private ExceptionResponse() {

    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" + "exception='" + exception + '\'' + ", message='" + message + '\''
                + ", stackTrace='" + stackTrace + '\'' + '}';
    }

    public static ExceptionResponse from(Throwable e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        String errorMessage = "<h3 style='display: inline;'>出错了！</h3><br/>错误信息：" + convertMessage(e);
        exceptionResponse.setMessage(errorMessage);
        exceptionResponse.setException(e.getClass().getName());
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        exceptionResponse.setStackTrace(stringWriter.toString());
        return exceptionResponse;
    }

    private static String convertMessage(Throwable e) {
        String errorMessage = e.getMessage();
        //验证失败
        //        if (e instanceof UnauthorizedException) {
        //            if (errorMessage.startsWith("Subject does not have permission")) {
        //                errorMessage = errorMessage.replaceAll("Subject does not have permission", "您没有操作权限，请联系管理员添加权限");
        //            }
        //            if (errorMessage.startsWith("User is not permitted")) {
        //                errorMessage = errorMessage.replaceAll("User is not permitted", "您没有操作权限，请联系管理员添加权限");
        //            }
        //            if (errorMessage.startsWith("Subject does not have role")) {
        //                errorMessage = errorMessage.replaceAll("Subject does not have role", "您没有操作权限，请联系管理员添加角色");
        //            }
        //        }

        return errorMessage;
    }
}
