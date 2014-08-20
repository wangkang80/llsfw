/**
 * SystemParamDef.java
 * Created at 2014年3月7日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: SystemParamDef
 * </p>
 * <p>
 * Description: 默认系统参数
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月7日
 * </p>
 */
public class SystemParamDef {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field data: 存储的系统参数map
     * </p>
     */
    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
        this.log.info("data:" + this.data);
    }
}
