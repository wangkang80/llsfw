/**
 * PageResultMapperOracle.java
 * Created at 2013-12-02
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.mapper.expand;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ClassName: IPageResultMapperOracle
 * </p>
 * <p>
 * Description: 分页查询
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月2日
 * </p>
 */
public interface IPageResultMapperOracle {

    /**
     * <p>
     * Description: 查询总量
     * </p>
     * 
     * @param sql sql语句
     * @return 总量
     */
    int getCount(@Param("sql") String sql);

    /**
     * <p>
     * Description: 分页查询
     * </p>
     * 
     * @param sql sql语句
     * @param pageSize 页条数
     * @param curPage 当前业
     * @return 结果集
     */
    List<Map<String, Object>> pageQuery(@Param("sql") String sql, @Param("pageSize") int pageSize,
            @Param("curPage") int curPage);
}
