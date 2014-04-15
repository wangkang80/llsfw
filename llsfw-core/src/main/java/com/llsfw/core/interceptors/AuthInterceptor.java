/**
 * AuthInterceptor.java
 * Created at 2013-11-30
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.interceptors;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.llsfw.core.common.Constants;
import com.llsfw.core.common.SystemParam;
import com.llsfw.core.model.expand.LoginUser;
import com.llsfw.core.service.AuthService;
import com.llsfw.core.service.serverparam.ParamService;

/**
 * <p>
 * ClassName: AuthInterceptor
 * </p>
 * <p>
 * Description: 权限拦截器
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年11月30日
 * </p>
 */
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * <p>
     * Field logger: 日志记录
     * </p>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * <p>
     * Field pss: 参数服务
     * </p>
     */
    @Autowired
    private ParamService pss;

    /**
     * <p>
     * Field as: 权限服务
     * </p>
     */
    @Autowired
    private AuthService as;

    /**
     * <p>
     * Field noSessionControll: 不校验session的controll
     * </p>
     */
    private List<String> noSessionControll;
    /**
     * <p>
     * Field noAuthControll: 不校验权限的controll
     * </p>
     */
    private List<String> noAuthControll;

    public static void main(String[] arge) {
        System.out.println();
    }

    /**
     * <p>
     * Description: 构造方法
     * </p>
     * 
     * @param noAuthControll 不校验权限的controll
     * @param noSessionControll 不校验session的controll
     */
    @SuppressWarnings("unchecked")
    public AuthInterceptor(String noAuthControll, String noSessionControll) {
        //初始化集合
        this.noAuthControll = new ArrayList<String>();
        this.noSessionControll = new ArrayList<String>();

        //设置定义值
        if (!StringUtils.isEmpty(noAuthControll)) {
            this.noAuthControll.addAll(CollectionUtils.arrayToList(StringUtils.split(noAuthControll, ",")));
        }
        if (!StringUtils.isEmpty(noAuthControll)) {
            this.noSessionControll.addAll(CollectionUtils.arrayToList(StringUtils.split(noSessionControll, ",")));
        }

        //设置默认值
        this.noAuthControll.add("pageInit");//页面初始化
        this.noAuthControll.add("loginController");//登陆 
        this.noAuthControll.add("mainController");//系统主框架

        this.noSessionControll.add("pageInit");//页面初始化
        this.noSessionControll.add("loginController");//登陆

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String REQUEST_URI = null;
        REQUEST_URI = request.getRequestURI();
        String contextPath = request.getContextPath() + "/";

        //如果请求的是 / 则直接跳过
        if (REQUEST_URI.equals(contextPath)) {
            return true;
        }

        //获得session
        String sessionName = null;
        sessionName = this.pss.getServerParamter(SystemParam.SESSION_NAME.name());
        Object sessionObject = null;
        sessionObject = request.getSession().getAttribute(sessionName);

        //设置输出格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        LOGGER.info("==============================REQUEST_URI:" + REQUEST_URI);

        //判断session是否为空
        if (null == sessionObject) {
            // 过滤掉不需要校验的controller
            for (String controller : this.noSessionControll) {
                if (REQUEST_URI.toUpperCase().indexOf(controller.toUpperCase()) > 0) {
                    return true;
                }
            }
            this.notAuth(request, response, "timeout", "由于您长时间没有操作, session已过期, 请重新登录.");
            return false;
        }

        //判断是否有操作权限
        LoginUser user = null;
        user = (LoginUser) sessionObject;
        List<Map<String, Object>> functionList = null;
        functionList = this.as.getUserFunction(user.getUser().getLoginName());
        boolean rv = false;
        rv = this.functionCheck(functionList, REQUEST_URI);
        if (!rv) {
            // 过滤掉不需要校验的controller
            for (String controller : this.noAuthControll) {
                if (REQUEST_URI.toUpperCase().indexOf(controller.toUpperCase()) > 0) {
                    rv = true;
                }
            }
            if (!rv) {
                this.notAuth(request, response, "noAuth", "您访问了一个未经授权的资源,请使用有权限访问该资源的账号登录系统.");
            }
        }
        return rv;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse rep, Object ob, Exception exc)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse rep, Object ob, ModelAndView mav)
            throws Exception {

    }

    /**
     * <p>
     * Description: 校验是否有权限访问资源
     * </p>
     * 
     * @param functionList 功能列表
     * @param requestUrl 路径
     * @return true:有权限,false:没权限
     */
    private boolean functionCheck(List<Map<String, Object>> functionList, String requestUrl) {
        if (!CollectionUtils.isEmpty(functionList)) {
            for (Map<String, Object> function : functionList) {
                String functionCode = null;
                functionCode = function.get("FUNCTION_CODE").toString();
                if (requestUrl.toUpperCase().indexOf((functionCode.toUpperCase())) > 0) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * <p>
     * Description: 权限校验失败后的行为
     * </p>
     * 
     * @param req 请求
     * @param res 响应
     * @param ss 状态
     * @param msg 信息
     * @throws Exception 异常
     */
    private void notAuth(HttpServletRequest req, HttpServletResponse res, String ss, String msg) throws Exception {
        //判断请求模式(普通,Ajax)
        String requestType = null;
        requestType = req.getHeader("X-Requested-With");
        if (!StringUtils.isEmpty(requestType) && "XMLHttpRequest".equalsIgnoreCase(requestType)) {
            res.setHeader("sessionstatus", ss);
            res.setHeader("path", req.getContextPath());
            res.sendError(Constants.HTTP_STATUS_518, ss);
        } else {
            String jsCode = null;
            jsCode = "<script type='text/javascript'>" + "alert('" + msg + "');"
                    + "var p=window;while(p!=p.parent){p=p.parent; } p.location.href='" + req.getContextPath()
                    + "/loginController/index'</script>";
            PrintWriter writer = null;
            writer = res.getWriter();
            writer.print(jsCode);
            writer.flush();
            writer.close();
        }
    }
}
