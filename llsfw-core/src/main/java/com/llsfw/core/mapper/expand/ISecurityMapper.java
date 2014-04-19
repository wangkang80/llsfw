/**
 * ISecurityMapper.java
 * Created at 2014年4月19日
 * Created by wangkang
 */
package com.llsfw.core.mapper.expand;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ClassName: ISecurityMapper
 * </p>
 * <p>
 * Description: 安全DAO
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月19日
 * </p>
 */
public interface ISecurityMapper {

    /**
     * <p>
     * Description: 返回用户所关联的角色
     * </p>
     * 
     * @param loginName 用户名
     * @return 角色列表
     */
    List<String> findUserRoles(@Param("loginName") String loginName);

    /**
     * <p>
     * Description: 返回角色权限
     * </p>
     * 
     * @param roleList 角色列表
     * @return 权限列表
     */
    List<String> findRolePermissions(@Param("roleList") List<String> roleList);

    List<String> findUserPermissions(@Param("loginName") String loginName);
}
