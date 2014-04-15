package com.llsfw.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.llsfw.core.model.expand.appconfig.AppConfig;

/**
 * <p>
 * ClassName: AppConfigListener
 * </p>
 * <p>
 * Description: 初始化系统配置
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月17日
 * </p>
 */
public class AppConfigListener implements ServletContextListener {

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

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.log.info("==========AppConfigListener.contextInitialized(ServletContextEvent)==========");
        this.ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());

        //获取系统配置,放入ServletContext中
        AppConfig appConfig = this.ctx.getBean(AppConfig.class);
        sce.getServletContext().setAttribute("appConfig", appConfig);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
