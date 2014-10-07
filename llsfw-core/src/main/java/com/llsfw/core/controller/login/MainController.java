/**
 * MainController.java
 * Created at 2013-12-15
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.login;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.mapper.expand.ISecurityMapper;
import com.llsfw.core.security.annotation.CurrentUser;
import com.llsfw.core.service.function.FunctionService;
import com.llsfw.core.service.onlineuser.OnLineUserService;
import com.llsfw.core.service.serverparam.ParamService;
import com.llsfw.core.service.user.UserService;

/**
 * <p>
 * ClassName: MainController
 * </p>
 * <p>
 * Description: 主体框架控制
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月15日
 * </p>
 */
@Controller
@RequestMapping("mainController")
public class MainController extends BaseController {

    /**
     * <p>
     * Field us: 用户服务
     * </p>
     */
    @Autowired
    private UserService us;

    /**
     * <p>
     * Field fs: 功能服务
     * </p>
     */
    @Autowired
    private FunctionService fs;

    /**
     * <p>
     * Field pss: 参数服务
     * </p>
     */
    @Autowired
    private ParamService pss;

    /**
     * <p>
     * Field olus: 在线用户服务
     * </p>
     */
    @Autowired
    private OnLineUserService olus;

    /**
     * <p>
     * Field ism: 安全dao
     * </p>
     */
    @Autowired
    private ISecurityMapper ism;

    /**
     * <p>
     * Description: 获得menu菜单
     * </p>
     * 
     * @param appCode 应用代码
     * @param loginName 登录名
     * @param request 请求对象
     * @return 菜单脚本
     */
    @RequestMapping("getMenuTreeAccordion")
    public String getMenuTreeAccordion(String appCode, @CurrentUser String loginName, HttpServletRequest request) {
        request.setAttribute("menuTreeAccordion", this.fs.getMenuTreeAccordion(loginName, appCode));
        return "llsfw/menu";
    }

    /**
     * <p>
     * Description: 返回应用菜单
     * </p>
     * 
     * @param session session对象
     * @return 应用列表
     */
    @RequestMapping("loadAppData")
    @ResponseBody
    public List<Map<String, Object>> loadAppData(@CurrentUser String loginName) {
        return this.fs.loadAppData(loginName);
    }

    /**
     * <p>
     * Description: 跳转到修改密码界面
     * </p>
     * 
     * @return 修改密码界面
     */
    @RequestMapping("toChangePswd")
    public String toChangePswd() {
        return "llsfw/changePswd";
    }

    /**
     * <p>
     * Description: 修改密码
     * </p>
     * 
     * @param loginName 登陆名
     * @param newPswd 新密码
     * @return 操作结果
     */
    @RequestMapping("changePswd")
    @ResponseBody
    public Map<String, Object> changePswd(@CurrentUser String loginName, String newPswd) {
        return this.us.changePswd(loginName, newPswd);
    }

    /**
     * <p>
     * Description: 验证密码是否正确
     * </p>
     * 
     * @param loginName 登陆名
     * @param oldPswd 旧密码
     * @return ture:正确,false:错误
     */
    @RequestMapping("pswdCheck")
    @ResponseBody
    public boolean pswdCheck(@CurrentUser String loginName, String oldPswd) {
        return this.us.pswdCheck(loginName, oldPswd);
    }

    /**
     * <p>
     * Description: 加载top
     * </p>
     * 
     * @param loginName 当前登陆名
     * @param request request对象
     * @return top页面
     */
    @RequestMapping("toTopPage")
    public String toTopPage(@CurrentUser String loginName, HttpServletRequest request) {
        request.setAttribute("userName", loginName);
        return "/llsfw/top";
    }

    /**
     * <p>
     * Description: 跳转到右部页面
     * </p>
     * 
     * @return 右部页面
     */
    @RequestMapping("toRight")
    public String toRight() {
        return "llsfw/right";
    }

    /**
     * <p>
     * Description: 跳转到底部页面
     * </p>
     * 
     * @return 底部页面
     */
    @RequestMapping("toBottom")
    public String toBottom() {
        return "llsfw/bottom";
    }

    /**
     * <p>
     * Description: 跳转到左边页面
     * </p>
     * 
     * @return 左边页面
     */
    @RequestMapping("toLeft")
    public String toLeft() {
        return "llsfw/left";
    }

}
