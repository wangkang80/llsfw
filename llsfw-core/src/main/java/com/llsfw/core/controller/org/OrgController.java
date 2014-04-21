/**
 * OrgController.java
 * Created at 2014年5月2日
 * Created by wangkang
 */
package com.llsfw.core.controller.org;

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
import com.llsfw.core.model.standard.TtJob;
import com.llsfw.core.model.standard.TtOrganization;
import com.llsfw.core.security.annotation.CurrentUser;
import com.llsfw.core.service.org.OrgService;

/**
 * <p>
 * ClassName: OrgController
 * </p>
 * <p>
 * Description: 组织机构维护
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年5月2日
 * </p>
 */
@Controller
@RequestMapping("orgController")
public class OrgController extends BaseController {

    /**
     * <p>
     * Field os: 组织机构服务
     * </p>
     */
    @Autowired
    private OrgService os;

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("orgController:view")
    @RequestMapping("init")
    public String init() {
        return "llsfw/org/orgMain";
    }

    /**
     * <p>
     * Description: 获得组织树
     * </p>
     * 
     * @return 组织树
     */
    @RequiresPermissions("orgController:view")
    @RequestMapping("getOrgNode")
    @ResponseBody
    public List<Map<String, Object>> getOrgNode() {
        return this.os.getOrgNode(null);
    }

    /**
     * <p>
     * Description: 跳转到新增组织界面
     * </p>
     * 
     * @param parentOrgCode 上级组织代码
     * @param request 请求对象
     * @return 新增组织界面
     */
    @RequiresPermissions("orgController:add")
    @RequestMapping("toOrgAdd")
    public String toOrgAdd(String parentOrgCode, HttpServletRequest request) {
        request.setAttribute("parentOrgCode", parentOrgCode);
        return "llsfw/org/orgAdd";
    }

    /**
     * <p>
     * Description: 校验组织代码是否存在
     * </p>
     * 
     * @param orgCode 组织代码
     * @return false:不通过,true:通过
     */
    @RequiresPermissions("orgController:add")
    @RequestMapping("orgCodeUniqueCheck")
    @ResponseBody
    public boolean orgCodeUniqueCheck(String orgCode) {
        return this.os.orgCodeUniqueCheck(orgCode);
    }

    /**
     * <p>
     * Description: 添加组织
     * </p>
     * 
     * @param loginName 登陆人
     * @param to 组织对象
     * @return 操作结果
     */
    @RequiresPermissions("orgController:add")
    @RequestMapping("addOrg")
    @ResponseBody
    public JsonResult<String> addOrg(@CurrentUser String loginName, TtOrganization to) {
        return this.os.addOrg(loginName, to);
    }

    /**
     * <p>
     * Description: 跳转到修改组织界面
     * </p>
     * 
     * @param orgCode 组织代码
     * @param request 请求对象
     * @return 修改组织界面
     */
    @RequiresPermissions("orgController:edit")
    @RequestMapping("toOrgEdit")
    public String toOrgEdit(String orgCode, HttpServletRequest request) {
        request.setAttribute("orgCode", orgCode);
        return "llsfw/org/orgEdit";
    }

    /**
     * <p>
     * Description: 加载组织对象
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 组织对象
     */
    @RequiresPermissions("orgController:edit")
    @RequestMapping("loadOrg")
    @ResponseBody
    public TtOrganization loadOrg(String orgCode) {
        return this.os.loadOrg(orgCode);
    }

    /**
     * <p>
     * Description: 获得组织树
     * </p>
     * 
     * @return 组织树
     */
    @RequiresPermissions("orgController:edit")
    @RequestMapping("getOrgNodeTree")
    @ResponseBody
    public List<Map<String, Object>> getOrgNodeTree() {
        List<Map<String, Object>> nodes = this.os.getOrgNode(null);
        return this.os.getOrgNodeTree(nodes);
    }

    /**
     * <p>
     * Description: 更新组织
     * </p>
     * 
     * @param loginName 当前登陆名称
     * @param r 请求
     * @param editOrg 功能代码
     * @throws Exception 异常
     * @return 操作结果
     */
    @RequiresPermissions("orgController:edit")
    @RequestMapping("editOrg")
    @ResponseBody
    public JsonResult<String> editOrg(@CurrentUser String loginName, HttpServletRequest r, String orgCode)
            throws Exception {
        return this.os.editOrg(r.getParameterMap(), orgCode, loginName);
    }

    /**
     * <p>
     * Description: 删除组织
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 操作结果
     */
    @RequiresPermissions("orgController:delete")
    @RequestMapping("deleteOrg")
    @ResponseBody
    public JsonResult<String> deleteOrg(String orgCode) {
        return this.os.deleteOrg(orgCode, null);
    }

    /**
     * <p>
     * Description: 根据组织代码反馈岗位列表
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 岗位列表
     */
    @RequiresPermissions("orgController:view")
    @RequestMapping("getJob")
    @ResponseBody
    public List<Map<String, Object>> getJob(String orgCode) {
        return this.os.getJob(orgCode);
    }

    /**
     * <p>
     * Description: 跳转到新增岗位界面
     * </p>
     * 
     * @param orgCode 组织代码
     * @param request 请求对象
     * @return 新增岗位界面
     */
    @RequiresPermissions("orgController:job_add")
    @RequestMapping("toJobAdd")
    public String toJobAdd(String orgCode, HttpServletRequest request) {
        request.setAttribute("orgCode", orgCode);
        return "llsfw/org/jobAdd";
    }

    /**
     * <p>
     * Description: 判断岗位代码是否存在
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 判断结果
     */
    @RequiresPermissions("orgController:job_add")
    @RequestMapping("jobCodeUniqueCheck")
    @ResponseBody
    public boolean jobCodeUniqueCheck(String jobCode) {
        return this.os.orgCodeUniqueCheck(jobCode);
    }

    /**
     * <p>
     * Description: 添加岗位
     * </p>
     * 
     * @param loginName 登陆人
     * @param tj 岗位对象
     * @return 操作结果
     */
    @RequiresPermissions("orgController:job_add")
    @RequestMapping("addJob")
    @ResponseBody
    public JsonResult<String> addJob(@CurrentUser String loginName, TtJob tj) {
        return this.os.addJob(loginName, tj);
    }

    /**
     * <p>
     * Description: 跳转到修改岗位界面
     * </p>
     * 
     * @param jobCode 岗位代码
     * @param request 请求对象
     * @return 修改岗位界面
     */
    @RequiresPermissions("orgController:job_edit")
    @RequestMapping("toJobEdit")
    public String toJobEdit(String jobCode, HttpServletRequest request) {
        request.setAttribute("jobCode", jobCode);
        return "llsfw/org/jobEdit";
    }

    /**
     * <p>
     * Description: 加载岗位对象
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 岗位对象
     */
    @RequiresPermissions("orgController:job_edit")
    @RequestMapping("loadJob")
    @ResponseBody
    public TtJob loadJob(String jobCode) {
        return this.os.loadJob(jobCode);
    }

    /**
     * <p>
     * Description: 更新岗位
     * </p>
     * 
     * @param valueMap 提交的更新值
     * @param orgCode 岗位代码
     * @param loginName 操作人
     * @return 操作结果
     * @throws Exception 异常
     */
    @RequiresPermissions("orgController:job_edit")
    @RequestMapping("editJob")
    @ResponseBody
    public JsonResult<String> editJob(@CurrentUser String loginName, HttpServletRequest r, String jobCode)
            throws Exception {
        return this.os.editJob(r.getParameterMap(), jobCode, loginName);
    }

    /**
     * <p>
     * Description: 删除岗位
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 操作结果
     */
    @RequiresPermissions("orgController:job_delete")
    @RequestMapping("deleteJob")
    @ResponseBody
    public JsonResult<String> deleteJob(String jobCode) {
        return this.os.deleteJob(jobCode);
    }

    /**
     * <p>
     * Description: 根据组织代码反馈岗位列表
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 岗位列表
     */
    @RequiresPermissions("orgController:view")
    @RequestMapping("getRole")
    @ResponseBody
    public List<Map<String, Object>> getRole(String jobCode) {
        return this.os.getRole(jobCode);
    }

    /**
     * <p>
     * Description: 跳转到岗位角色关联界面
     * </p>
     * 
     * @param jobCode 岗位代码
     * @param request 请求
     * @return 岗位角色关联界面
     */
    @RequiresPermissions("orgController:role_add")
    @RequestMapping("toAddRole")
    public String toAddRole(String jobCode, HttpServletRequest request) {
        request.setAttribute("jobCode", jobCode);
        return "llsfw/org/roleAdd";
    }

    /**
     * <p>
     * Description: 根据岗位返回不存在角色列表
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 角色列表
     */
    @RequiresPermissions("orgController:role_add")
    @RequestMapping("getRoleList")
    @ResponseBody
    public List<Map<String, Object>> getRoleList(String jobCode) {
        return this.os.getRoleList(jobCode);
    }

    /**
     * <p>
     * Description: 添加岗位角色关联
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCode 岗位代码
     * @param roleCodeList 角色列表
     * @return 操作结果
     */
    @RequiresPermissions("orgController:role_add")
    @RequestMapping("addRole")
    @ResponseBody
    public JsonResult<String> addRole(@CurrentUser String loginName, String jobCode, String roleCodeList) {
        return this.os.addRole(loginName, jobCode, roleCodeList);
    }

    /**
     * <p>
     * Description: 删除角色与岗位的关联
     * </p>
     * 
     * @param jobCode 岗位代码
     * @param roleCode 角色代码
     * @return 操作结果
     */
    @RequiresPermissions("orgController:role_delete")
    @RequestMapping("deleteRole")
    @ResponseBody
    public JsonResult<String> deleteRole(String jobCode, String roleCode) {
        return this.os.deleteRole(jobCode, roleCode);
    }
}
