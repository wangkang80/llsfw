/**
 * OnlineListener.java
 * Created at 2013-12-18
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.llsfw.core.common.Constants;
import com.llsfw.core.common.SystemParam;
import com.llsfw.core.service.onlineuser.OnLineUserService;
import com.llsfw.core.service.serverparam.ParamService;

/**
 * <p>
 * ClassName: OnlineListener
 * </p>
 * <p>
 * Description: 系统监听器
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月18日
 * </p>
 */
public class OnlineListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
        HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener,
        ServletRequestListener, ServletRequestAttributeListener {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field ctx: spring上下文对象
     * </p>
     */
    private ApplicationContext ctx = null;

    /**
     * <p>
     * Field req: 请求对象
     * </p>
     */
    private HttpServletRequest req = null;

    /**
     * <p>
     * Description: 用户离线
     * </p>
     * 
     * @param session user
     */
    private void userOffline(HttpSession session) {
        if (session != null) {
            OnLineUserService olus = null;
            olus = this.ctx.getBean(OnLineUserService.class);
            ParamService ps = null;
            ps = this.ctx.getBean(ParamService.class);
            //获得session名称
            String name = null;
            name = ps.getServerParamter(SystemParam.SESSION_NAME.name());
            String ip = null;
            ip = Constants.getIpAddr(this.req);
            if (ip == null) {
                Object ipobj = null;
                ipobj = session.getAttribute("ip");
                ip = ipobj == null ? "0.0.0.0" : ipobj.toString();
            }
            this.log.info("==========OnlineListener.userOffline(HttpSession)-->name:" + name + "===========");
            this.log.info("==========OnlineListener.userOffline(HttpSession)-->ip:" + ip + "============");
            olus.userOffline(ip, session.getAttribute(name));
        }
    }

    /**
     * <p>
     * Description: 用户离线
     * </p>
     * 
     * @param sessionUser user
     * @param name name
     */
    private void userOffline(Object sessionUser, String name) {
        if (sessionUser != null) {
            OnLineUserService olus = null;
            olus = this.ctx.getBean(OnLineUserService.class);
            String ip = null;
            ip = Constants.getIpAddr(this.req);
            this.log.info("==========OnlineListener.userOffline(HttpSession)-->name:" + name + "==========");
            this.log.info("==========OnlineListener.userOffline(HttpSession)-->ip:" + ip + "==========");
            olus.userOffline(ip, sessionUser);
        }
    }

    /**
     * <p>
     * Description: 用户在线
     * </p>
     * 
     * @param session session
     * @param name name
     */
    private void userOnline(HttpSession session, String name) {
        OnLineUserService olus = null;
        olus = this.ctx.getBean(OnLineUserService.class);
        String ip = null;
        ip = Constants.getIpAddr(this.req);
        session.setAttribute("ip", ip); //记录请求IP
        this.log.info("==========OnlineListener.userOnline(HttpSession,String)-->name:" + name + "==========");
        this.log.info("==========OnlineListener.userOnline(HttpSession,String)-->ip:" + ip + "==========");
        olus.userOnline(ip, session.getAttribute(name));
    }

    //-------------------context-------------------------

    //服务器初始化
    @Override
    public void contextInitialized(ServletContextEvent e) {
        this.log.info("==========OnlineListener.contextInitialized(ServletContextEvent)==========");
        this.ctx = WebApplicationContextUtils.getWebApplicationContext(e.getServletContext());
    }

    //添加服务器参数
    @Override
    public void attributeAdded(ServletContextAttributeEvent e) {
    }

    //替换服务器参数
    @Override
    public void attributeReplaced(ServletContextAttributeEvent e) {

    }

    //移除服务器参数
    @Override
    public void attributeRemoved(ServletContextAttributeEvent e) {
    }

    //服务器销毁
    @Override
    public void contextDestroyed(ServletContextEvent e) {
    }

    //-------------------request-------------------------

    //请求对象初始化
    @Override
    public void requestInitialized(ServletRequestEvent e) {
        this.log.debug("==========OnlineListener.requestInitialized(ServletRequestEvent)==========");
        this.req = (HttpServletRequest) e.getServletRequest();
    }

    //添加请求对象参数
    @Override
    public void attributeAdded(ServletRequestAttributeEvent e) {

    }

    //替换请求对象参数
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent e) {
    }

    //移除请求对象参数
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent e) {
    }

    //请求对象销毁
    @Override
    public void requestDestroyed(ServletRequestEvent e) {
    }

    //-------------------session(binding)-------------------------

    //添加session参数
    @Override
    public void attributeAdded(HttpSessionBindingEvent e) {
        this.log.info("==========OnlineListener.attributeAdded(HttpSessionBindingEvent)==========");
        this.userOnline(e.getSession(), e.getName());
    }

    //移除session参数
    @Override
    public void attributeRemoved(HttpSessionBindingEvent e) {
        this.log.info("==========OnlineListener.attributeRemoved(HttpSessionBindingEvent)==========");
        this.userOffline(e.getValue(), e.getName());
    }

    //替换session参数
    @Override
    public void attributeReplaced(HttpSessionBindingEvent e) {
    }

    //参数绑定session
    @Override
    public void valueBound(HttpSessionBindingEvent e) {
    }

    //参数解除session绑定
    @Override
    public void valueUnbound(HttpSessionBindingEvent e) {
        this.log.info("==========OnlineListener.valueUnbound(HttpSessionBindingEvent)==========");
        this.userOffline(e.getValue(), e.getName());
    }

    //-------------------session-------------------------

    //session创建
    @Override
    public void sessionCreated(HttpSessionEvent e) {

    }

    //session销毁
    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        this.log.info("==========OnlineListener.sessionDestroyed(HttpSessionEvent e)==========");
        this.userOffline(e.getSession());
    }

    //session被激活
    @Override
    public void sessionDidActivate(HttpSessionEvent e) {
    }

    //session被迁移
    @Override
    public void sessionWillPassivate(HttpSessionEvent e) {
    }

}
