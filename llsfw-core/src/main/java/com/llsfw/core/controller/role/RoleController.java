/**
 * RoleController.java
 * Created at 2013-12-14
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.role;

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
import com.llsfw.core.model.standard.TtRole;
import com.llsfw.core.security.annotation.CurrentUser;
import com.llsfw.core.service.role.RoleService;

/**
 * <p>
 * ClassName: RoleController
 * </p>
 * <p>
 * Description: 角色维护
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月14日
 * </p>
 */
@Controller
@RequestMapping("roleController")
public class RoleController extends BaseController {
    /**
     * <p>
     * Field rs: 角色服务
     * </p>
     */
    @Autowired
    private RoleService rs;

    /**
     * <p>
     * Description: 跳转到角色功能关联界面
     * </p>
     * 
     * @param roleCode 角色代码
     * @param request 请求
     * @return 角色功能关联
     */
    @RequiresPermissions("roleController:function_add")
    @RequestMapping("toAddRoleFunction")
    public String toAddRoleFunction(String roleCode, HttpServletRequest request) {
        request.setAttribute(roleCode, roleCode);
        return "llsfw/role/addRoleFunction";
    }

    /**
     * <p>
     * Description: 返回功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    @RequiresPermissions("roleController:function_add")
    @RequestMapping("getFunctionList")
    @ResponseBody
    public List<Map<String, Object>> getFunctionList(String roleCode) {
        return this.rs.getFunctionList(roleCode);
    }

    /**
     * <p>
     * Description: 返回角色功能不包含的权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @return 角色功能不包含的权限列表
     */
    @RequiresPermissions("roleController:function_add")
    @RequestMapping("getFunctionPurviewList")
    @ResponseBody
    public List<Map<String, Object>> getFunctionPurviewList(String roleCode, String functionCode) {
        return this.rs.getFunctionPurviewList(roleCode, functionCode);
    }

    /**
     * <p>
     * Description: 添加角色的功能权限
     * </p>
     * 
     * @param loginName 用户名
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @param purviewCodes 权限代码
     * @return 操作结果
     */
    @RequiresPermissions("roleController:function_add")
    @RequestMapping("addRoleFunction")
    @ResponseBody
    public JsonResult<String> addRoleFunction(@CurrentUser String loginName, String roleCode, String functionCode,
            String purviewCodes) {
        return this.rs.addRoleFunction(loginName, roleCode, functionCode, purviewCodes);
    }

    /**
     * <p>
     * Description: 删除角色关联的功能权限
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    @RequiresPermissions("roleController:function_delete")
    @RequestMapping("deleteRoleFunction")
    @ResponseBody
    public JsonResult<String> deleteRoleFunction(String roleCode, String functionCode, String purviewCode) {
        return this.rs.deleteRoleFunction(roleCode, functionCode, purviewCode);
    }

    /**
     * <p>
     * Description: 根据角色代码返回所关联的功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("getRoleFunctionList")
    @ResponseBody
    public List<Map<String, Object>> getRoleFunctionList(String roleCode) {
        return this.rs.getRoleFunctionList(roleCode);
    }

    /**
     * <p>
     * Description: 删除角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 操作结果
     */
    @RequiresPermissions("roleController:delete")
    @RequestMapping("deleteRole")
    @ResponseBody
    public JsonResult<String> deleteRole(String roleCode) {
        return this.rs.deleteRole(roleCode);
    }

    /**
     * <p>
     * Description: 跳转到角色修改界面
     * </p>
     * 
     * @param roleCode 角色代码
     * @param request 请求
     * @return 角色修改界面
     */
    @RequiresPermissions("roleController:edit")
    @RequestMapping("toRoleEdit")
    public String toRoleEdit(String roleCode, HttpServletRequest request) {
        request.setAttribute(roleCode, roleCode);
        return "llsfw/role/roleEdit";
    }

    /**
     * <p>
     * Description: 更新角色
     * </p>
     * 
     * @param loginName 用户名
     * @param r 请求对象
     * @param roleCode 角色代码
     * @return 操作结果
     * @throws Exception 异常
     */
    @RequiresPermissions("roleController:edit")
    @RequestMapping("editRole")
    @ResponseBody
    public JsonResult<String> editRole(@CurrentUser String loginName, HttpServletRequest r, String roleCode)
            throws Exception {
        return this.rs.editRole(r.getParameterMap(), roleCode, loginName);
    }

    /**
     * <p>
     * Description: 加载单个角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 角色
     */
    @RequiresPermissions("roleController:add")
    @RequestMapping("loadRole")
    @ResponseBody
    public TtRole loadRole(String roleCode) {
        return this.rs.loadRole(roleCode);
    }

    /**
     * <p>
     * Description: 创建角色
     * </p>
     * 
     * @param session session对象
     * @param tr 角色对象
     * @return 操作结果
     */
    @RequiresPermissions("roleController:add")
    @RequestMapping("addRole")
    @ResponseBody
    public JsonResult<String> addRole(@CurrentUser String loginName, TtRole tr) {
        return this.rs.addRole(tr, loginName);
    }

    /**
     * <p>
     * Description: 校验角色是否唯一
     * </p>
     * 
     * @param roleCode 角色代码
     * @return true:通过,false:不通过
     */
    @RequiresPermissions("roleController:add")
    @RequestMapping("roleCodeUniqueCheck")
    @ResponseBody
    public boolean roleCodeUniqueCheck(String roleCode) {
        return this.rs.roleCodeUniqueCheck(roleCode);
    }

    /**
     * <p>
     * Description: 跳转到角色添加界面
     * </p>
     * 
     * @return 角色添加
     */
    @RequiresPermissions("roleController:add")
    @RequestMapping("toRoleAdd")
    public String toRoleAdd() {
        return "llsfw/role/roleAdd";
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("init")
    public String init() {
        return "llsfw/role/roleMain";
    }

    /**
     * <p>
     * Description: 返回角色列表
     * </p>
     * 
     * @return 角色列表
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("getRoleList")
    @ResponseBody
    public List<Map<String, Object>> getRoleList() {
        return this.rs.getRoleList();
    }
}
