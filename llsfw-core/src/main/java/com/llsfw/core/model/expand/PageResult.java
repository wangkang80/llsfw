/**
 * PageResult.java
 * Created at 2013-12-02
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.model.expand;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * ClassName: PageResult
 * </p>
 * <p>
 * Description: 分页类
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月2日
 * </p>
 */
public class PageResult {
    /**
     * 当前页,默认1
     */
    private int curPage = 1;

    /**
     * 每页数量,默认0
     */
    private int pageSize = 0;

    /**
     * 总页数
     */
    private int totalPages = 0;

    /**
     * 总条数
     */
    private int totalRecords = 0;

    /**
     * 结果集
     */
    private List<Map<String, Object>> records = null;

    public int getCurPage() {
        return this.curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return this.totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<Map<String, Object>> getRecords() {
        return this.records;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }
}
