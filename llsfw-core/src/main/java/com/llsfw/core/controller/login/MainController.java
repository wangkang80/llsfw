/**
 * MainController.java
 * Created at 2013-12-15
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.llsfw.core.common.SystemParam;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.model.expand.LoginUser;
import com.llsfw.core.model.standard.TtOnlineUser;
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
     * Description: 返回在线用户列表
     * </p>
     * 
     * @return 在线用户列表
     */
    @RequestMapping("getOnlineUserList")
    @ResponseBody
    public List<TtOnlineUser> getOnlineUserList() {
        return this.olus.getOnlineUserList();
    }

    /**
     * <p>
     * Description: 获得menu菜单
     * </p>
     * 
     * @param appCode 应用代码
     * @param session session对象
     * @param request 请求对象
     * @return 菜单脚本
     */
    @RequestMapping("getMenuTreeAccordion")
    public String getMenuTreeAccordion(String appCode, HttpSession session, HttpServletRequest request) {
        //获得用户
        LoginUser user = null;
        user = this.getLoginUser(session);

        //放入作用域
        request.setAttribute("menuTreeAccordion", this.fs.getMenuTreeAccordion(user.getUser().getLoginName(), appCode));

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
    public List<Map<String, Object>> loadAppData(HttpSession session) {
        //获得用户
        LoginUser user = null;
        user = this.getLoginUser(session);

        return this.fs.loadAppData(user.getUser().getLoginName());
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
     * @param session session对象
     * @param newPswd 新密码
     * @return 操作结果
     */
    @RequestMapping("changePswd")
    @ResponseBody
    public Map<String, Object> changePswd(HttpSession session, String newPswd) {
        //获得用户
        LoginUser user = null;
        user = this.getLoginUser(session);

        return this.us.changePswd(user.getUser().getLoginName(), newPswd);
    }

    /**
     * <p>
     * Description: 验证密码是否正确
     * </p>
     * 
     * @param session session对象
     * @param oldPswd 旧密码
     * @return ture:正确,false:错误
     */
    @RequestMapping("pswdCheck")
    @ResponseBody
    public boolean pswdCheck(HttpSession session, String oldPswd) {
        //获得用户
        LoginUser user = null;
        user = this.getLoginUser(session);
        return this.us.pswdCheck(user.getUser().getLoginName(), oldPswd);
    }

    /**
     * <p>
     * Description: 登出
     * </p>
     * 
     * @param session session对象
     * @return 操作结果
     */
    @RequestMapping("loginOut")
    @ResponseBody
    public Map<String, Object> loginOut(HttpSession session) {
        //获得session名称
        String sessionName = null;
        sessionName = this.pss.getServerParamter(SystemParam.SESSION_NAME.name());
        //移除session
        session.removeAttribute(sessionName);
        //返回
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "success");
        return rv;
    }

    /**
     * <p>
     * Description: 加载top
     * </p>
     * 
     * @param session session对象
     * @param request request对象
     * @return top页面
     */
    @RequestMapping("toTopPage")
    public String toTopPage(HttpSession session, HttpServletRequest request) {
        //获得用户
        LoginUser user = null;
        user = this.getLoginUser(session);

        //获得角色列表
        List<Map<String, Object>> roleList = null;
        roleList = this.us.getUserRoleList(user.getUser().getLoginName());
        List<String> roleArray = null;
        roleArray = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(roleList)) {
            for (Map<String, Object> role : roleList) {
                String roleName = null;
                roleName = role.get("ROLE_NAME").toString();
                roleArray.add(roleName);
            }
        }

        //放入作用域
        request.setAttribute("userName", user.getUser().getUserName());
        request.setAttribute("role", StringUtils.collectionToDelimitedString(roleArray, ","));

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

    /**
     * <p>
     * Description: 跳转到mainPage
     * </p>
     * 
     * @return main页面
     */
    @RequestMapping("toMainPage")
    public String toMainPage() {
        return "llsfw/main";
    }
}
