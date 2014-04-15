/**
 * IFunctionExpMapper.java
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
 * ClassName: IFunctionExpMapper
 * </p>
 * <p>
 * Description: function扩展方法
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月20日
 * </p>
 */
public interface IFunctionExpMapper {
    /**
     * <p>
     * Description: 返回当前用户所拥有的功能代码
     * </p>
     * 
     * @param loginName 登陆名
     * @return 结果集
     */
    List<String> getUserHasFunctionCode(@Param("loginName") String loginName);

    /**
     * <p>
     * Description: 返回功能代码列表
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 结果集
     */
    List<String> getFunctionListCode(@Param("functionCodes") String functionCodes);

    /**
     * <p>
     * Description: 返回完整菜单列表
     * </p>
     * 
     * @param functionCodes 功能代码清单
     * @param parentFunctionCode 指定上级功能代码
     * @return 结果集
     */
    List<Map<String, Object>> getFunctionList(@Param("functionCodes") String functionCodes,
            @Param("parentFunctionCode") String parentFunctionCode);

    /**
     * <p>
     * Description: 返回当前用户所拥有的功能代码
     * </p>
     * 
     * @param loginName 登陆名
     * @param parentFunctionCode 指定上级功能代码
     * @return 结果集
     */
    List<Map<String, Object>> getUserHasFunction(@Param("loginName") String loginName,
            @Param("parentFunctionCode") String parentFunctionCode);

}
