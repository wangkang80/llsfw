package com.llsfw.core.model.expand.appconfig;

import java.io.Serializable;

/**
 * <p>
 * ClassName: LoginPageConfig
 * </p>
 * <p>
 * Description: 登陆页面配置
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月17日
 * </p>
 */
public class LoginPageConfig implements Serializable {
    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 4933507399996636007L;

    /**
     * <p>
     * Field loginPageBackgroundImagePath: 登陆界面背景图片(尺寸5*5)
     * </p>
     */
    private String loginPageBackgroundImagePath;

    /**
     * <p>
     * Field loginPageTileBackgroundImagePath: 登陆界面标题图片(尺寸4*62)
     * </p>
     */
    private String loginPageTileBackgroundImagePath;

    /**
     * <p>
     * Field loginPageLogoImagePath: 登陆界面logo图片(尺寸390*62)
     * </p>
     */
    private String loginPageLogoImagePath;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public LoginPageConfig() {
        this.loginPageBackgroundImagePath = "llsfw/img/bg.gif";
        this.loginPageTileBackgroundImagePath = "llsfw/img/login_1.jpg";
        this.loginPageLogoImagePath = "llsfw/img/login_2.jpg";
    }

    public String getLoginPageBackgroundImagePath() {
        return loginPageBackgroundImagePath;
    }

    public void setLoginPageBackgroundImagePath(String loginPageBackgroundImagePath) {
        this.loginPageBackgroundImagePath = loginPageBackgroundImagePath;
    }

    public String getLoginPageTileBackgroundImagePath() {
        return loginPageTileBackgroundImagePath;
    }

    public void setLoginPageTileBackgroundImagePath(String loginPageTileBackgroundImagePath) {
        this.loginPageTileBackgroundImagePath = loginPageTileBackgroundImagePath;
    }

    public String getLoginPageLogoImagePath() {
        return loginPageLogoImagePath;
    }

    public void setLoginPageLogoImagePath(String loginPageLogoImagePath) {
        this.loginPageLogoImagePath = loginPageLogoImagePath;
    }

}
