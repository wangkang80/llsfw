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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.model.standard.TtRole;
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
    @RequestMapping("toAddRoleFunction")
    public String toAddRoleFunction(String roleCode, HttpServletRequest request) {
        request.setAttribute(roleCode, roleCode);
        return "llsfw/role/addRoleFunction";
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
    @RequestMapping("toRoleEdit")
    public String toRoleEdit(String roleCode, HttpServletRequest request) {
        request.setAttribute(roleCode, roleCode);
        return "llsfw/role/roleEdit";
    }

    /**
     * <p>
     * Description: 跳转到角色添加界面
     * </p>
     * 
     * @return 角色添加
     */
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
    @RequestMapping("init")
    public String init() {
        return "llsfw/role/roleMain";
    }

    /**
     * <p>
     * Description: 删除功能
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @return 操作结果
     */
    @RequestMapping("deleteRoleFunction")
    @ResponseBody
    public Map<String, Object> deleteRoleFunction(String roleCode, String functionCode) {
        return this.rs.deleteRoleFunction(roleCode, functionCode);
    }

    /**
     * <p>
     * Description: 为角色添加功能
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCodeList 功能代码列表
     * @param s session对象
     * @return 操作结果
     */
    @RequestMapping("addRoleFunction")
    @ResponseBody
    public Map<String, Object> addRoleFunction(String roleCode, String functionCodeList, HttpSession s) {
        //获取登录名
        String loginName = null;
        loginName = this.getLoginName(s);

        return this.rs.addRoleFunction(roleCode, functionCodeList, loginName);
    }

    /**
     * <p>
     * Description: 返回功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    @RequestMapping("getFunctionList")
    @ResponseBody
    public List<Map<String, Object>> getFunctionList(String roleCode) {
        return this.rs.getFunctionList(roleCode);
    }

    /**
     * <p>
     * Description: 根据角色代码返回所关联的功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
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
    @RequestMapping("deleteRole")
    @ResponseBody
    public Map<String, Object> deleteRole(String roleCode) {
        return this.rs.deleteRole(roleCode);
    }

    /**
     * <p>
     * Description: 更新角色
     * </p>
     * 
     * @param s session对象
     * @param r 请求对象
     * @param roleCode 角色代码
     * @return 操作结果
     * @throws Exception 异常
     */
    @RequestMapping("editRole")
    @ResponseBody
    public Map<String, Object> editRole(HttpSession s, HttpServletRequest r, String roleCode) throws Exception {
        //获取登录名
        String loginName = null;
        loginName = this.getLoginName(s);

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
    @RequestMapping("addRole")
    @ResponseBody
    public Map<String, Object> addRole(HttpSession session, TtRole tr) {
        //获取登录名
        String loginName = null;
        loginName = this.getLoginName(session);

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
    @RequestMapping("roleCodeUniqueCheck")
    @ResponseBody
    public boolean roleCodeUniqueCheck(String roleCode) {
        return this.rs.roleCodeUniqueCheck(roleCode);
    }

    /**
     * <p>
     * Description: 返回角色列表
     * </p>
     * 
     * @return 角色列表
     */
    @RequestMapping("getRoleList")
    @ResponseBody
    public List<Map<String, Object>> getRoleList() {
        return this.rs.getRoleList();
    }
}
