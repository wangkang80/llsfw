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

import com.llsfw.core.controller.base.BaseController;

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
public class LoginController extends BaseController {

    /**
     * <p>
     * Description: 跳转到mainPage
     * </p>
     * 
     * @return main页面
     */
    @RequestMapping("/")
    public String toMainPage() {
        return "llsfw/main";
    }

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
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String rv = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户名/密码错误";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户被锁定";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            rv = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            rv = "未知错误,请联系管理员";
            this.log.info(exceptionClassName);
        }
        req.setAttribute("rv", rv);
        return "llsfw/login";
    }
}
