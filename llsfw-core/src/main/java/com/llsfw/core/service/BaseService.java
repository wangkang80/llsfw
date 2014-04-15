/**
 * BaseService.java
 * Created at 2013-12-02
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.mapper.expand.IMapQueryMapper;
import com.llsfw.core.service.pagequery.OracelPageResultService;
import com.llsfw.core.service.serverparam.ParamService;

/**
 * <p>
 * ClassName: BaseService
 * </p>
 * <p>
 * Description: 根服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月2日
 * </p>
 */
@Service
public class BaseService {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field ps: 参数获取服务
     * </p>
     */
    @Autowired
    private ParamService ps;

    /**
     * <p>
     * Field prs: 分页服务
     * </p>
     */
    @Autowired
    private OracelPageResultService prs;

    /**
     * <p>
     * Field imqm: List<Map>查询方法
     * </p>
     */
    @Autowired
    private IMapQueryMapper imqm;

    public IMapQueryMapper getImqm() {
        return this.imqm;
    }

    public void setImqm(IMapQueryMapper imqm) {
        this.imqm = imqm;
    }

    public OracelPageResultService getPrs() {
        return this.prs;
    }

    public void setPrs(OracelPageResultService prs) {
        this.prs = prs;
    }

    public ParamService getPs() {
        return this.ps;
    }

    public void setPs(ParamService ps) {
        this.ps = ps;
    }

}