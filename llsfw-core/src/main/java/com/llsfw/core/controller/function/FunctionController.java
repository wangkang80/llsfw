/**
 * FunctionController.java
 * Created at 2013-12-06
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.model.standard.TtFunction;
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
    @RequestMapping("deleteFunction")
    @ResponseBody
    public Map<String, Object> deleteFunction(String functionCode) {
        //删除
        this.fs.deleteFunction(functionCode);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 更新功能
     * </p>
     * 
     * @param s session对象
     * @param r 请求
     * @param functionCode 功能代码
     * @throws Exception 异常
     * @return 操作结果
     */
    @RequestMapping("editFunction")
    @ResponseBody
    public Map<String, Object> editFunction(HttpSession s, HttpServletRequest r, String functionCode) throws Exception {
        //获取登录名
        String loginName = null;
        loginName = this.getLoginName(s);

        //更新
        this.fs.editFunction(r.getParameterMap(), functionCode, loginName);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 获得功能对象
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 功能对象
     */
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
     * @param session session对象
     * @return 操作结果
     */
    @RequestMapping("addFunction")
    @ResponseBody
    public Map<String, Object> addFunction(HttpSession session, TtFunction tf) {
        //获取登录名
        String loginName = null;
        loginName = this.getLoginName(session);

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
    @RequestMapping("functionCodeUniqueCheck")
    @ResponseBody
    public boolean functionCodeUniqueCheck(String functionCode) {
        return this.fs.functionCodeUniqueCheck(functionCode);
    }

    @RequestMapping("getAppNode")
    @ResponseBody
    public List<Map<String, Object>> getAppNode() {
        return this.fs.getAppNode(true);
    }
}
