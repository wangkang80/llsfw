/**
 * BaseController.java
 * Created at 2013-12-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.llsfw.core.service.applog.AppLogService;
import com.llsfw.core.service.serverparam.ParamService;

/**
 * <p>
 * ClassName: BaseController
 * </p>
 * <p>
 * Description: 父控制器
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月4日
 * </p>
 */
@Controller
public class BaseController {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field pss: 参数服务
     * </p>
     */
    @Autowired
    private ParamService pss;

    /**
     * <p>
     * Field als: applog服务
     * </p>
     */
    @Autowired
    private AppLogService als;
}
