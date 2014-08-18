/**
 * IPageResult.java
 * Created at 2014年8月18日
 * Created by wangkang
 */
package com.llsfw.core.service.pagequery;

import com.llsfw.core.model.expand.PageResult;

/**
 * <p>
 * ClassName: IPageResult
 * </p>
 * <p>
 * Description: 分页接口
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年8月18日
 * </p>
 */
public interface IPageResult {

    /**
     * <p>
     * Description: 分页
     * </p>
     * 
     * @param sql sql语句
     * @param pageSize 页大小
     * @param curPage 当前页
     * @return 分页数据
     * @throws Exception 异常
     */
    public PageResult pageQuery(String sql, int pageSize, int curPage) throws Exception;

}
