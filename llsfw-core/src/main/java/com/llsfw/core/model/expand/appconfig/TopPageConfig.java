package com.llsfw.core.model.expand.appconfig;

import java.io.Serializable;

/**
 * <p>
 * ClassName: TopPageConfig
 * </p>
 * <p>
 * Description: 顶部页面配置
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月17日
 * </p>
 */
public class TopPageConfig implements Serializable {
    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 4933507399996636007L;

    /**
     * <p>
     * Field topPageBackgroundImagePath: 顶部界面背景图片(尺寸6*62)
     * </p>
     */
    private String topPageBackgroundImagePath;

    /**
     * <p>
     * Field topPageLogoImagePath: 顶部界面logo图片(尺寸370*62)
     * </p>
     */
    private String topPageLogoImagePath;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public TopPageConfig() {
        this.topPageBackgroundImagePath = "llsfw/img/main_2.gif";
        this.topPageLogoImagePath = "llsfw/img/main_1.jpg";
    }

    public String getTopPageBackgroundImagePath() {
        return topPageBackgroundImagePath;
    }

    public void setTopPageBackgroundImagePath(String topPageBackgroundImagePath) {
        this.topPageBackgroundImagePath = topPageBackgroundImagePath;
    }

    public String getTopPageLogoImagePath() {
        return topPageLogoImagePath;
    }

    public void setTopPageLogoImagePath(String topPageLogoImagePath) {
        this.topPageLogoImagePath = topPageLogoImagePath;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
