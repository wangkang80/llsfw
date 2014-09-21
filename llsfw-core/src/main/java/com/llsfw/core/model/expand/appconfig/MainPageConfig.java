package com.llsfw.core.model.expand.appconfig;

import java.io.Serializable;

/**
 * <p>
 * ClassName: MainPageConfig
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
public class MainPageConfig implements Serializable {
    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 4933507399996636007L;

    /**
     * <p>
     * Field mainPagePath: 主页路径
     * </p>
     */
    private String mainPagePath;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public MainPageConfig() {
        this.mainPagePath = "";
    }

    public String getMainPagePath() {
        return mainPagePath;
    }

    public void setMainPagePath(String mainPagePath) {
        this.mainPagePath = mainPagePath;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
