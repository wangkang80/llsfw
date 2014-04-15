/**
 * BaseController.java
 * Created at 2013-12-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.base;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.llsfw.core.common.SystemParam;
import com.llsfw.core.model.expand.LoginUser;
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

    /**
     * <p>
     * Description: 获得登陆对象
     * </p>
     * 
     * @param session session对象
     * @return 登陆对象
     */
    public LoginUser getLoginUser(HttpSession session) {
        //获得session名称
        String sessionName = null;
        sessionName = this.pss.getServerParamter(SystemParam.SESSION_NAME.name());

        //获得session对象
        Object sessionObject = null;
        sessionObject = session.getAttribute(sessionName);

        //返回
        if (sessionObject == null) {
            return null;
        } else {
            return (LoginUser) sessionObject;
        }
    }

    /**
     * <p>
     * Description: 获取登陆名称(如果session不存在,并且此功能是设定了权限例外的,则会获取系统定义的默认用户名)
     * </p>
     * 
     * @param session session对象
     * @return 登录名
     */
    public String getLoginName(HttpSession session) {

        //获得session名称
        String sessionName = null;
        sessionName = this.pss.getServerParamter(SystemParam.SESSION_NAME.name());

        //获得session对象
        Object sessionObject = null;
        sessionObject = session.getAttribute(sessionName);

        //判断session是否存在
        String loginName = null;
        if (sessionObject == null) {
            //不存在,获取系统默认定义的值
            loginName = this.pss.getServerParamter(SystemParam.SYSTEM_DEF_NAME.name());
        } else {
            //存在,从session中获取
            LoginUser user = null;
            user = (LoginUser) sessionObject;
            loginName = user.getUser().getLoginName();
        }

        return loginName;
    }
}
