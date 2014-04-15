/**
 * LoginController.java
 * Created at 2013-11-30
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.login;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.common.SystemParam;
import com.llsfw.core.model.expand.LoginUser;
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

    /**
     * <p>
     * Description: 登陆服务
     * </p>
     * 
     * @param session session
     * @param userName 用户名
     * @param password 密码
     * @return 操作结果
     */
    @RequestMapping("loginCheck")
    @ResponseBody
    public Map<String, Object> loginCheck(HttpSession session, String userName, String password) {
        //设定返回值
        Map<String, Object> rv = null;
        rv = this.ls.loginCheck(userName, password);

        //登陆成功
        if ("success".equals(rv.get("code").toString())) {
            //创建session对象
            LoginUser lu = null;
            lu = new LoginUser(this.ls.loadUser(userName));

            //获得session名称
            String sessionName = null;
            sessionName = this.pss.getServerParamter(SystemParam.SESSION_NAME.name());

            //放入session中
            session.setAttribute(sessionName, lu);
        }
        return rv;
    }
}
