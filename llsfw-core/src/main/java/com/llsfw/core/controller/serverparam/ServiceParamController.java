/**
 * ServiceParamController.java
 * Created at 2013-12-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.serverparam;

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
import com.llsfw.core.model.standard.TtServerGlobalParameters;
import com.llsfw.core.service.serverparam.ServiceParamService;

/**
 * <p>
 * ClassName: ServiceParamController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月4日
 * </p>
 */
@Controller
@RequestMapping("serviceParamController")
public class ServiceParamController extends BaseController {

    /**
     * <p>
     * Field fsps: 服务
     * </p>
     */
    @Autowired
    private ServiceParamService fsps;

    /**
     * <p>
     * Description: 删除参数
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 操作结果
     */
    @RequiresPermissions("serviceParamController:delete")
    @RequestMapping("deleteParam")
    @ResponseBody
    public JsonResult<String> deleteParam(String parametersCode) {
        return this.fsps.deleteParam(parametersCode);
    }

    @RequiresPermissions("serviceParamController:edit")
    @RequestMapping("toServerParamEdit")
    public String toServerParamEdit(String PARAMETERS_CODE, HttpServletRequest request) {
        request.setAttribute("PARAMETERS_CODE", PARAMETERS_CODE);
        return "llsfw/serverParam/serverParamEdit";
    }

    /**
     * <p>
     * Description: 修改参数
     * </p>
     * 
     * @param request 请求对象
     * @param parametersCode 主键
     * @return 操作结果
     * @throws Exception 异常
     */
    @RequiresPermissions("serviceParamController:edit")
    @RequestMapping("editParameters")
    @ResponseBody
    public JsonResult<String> editParameters(HttpServletRequest request, String parametersCode) throws Exception {
        return this.fsps.editParameters(request.getParameterMap(), parametersCode);
    }

    /**
     * <p>
     * Description:加载参数对象
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 参数对象
     */
    @RequiresPermissions("serviceParamController:edit")
    @RequestMapping("loadParam")
    @ResponseBody
    public TtServerGlobalParameters loadParam(String parametersCode) {
        return this.fsps.loadParam(parametersCode);
    }

    /**
     * <p>
     * Description: 跳转到参数新增界面
     * </p>
     * 
     * @return 参数新增界面
     */
    @RequiresPermissions("serviceParamController:add")
    @RequestMapping("toServerParamAdd")
    public String toServerParamAdd() {
        return "llsfw/serverParam/serverParamAdd";
    }

    /**
     * <p>
     * Description: 保存参数
     * </p>
     * 
     * @param tsgp 需保存参数的对象
     * @return 返回保存结果
     */
    @RequiresPermissions("serviceParamController:add")
    @RequestMapping("addParameters")
    @ResponseBody
    public JsonResult<String> addParameters(TtServerGlobalParameters tsgp) {
        return this.fsps.addParameters(tsgp);
    }

    /**
     * <p>
     * Description: 校验参数代码是否重复
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return true:通过,false:不通过
     */
    @RequiresPermissions("serviceParamController:add")
    @RequestMapping("parametersCodeUniqueCheck")
    @ResponseBody
    public boolean parametersCodeUniqueCheck(String parametersCode) {
        return this.fsps.parametersCodeUniqueCheck(parametersCode);
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("serviceParamController:view")
    @RequestMapping("init")
    public String init() {
        return "llsfw/serverParam/serverParam";
    }

    /**
     * <p>
     * Description: 返回参数列表(无分页)
     * </p>
     * 
     * @param parametersCode 参数代码
     * @param parametersDesc 参数描述
     * @param parametersTypeCode 参数类型
     * @return 参数列表
     */
    @RequiresPermissions("serviceParamController:view")
    @RequestMapping("getParamsList")
    @ResponseBody
    public List<Map<String, Object>> getParamsList(String parametersCode, String parametersDesc) {
        return this.fsps.getParamsList(parametersCode, parametersDesc);
    }
}
