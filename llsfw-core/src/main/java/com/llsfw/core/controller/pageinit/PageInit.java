/**
 * PageInit.java
 * Created at 2013-12-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.pageinit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.model.standard.TtServerGlobalParameters;
import com.llsfw.core.service.pageinit.PageInitService;

/**
 * <p>
 * ClassName: PageInit
 * </p>
 * <p>
 * Description: 页面初始化
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月4日
 * </p>
 */
@Controller
@RequestMapping("pageInit")
public class PageInit extends BaseController {

    /**
     * <p>
     * Field pis: 页面初始化服务
     * </p>
     */
    @Autowired
    private PageInitService pis;

    /**
     * <p>
     * Description: 获得指定参数
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 参数对象
     */
    @RequestMapping("getServerParam")
    @ResponseBody
    public TtServerGlobalParameters getServerParam(String parametersCode) {
        return this.pis.getPs().getServerGlobalParamters(parametersCode);
    }

    /**
     * <p>
     * Description: 获得指定参数列表
     * </p>
     * 
     * @param parametersTypeCode 参数类型
     * @return 参数对象列表
     */
    @RequestMapping("getServerParamByTypeCode")
    @ResponseBody
    public List<TtServerGlobalParameters> getServerParamByTypeCode(String parametersTypeCode) {
        return this.pis.getPs().getServerGlobalParamtersByTypeCode(parametersTypeCode);
    }
}
