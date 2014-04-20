/**
 * LoginController.java
 * Created at 2013-11-30
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * Description: shiro自动验证,验证失败后,调用此方法,返回失败原因到界面
     * </p>
     * 
     * @param req 请求对象
     * @return 登录页面
     */
    @RequestMapping(value = "login")
    public String login(HttpServletRequest req) {
        System.out.println(req.getRequestURL());
        System.out.println(req.getMethod());
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String rv = "请登录系统";
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户不存在";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户已停用";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            rv = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            rv = "其他错误：" + exceptionClassName;
        }
        req.setAttribute("rv", rv);
        return "llsfw/login";
    }
}
