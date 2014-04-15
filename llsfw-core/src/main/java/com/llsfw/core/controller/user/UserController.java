/**
 * UserController.java
 * Created at 2013-12-15
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.model.standard.TtApplicationUser;
import com.llsfw.core.service.user.UserService;

/**
 * <p>
 * ClassName: UserController
 * </p>
 * <p>
 * Description: 用户维护
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月15日
 * </p>
 */
@Controller
@RequestMapping("userController")
public class UserController extends BaseController {
    /**
     * <p>
     * Field us: 用户服务
     * </p>
     */
    @Autowired
    private UserService us;

    /**
     * <p>
     * Description: 跳转到用户新增界面
     * </p>
     * 
     * @return 用户新增界面
     */
    @RequestMapping("toUserAdd")
    public String toUserAdd() {
        return "llsfw/user/userAdd";
    }

    /**
     * <p>
     * Description: 跳转到用户修改界面
     * </p>
     * 
     * @param loginName 用户名
     * @param request 请求
     * @return 用户修改界面
     */
    @RequestMapping("toUserEdit")
    public String toUserEdit(String loginName, HttpServletRequest request) {
        request.setAttribute(loginName, loginName);
        return "llsfw/user/userEdit";
    }

    /**
     * <p>
     * Description: 跳转到用户角色关联界面
     * </p>
     * 
     * @param loginName 用户名
     * @param request 请求
     * @return 用户角色关联界面
     */
    @RequestMapping("toAddUserRole")
    public String toAddUserRole(String loginName, HttpServletRequest request) {
        request.setAttribute(loginName, loginName);
        return "llsfw/user/addUserRole";
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequestMapping("init")
    public String init() {
        return "llsfw/user/userMain";
    }

    /**
     * <p>
     * Description: 删除用户角色关联
     * </p>
     * 
     * @param loginName 用户
     * @param roleCode 角色
     * @return 操作结果
     */
    @RequestMapping("deleteUserRole")
    @ResponseBody
    public Map<String, Object> deleteUserRole(String loginName, String roleCode) {
        return this.us.deleteUserRole(loginName, roleCode);
    }

    /**
     * <p>
     * Description: 添加用户角色关联
     * </p>
     * 
     * @param loginName 用户名
     * @param roleCodeList 角色列表
     * @param s session对象
     * @return 操作结果
     */
    @RequestMapping("addUserRole")
    @ResponseBody
    public Map<String, Object> addUserRole(String loginName, String roleCodeList, HttpSession s) {
        //获取登录名
        String ln = null;
        ln = this.getLoginName(s);

        return this.us.addUserRole(loginName, roleCodeList, ln);
    }

    /**
     * <p>
     * Description: 返回角色列列表
     * </p>
     * 
     * @param loginName 登录名
     * @return 角色列表
     */
    @RequestMapping("getRoleList")
    @ResponseBody
    public List<Map<String, Object>> getRoleList(String loginName) {
        return this.us.getRoleList(loginName);
    }

    /**
     * <p>
     * Description: 返回用户角色列表
     * </p>
     * 
     * @param loginName 当前用户名
     * @return 角色列表
     */
    @RequestMapping("getUserRoleList")
    @ResponseBody
    public List<Map<String, Object>> getUserRoleList(String loginName) {
        return this.us.getUserRoleList(loginName);
    }

    /**
     * <p>
     * Description: 初始化密码
     * </p>
     * 
     * @param s session对象
     * @param loginName 登录名
     * @return 操作结果
     */
    @RequestMapping("saveDefPswd")
    @ResponseBody
    public Map<String, Object> saveDefPswd(HttpSession s, String loginName) {
        //获取登录名
        String ln = null;
        ln = this.getLoginName(s);

        return this.us.saveDefPswd(loginName, ln);
    }

    /**
     * <p>
     * Description: 修改用户
     * </p>
     * 
     * @param s session对象
     * @param r 请求对象
     * @param loginName 用户ID
     * @return 操作人
     * @throws Exception 异常
     */
    @RequestMapping("editUser")
    @ResponseBody
    public Map<String, Object> editUser(HttpSession s, HttpServletRequest r, String loginName) throws Exception {
        //获取登录名
        String ln = null;
        ln = this.getLoginName(s);

        return this.us.editUser(r.getParameterMap(), loginName, ln);
    }

    /**
     * <p>
     * Description: 加载用户数据
     * </p>
     * 
     * @param loginName 登陆名字
     * @return 用户
     */
    @RequestMapping("loadUser")
    @ResponseBody
    public TtApplicationUser loadUser(String loginName) {
        return this.us.loadUser(loginName);
    }

    /**
     * <p>
     * Description: 保存用户(密码初始化)
     * </p>
     * 
     * @param tau 用户对象
     * @param session 登录名
     * @return 操作结果
     */
    @RequestMapping("addUser")
    @ResponseBody
    public Map<String, Object> addUser(TtApplicationUser tau, HttpSession session) {
        //获取登录名
        String loginName = null;
        loginName = this.getLoginName(session);

        return this.us.addUser(tau, loginName);
    }

    /**
     * <p>
     * Description: 校验登录名是否维护
     * </p>
     * 
     * @param loginName 登录名
     * @return true:通过,false:不通过
     */
    @RequestMapping("loginNameUniqueCheck")
    @ResponseBody
    public boolean loginNameUniqueCheck(String loginName) {
        return this.us.loginNameUniqueCheck(loginName);
    }

    /**
     * <p>
     * Description: 返回用户列表
     * </p>
     * 
     * @return 用户列表
     */
    @RequestMapping("getUserList")
    @ResponseBody
    public List<Map<String, Object>> getUserList() {
        return this.us.getUserList();
    }
}
