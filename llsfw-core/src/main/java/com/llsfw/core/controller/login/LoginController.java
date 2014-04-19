/**
 * LoginController.java
 * Created at 2013-11-30
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.service.login.LoginService;
import com.llsfw.core.service.serverparam.ParamService;

/**
 * <p>
 * ClassName: LoginController
 * </p>
 * <p>
 * Description: 登陆
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年11月30日
 * </p>
 */
@Controller
@RequestMapping("loginController")
public class LoginController {

    /**
     * <p>
     * Field pss: 参数服务
     * </p>
     */
    @Autowired
    private ParamService pss;

    /**
     * <p>
     * Field ls: 登陆服务
     * </p>
     */
    @Autowired
    private LoginService ls;

    @RequestMapping("index")
    public String index() {
        return "llsfw/index";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest req) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String rv = "success";
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户不存在";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户已停用";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            rv = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            rv = "其他错误：" + exceptionClassName;
        }
        return rv;
    }
}
