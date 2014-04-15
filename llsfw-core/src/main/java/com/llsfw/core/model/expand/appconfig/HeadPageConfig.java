package com.llsfw.core.model.expand.appconfig;

import java.io.Serializable;

/**
 * <p>
 * ClassName: HeadPageConfig
 * </p>
 * <p>
 * Description: 头文件配置
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月17日
 * </p>
 */
public class HeadPageConfig implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -7388433286496391688L;

    /**
     * <p>
     * Field easyuiThemesName:
     * easyui的主题,有如下选择:default,black,bootstrap,gray,icons,metro
     * </p>
     */
    private String easyuiThemesName;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public HeadPageConfig() {
        this.easyuiThemesName = "default";
    }

    public String getEasyuiThemesName() {
        return easyuiThemesName;
    }

    public void setEasyuiThemesName(String easyuiThemesName) {
        this.easyuiThemesName = easyuiThemesName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
