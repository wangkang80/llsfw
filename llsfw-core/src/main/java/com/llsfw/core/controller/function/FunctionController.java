/**
 * FunctionController.java
 * Created at 2013-12-06
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.function;

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
import com.llsfw.core.model.standard.TtFunction;
import com.llsfw.core.model.standard.TtPurview;
import com.llsfw.core.security.annotation.CurrentUser;
import com.llsfw.core.service.function.FunctionService;

/**
 * <p>
 * ClassName: FunctionController
 * </p>
 * <p>
 * Description: 功能维护
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月6日
 * </p>
 */
@Controller
@RequestMapping("functionController")
public class FunctionController extends BaseController {

    /**
     * <p>
     * Field fs: 功能服务
     * </p>
     */
    @Autowired
    private FunctionService fs;

    /**
     * <p>
     * Description: 跳转到功能添加
     * </p>
     * 
     * @param levelNo 等级
     * @param parentFunctionCode 上级功能ID
     * @param request 请求
     * @return 功能添加界面
     */
    @RequiresPermissions("functionController:add")
    @RequestMapping("toFunctionAdd")
    public String toFunctionAdd(String levelNo, String parentFunctionCode, HttpServletRequest request) {
        request.setAttribute(levelNo, levelNo);
        request.setAttribute(parentFunctionCode, parentFunctionCode);
        return "llsfw/function/functionAdd";
    }

    /**
     * <p>
     * Description: 跳转到功能编辑界面
     * </p>
     * 
     * @param functionCode 功能代码
     * @param request 请求对象
     * @return 功能编辑界面
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("toFunctionEdit")
    public String toFunctionEdit(String functionCode, HttpServletRequest request) {
        request.setAttribute(functionCode, functionCode);
        return "llsfw/function/functionEdit";
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("init")
    public String init() {
        return "llsfw/function/functionMain";
    }

    /**
     * <p>
     * Description: 删除功能
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 操作结果
     */
    @RequiresPermissions("functionController:delete")
    @RequestMapping("deleteFunction")
    @ResponseBody
    public JsonResult<String> deleteFunction(String functionCode) {
        return this.fs.deleteFunction(functionCode);
    }

    /**
     * <p>
     * Description: 更新功能
     * </p>
     * 
     * @param loginName 当前登陆名称
     * @param r 请求
     * @param functionCode 功能代码
     * @throws Exception 异常
     * @return 操作结果
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("editFunction")
    @ResponseBody
    public JsonResult<String> editFunction(@CurrentUser String loginName, HttpServletRequest r, String functionCode)
            throws Exception {
        return this.fs.editFunction(r.getParameterMap(), functionCode, loginName);
    }

    /**
     * <p>
     * Description: 返回当前功能的上级列表
     * </p>
     * 
     * @param levelNo 级别编号
     * @return 当前功能的上级列表
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("loadParentFunction")
    @ResponseBody
    public List<TtFunction> loadParentFunction(Integer levelNo) {
        return this.fs.loadParentFunction(levelNo);
    }

    /**
     * <p>
     * Description: 获得功能对象
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 功能对象
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("loadFunction")
    @ResponseBody
    public TtFunction loadFunction(String functionCode) {
        return this.fs.loadFunction(functionCode);
    }

    /**
     * <p>
     * Description: 保存功能
     * </p>
     * 
     * @param tf 功能对象
     * @param loginName 登陆对象
     * @return 操作结果
     */
    @RequiresPermissions("functionController:add")
    @RequestMapping("addFunction")
    @ResponseBody
    public JsonResult<String> addFunction(@CurrentUser String loginName, TtFunction tf) {
        return this.fs.addFunction(tf, loginName);
    }

    /**
     * <p>
     * Description: 校验功能代码是否存在
     * </p>
     * 
     * @param functionCode 功能代码
     * @return false:不通过,true:通过
     */
    @RequiresPermissions("functionController:add")
    @RequestMapping("functionCodeUniqueCheck")
    @ResponseBody
    public boolean functionCodeUniqueCheck(String functionCode) {
        return this.fs.functionCodeUniqueCheck(functionCode);
    }

    /**
     * <p>
     * Description: 返回功能菜单数据
     * </p>
     * 
     * @return 功能菜单数据
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("getAppNode")
    @ResponseBody
    public List<Map<String, Object>> getAppNode() {
        return this.fs.getAppNode(true);
    }

    /**
     * <p>
     * Description: 获得权限列表
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 权限列表
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("getPurviewList")
    @ResponseBody
    public List<TtPurview> getPurviewList(String functionCode) {
        return this.fs.getPurviewList(functionCode);
    }

    /**
     * <p>
     * Description: 跳转到操作权限添加界面
     * </p>
     * 
     * @param functionCode 功能代码
     * @param request 请求对象
     * @return 添加界面
     */
    @RequiresPermissions("functionController:purview_add")
    @RequestMapping("toPurviewAdd")
    public String toPurviewAdd(String functionCode, HttpServletRequest request) {
        request.setAttribute("functionCode", functionCode);
        return "/llsfw/function/purviewAdd";
    }

    /**
     * <p>
     * Description: 添加操作权限
     * </p>
     * 
     * @param purview 操作权限
     * @return 结果
     */
    @RequiresPermissions("functionController:purview_add")
    @RequestMapping("purviewAdd")
    @ResponseBody
    public JsonResult<String> purviewAdd(@CurrentUser String loginName, TtPurview purview) {
        return this.fs.purviewAdd(loginName, purview);
    }

    /**
     * <p>
     * Description: 跳转到修改操作权限界面
     * </p>
     * 
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @param request 请求对象
     * @return
     */
    @RequiresPermissions("functionController:purview_edit")
    @RequestMapping("toPurviewEdit")
    public String toPurviewEdit(String functionCode, String purviewCode, HttpServletRequest request) {
        request.setAttribute("functionCode", functionCode);
        request.setAttribute("purviewCode", purviewCode);
        return "/llsfw/function/purviewEdit";
    }

    /**
     * <p>
     * Description: 加载操作权限
     * </p>
     * 
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作权限对象
     */
    @RequiresPermissions("functionController:purview_edit")
    @RequestMapping("loadPurview")
    @ResponseBody
    public TtPurview loadPurview(String functionCode, String purviewCode) {
        return this.fs.getPurview(functionCode, purviewCode);
    }

    /**
     * <p>
     * Description: 修改操作权限
     * </p>
     * 
     * @param valueMap 修改值
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @param loginName 登陆人
     * @return 操作结果
     * @throws Exception 异常
     */
    @RequiresPermissions("functionController:purview_edit")
    @RequestMapping("purviewEdit")
    @ResponseBody
    public JsonResult<String> purviewEdit(@CurrentUser String loginName, HttpServletRequest r, String functionCode,
            String purviewCode) throws Exception {
        return this.fs.purviewEdit(r.getParameterMap(), functionCode, purviewCode, loginName);
    }

    /**
     * <p>
     * Description: 删除操作权限
     * </p>
     * 
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    @RequiresPermissions("functionController:purview_delete")
    @RequestMapping("purviewDelete")
    @ResponseBody
    public JsonResult<String> purviewDelete(String functionCode, String purviewCode) {
        return this.fs.purviewDelete(functionCode, purviewCode);
    }
}
