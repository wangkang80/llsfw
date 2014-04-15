/**
 * DefaultExceptionHandler.java
 * Created at 2014年3月8日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * ClassName: DefaultExceptionHandler
 * </p>
 * <p>
 * Description: 全局异常处理
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月8日
 * </p>
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({ RuntimeException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, RuntimeException e) {
        log.error("系统错误:", e);
        ExceptionResponse exceptionResponse = ExceptionResponse.from(e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", exceptionResponse);
        mv.setViewName("llsfw/error/exception");
        return mv;
    }
}
