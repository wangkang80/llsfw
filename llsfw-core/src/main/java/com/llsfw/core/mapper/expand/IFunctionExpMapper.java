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
     * Description: 返回角色功能不包含的权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @return 角色功能不包含的权限列表
     */
    List<Map<String, Object>> findNotIncludePurviewByRoleFunction(@Param("roleCode") String roleCode,
            @Param("functionCode") String functionCode);

    /**
     * <p>
     * Description: 返回角色不包含的功能权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 角色不包含的功能权限列表
     */
    List<Map<String, Object>> findNotIncludeFunctionPurviewByRole(@Param("roleCode") String roleCode);

    /**
     * <p>
     * Description: 返回角色所包含的功能权限
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 角色所包含的功能权限
     */
    List<Map<String, Object>> findFunctionPurviewByRole(@Param("roleCode") String roleCode);

    /**
     * <p>
     * Description: 返回上级功能代码列表
     * </p>
     * 
     * @param functionCodeList 功能代码列表
     * @return 结果集
     */
    List<String> getParentFunctionListCode(@Param("functionCodeList") List<String> functionCodeList);

    /**
     * <p>
     * Description: 返回功能代码清单
     * </p>
     * 
     * @param functionCodeList 功能代码列表
     * @param parentFunctionCode 上级功能代码
     * @return 结果集
     */
    List<Map<String, Object>> getFunctionList(@Param("functionCodeList") List<String> functionCodeList,
            @Param("parentFunctionCode") String parentFunctionCode);

}
