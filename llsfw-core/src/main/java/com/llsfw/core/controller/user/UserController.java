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
     * Description: 添加用户岗位关联
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCodeList 岗位代码
     * @return 操作结果
     */
    @RequiresPermissions("userController:job_add")
    @RequestMapping("addUserJob")
    @ResponseBody
    public JsonResult<String> addUserJob(@CurrentUser String loginName, String userName, String jobCode) {
        return this.us.addUserJob(loginName, userName, jobCode);
    }

    @RequiresPermissions("userController:job_add")
    @RequestMapping("loadAddUserJobFunctionTree")
    @ResponseBody
    public List<Map<String, Object>> loadAddUserJobFunctionTree(String jobCode) {
        return this.us.loadAddUserJobFunctionTree(jobCode);
    }

    /**
     * <p>
     * Description: 加载岗位清单
     * </p>
     * 
     * @param loginName 用户名
     * @param orgCodeList 组织清单
     * @return 结果集
     */
    @RequiresPermissions("userController:job_add")
    @RequestMapping("loadJobList")
    @ResponseBody
    public List<Map<String, Object>> loadJobList(String loginName, String orgCodeList) {
        return this.us.loadJobList(loginName, orgCodeList);
    }

    /**
     * <p>
     * Description: 加载组织机构树
     * </p>
     * 
     * @param userName 用户名
     * @return 结果集
     */
    @RequiresPermissions("userController:job_add")
    @RequestMapping("loadAllOrgTree")
    @ResponseBody
    public List<Map<String, Object>> loadAllOrgTree(String userName) {
        return this.us.loadAllOrgTree(userName);
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

    //-------------------------------直接授权

    /**
     * <p>
     * Description: 取消直接授权
     * </p>
     * 
     * @param userName 用户名
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    @RequiresPermissions("userController:function_add")
    @RequestMapping("deleteUserFunction")
    @ResponseBody
    public JsonResult<String> deleteUserFunction(String userName, String functionCode, String purviewCode) {
        return this.us.deleteUserFunction(userName, functionCode, purviewCode);
    }

    /**
     * <p>
     * Description: 添加直接权限
     * </p>
     * 
     * @param loginName 登陆人
     * @param userName 用户名
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    @RequiresPermissions("userController:function_add")
    @RequestMapping("addUserFunction")
    @ResponseBody
    public JsonResult<String> addUserFunction(@CurrentUser String loginName, String userName, String functionCode,
            String purviewCode) {
        return this.us.addUserFunction(loginName, userName, functionCode, purviewCode);
    }

    /**
     * <p>
     * Description: 加载完整的功能权限树
     * </p>
     * 
     * @param userName 用户名
     * @return 功能权限树
     */
    @RequiresPermissions("userController:function_add")
    @RequestMapping("loadUserFunctionTree")
    @ResponseBody
    public List<Map<String, Object>> loadUserFunctionTree(String userName) {
        return this.us.loadUserFunctionTree(userName);
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
    @RequiresPermissions("userController:function_add")
    @RequestMapping("toAddUserFunction")
    public String toAddUserFunction(String userName, HttpServletRequest request) {
        request.setAttribute("userName", userName);
        return "llsfw/user/addUserFunction";
    }

    //-------------------------------主体功能
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
     * Description: 返回用户列表
     * </p>
     * 
     * @param page 当前页
     * @param rows 每页行数
     * 
     * @return 用户列表
     * @throws Exception
     */
    @RequiresPermissions("userController:view")
    @RequestMapping("getUserList")
    @ResponseBody
    public Map<String, Object> getUserList(int page, int rows) throws Exception {
        return this.us.getUserList(page, rows);
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
     * Description: 返回角色列表
     * </p>
     * 
     * @param loginName 用户名
     * @param jobName 岗位名
     * @return 角色列表
     */
    @RequiresPermissions("userController:view")
    @RequestMapping("getUserJobRoleList")
    @ResponseBody
    public List<Map<String, Object>> getUserJobRoleList(String loginName, String jobName) {
        return this.us.getUserJobRoleList(loginName, jobName);
    }

    /**
     * <p>
     * Description: 加载用户关联的组织机构清单
     * </p>
     * 
     * @param loginName 用户名
     * @param jobName 岗位清单
     * @param loadOrgType 加载类别
     * @return 组织机构清单
     */
    @RequiresPermissions("userController:view")
    @RequestMapping("getUserJobOrgTree")
    @ResponseBody
    public List<Map<String, Object>> getUserJobOrgTree(String loginName, String jobName, String loadOrgType) {
        return this.us.getUserJobOrgTree(loginName, jobName, loadOrgType);
    }

    /**
     * <p>
     * Description: 加载用户关联的功能清单
     * </p>
     * 
     * @param loginName 用户名
     * @param jobName 岗位清单
     * @param roleName 角色清单
     * @param loadFunctionType 加载类别
     * @return 功能清单
     */
    @RequiresPermissions("userController:view")
    @RequestMapping("getUserJobRoleFunctionTree")
    @ResponseBody
    public List<Map<String, Object>> getUserJobRoleFunctionTree(String loginName, String jobName, String roleName,
            String loadFunctionType) {
        return this.us.getUserJobRoleFunctionTree(loginName, jobName, roleName, loadFunctionType);
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
}
