/**
 * MySqllPageResultService.java
 * Created at 2013-12-02
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.pagequery;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.SystemParam;
import com.llsfw.core.mapper.expand.IPageResultMapperMySql;
import com.llsfw.core.model.expand.PageResult;
import com.llsfw.core.service.serverparam.ParamService;

/**
 * <p>
 * ClassName: MySqllPageResultService
 * </p>
 * <p>
 * Description: 分页服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月2日
 * </p>
 */
@Service
public class MySqllPageResultService implements IPageResult {

    /**
     * <p>
     * Field iq: 查询接口
     * </p>
     */
    @Autowired
    private IPageResultMapperMySql iq;

    /**
     * <p>
     * Field ps: 参数获取服务
     * </p>
     */
    @Autowired
    private ParamService ps;

    /**
     * <p>
     * Description: 分页方法
     * </p>
     * 
     * @param sql sql语句
     * @param pageSize 页大小
     * @param curPage 第几页
     * @return 分页结果集
     * @throws Exception 未知异常
     */
    public PageResult pageQuery(String sql, int pageSize, int curPage) throws Exception {
        if (pageSize < 1) {
            final String PS = this.ps.getServerParamter(SystemParam.PAGE_SIZE.name());
            pageSize = new Integer(PS);
        }
        if (curPage < 1) {
            curPage = 1;
        }
        final PageResult PRET = new PageResult();
        PRET.setPageSize(pageSize); // 设置每页条数
        PRET.setTotalRecords(this.iq.getCount(sql)); // 设置总条数
        if (PRET.getTotalRecords() > 0) {
            final int TOTAL_PAGES = PRET.getTotalRecords() / pageSize
                    + ((PRET.getTotalRecords() % pageSize > 0) ? 1 : 0);
            PRET.setTotalPages(TOTAL_PAGES); // 设置总页数
            if (curPage > PRET.getTotalPages()) {
                PRET.setCurPage(PRET.getTotalPages());
            } else {
                PRET.setCurPage(curPage);
            }
            PRET.setRecords(this.iq.pageQuery(sql, pageSize, (curPage - 1) * pageSize));
        } else {
            PRET.setCurPage(0);
            PRET.setTotalPages(0);
            PRET.setRecords(new ArrayList<Map<String, Object>>());
        }
        return PRET;
    }
}
