/**
 * MapQueryMapper.java
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
 * ClassName: IMapQueryMapper
 * </p>
 * <p>
 * Description: 查询
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月2日
 * </p>
 */
public interface IMapQueryMapper {
    /**
     * <p>
     * Description: 传入复杂SQL语句,反馈Map集合
     * </p>
     * 
     * @param sql sql语句
     * @return Map集合
     */
    List<Map<String, Object>> queryMap(@Param("sql") String sql);

    /**
     * <p>
     * Description: 查询单行单列
     * </p>
     * 
     * @param sql sql语句
     * @return 结果
     */
    String queryOneRowOneValue(@Param("sql") String sql);

    /**
     * <p>
     * Description: 删除
     * </p>
     * 
     * @param sql sql语句
     */
    void delete(@Param("sql") String sql);

    /**
     * <p>
     * Description: 更新
     * </p>
     * 
     * @param sql sql语句
     */
    void update(@Param("sql") String sql);

    /**
     * <p>
     * Description: 新增
     * </p>
     * 
     * @param sql sql语句
     */
    void insert(@Param("sql") String sql);
}
