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

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.model.standard.TtApplicationUser;
import com.llsfw.core.security.annotation.CurrentUser;
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
     * Description: 删除用户岗位关联
     * </p>
     * 
     * @param loginName 用户
     * @param jobCode 岗位
     * @return 操作结果
     */
    @RequiresPermissions("userController:job_delete")
    @RequestMapping("deleteUserJob")
    @ResponseBody
    public JsonResult<String> deleteUserJob(String loginName, String jobCode) {
        return this.us.deleteUserJob(loginName, jobCode);
    }

    /**
     * <p>
     * Description: 跳转到用户岗位关联界面
     * </p>
     * 
     * @param loginName 用户名
     * @param request 请求
     * @return 用户岗位关联界面
     */
    @RequiresPermissions("userController:job_add")
    @RequestMapping("toAddUserJob")
    public String toAddUserJob(String userName, HttpServletRequest request) {
        request.setAttribute("userName", userName);
        return "llsfw/user/addUserJob";
    }

    /**
     * <p>
     * Description: 添加用户岗位关联
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCodeList 岗位列表
     * @param s session对象
     * @return 操作结果
     */
    @RequiresPermissions("userController:job_add")
    @RequestMapping("addUserJob")
    @ResponseBody
    public JsonResult<String> addUserJob(@CurrentUser String loginName, String userName, String jobCodeList) {
        return this.us.addUserJob(loginName, userName, jobCodeList);
    }

    /**
     * <p>
     * Description: 返回岗位列列表
     * </p>
     * 
     * @param loginName 登录名
     * @return 岗位列表
     */
    @RequiresPermissions("userController:job_add")
    @RequestMapping("getJobList")
    @ResponseBody
    public List<Map<String, Object>> getJobList(String loginName) {
        return this.us.getJobList(loginName);
    }

    /**
     * <p>
     * Description: 删除用户
     * </p>
     * 
     * @param loginName 用户名
     * @return 操作结果
     */
    @RequiresPermissions("userController:delete")
    @RequestMapping("userDelete")
    @ResponseBody
    public JsonResult<String> userDelete(String loginName) {
        return this.us.userDelete(loginName);
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
    @RequiresPermissions("userController:def_pswd")
    @RequestMapping("saveDefPswd")
    @ResponseBody
    public JsonResult<String> saveDefPswd(@CurrentUser String loginUser, String loginName) {
        return this.us.saveDefPswd(loginName, loginUser);
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
    @RequiresPermissions("userController:edit")
    @RequestMapping("toUserEdit")
    public String toUserEdit(String loginName, HttpServletRequest request) {
        request.setAttribute("loginName", loginName);
        return "llsfw/user/userEdit";
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
    @RequiresPermissions("userController:edit")
    @RequestMapping("editUser")
    @ResponseBody
    public JsonResult<String> editUser(@CurrentUser String loginUser, HttpServletRequest r, String loginName)
            throws Exception {
        return this.us.editUser(r.getParameterMap(), loginName, loginUser);
    }

    /**
     * <p>
     * Description: 加载用户数据
     * </p>
     * 
     * @param loginName 登陆名字
     * @return 用户
     */
    @RequiresPermissions("userController:edit")
    @RequestMapping("loadUser")
    @ResponseBody
    public TtApplicationUser loadUser(String loginName) {
        return this.us.loadUser(loginName);
    }

    /**
     * <p>
     * Description: 跳转到用户新增界面
     * </p>
     * 
     * @return 用户新增界面
     */
    @RequiresPermissions("userController:add")
    @RequestMapping("toUserAdd")
    public String toUserAdd() {
        return "llsfw/user/userAdd";
    }

    /**
     * <p>
     * Description: 保存用户(密码初始化)
     * </p>
     * 
     * @param tau 用户对象
     * @param loginName 登录名
     * @return 操作结果
     */
    @RequiresPermissions("userController:add")
    @RequestMapping("addUser")
    @ResponseBody
    public JsonResult<String> addUser(@CurrentUser String loginName, TtApplicationUser tau) {
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
    @RequiresPermissions("userController:add")
    @RequestMapping("loginNameUniqueCheck")
    @ResponseBody
    public boolean loginNameUniqueCheck(String loginName) {
        return this.us.loginNameUniqueCheck(loginName);
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("userController:view")
    @RequestMapping("init")
    public String init() {
        return "llsfw/user/userMain";
    }

    /**
     * <p>
     * Description: 返回用户岗位列表
     * </p>
     * 
     * @param loginName 当前用户名
     * @return 岗位列表
     */
    @RequiresPermissions("userController:view")
    @RequestMapping("getUserJobList")
    @ResponseBody
    public List<Map<String, Object>> getUserJobList(String loginName) {
        return this.us.getUserJobList(loginName);
    }

    /**
     * <p>
     * Description: 返回用户列表
     * </p>
     * 
     * @return 用户列表
     */
    @RequiresPermissions("userController:view")
    @RequestMapping("getUserList")
    @ResponseBody
    public List<Map<String, Object>> getUserList() {
        return this.us.getUserList();
    }
}
