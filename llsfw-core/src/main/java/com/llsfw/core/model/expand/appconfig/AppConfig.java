package com.llsfw.core.model.expand.appconfig;

import java.io.Serializable;

/**
 * <p>
 * ClassName: AppConfig
 * </p>
 * <p>
 * Description: 系统配置
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月17日
 * </p>
 */
public class AppConfig implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -4727935109014229745L;

    /**
     * <p>
     * Field appTitle: 应用程序标题
     * </p>
     */
    private String appTitle;

    /**
     * <p>
     * Field headPageConfig: 头页面配置
     * </p>
     */
    private HeadPageConfig headPageConfig;

    /**
     * <p>
     * Field loginPageConfig: 登陆界面配置
     * </p>
     */
    private LoginPageConfig loginPageConfig;

    /**
     * <p>
     * Field topPageConfig: 顶部界面配置
     * </p>
     */
    private TopPageConfig topPageConfig;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public AppConfig() {
        this.appTitle = "llsfw快速开发平台";
        this.headPageConfig = new HeadPageConfig();
        this.loginPageConfig = new LoginPageConfig();
        this.topPageConfig = new TopPageConfig();
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public TopPageConfig getTopPageConfig() {
        return topPageConfig;
    }

    public void setTopPageConfig(TopPageConfig topPageConfig) {
        this.topPageConfig = topPageConfig;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public LoginPageConfig getLoginPageConfig() {
        return loginPageConfig;
    }

    public void setLoginPageConfig(LoginPageConfig loginPageConfig) {
        this.loginPageConfig = loginPageConfig;
    }

    public HeadPageConfig getHeadPageConfig() {
        return headPageConfig;
    }

    public void setHeadPageConfig(HeadPageConfig headPageConfig) {
        this.headPageConfig = headPageConfig;
    }

}
