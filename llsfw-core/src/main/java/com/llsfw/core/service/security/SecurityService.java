/**
 * SecurityService.java
 * Created at 2014年4月25日
 * Created by wangkang
 */
package com.llsfw.core.service.security;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.mapper.expand.IFunctionExpMapper;
import com.llsfw.core.mapper.expand.ISecurityMapper;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: SecurityService
 * </p>
 * <p>
 * Description: 安全服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月25日
 * </p>
 */
@Service
public class SecurityService extends BaseService {

    /**
     * <p>
     * Field ism: 安全dao
     * </p>
     */
    @Autowired
    private ISecurityMapper ism;

    /**
     * <p>
     * Field ifem: 功能dao
     * </p>
     */
    @Autowired
    private IFunctionExpMapper ifem;

    /**
     * <p>
     * Description: 返回角色功能不包含的权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @return 角色功能不包含的权限列表
     */
    public List<Map<String, Object>> findNotIncludePurviewByRoleFunction(String roleCode, String functionCode) {
        return this.ifem.findNotIncludePurviewByRoleFunction(roleCode, functionCode);
    }

    /**
     * <p>
     * Description: 返回角色不包含的功能权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 角色不包含的功能权限列表
     */
    public List<Map<String, Object>> findNotIncludeFunctionPurviewByRole(String roleCode) {
        return this.ifem.findNotIncludeFunctionPurviewByRole(roleCode);
    }

    /**
     * <p>
     * Description: 返回角色所关联的功能权限清单
     * </p>
     * 
     * @param roleCode 角色ID
     * @return 角色所关联的功能权限清单
     */
    public List<Map<String, Object>> findFunctionPurviewByRole(String roleCode) {
        return this.ifem.findFunctionPurviewByRole(roleCode);
    }

    /**
     * <p>
     * Description: 返回用户所拥有的角色
     * </p>
     * 
     * @param loginName 用户名
     * @return 角色列表
     */
    public List<String> findUserRoles(String loginName) {
        return ism.findUserRoles(loginName);
    }

    /**
     * <p>
     * Description: 返回用户所拥有的所有权限(角色权限,直接权限)
     * </p>
     * 
     * @param loginName 用户名
     * @param roleList 角色清单
     * @return 权限列表
     */
    public Set<String> findUserPermissions(String loginName, List<String> roleList) {
        if (!CollectionUtils.isEmpty(roleList)) {
            List<String> rolePermissions = ism.findRolePermissions(roleList);
            List<String> userPermissions = ism.findUserPermissions(loginName);
            Set<String> permissions = new HashSet<String>();
            permissions.addAll(rolePermissions);
            permissions.addAll(userPermissions);
            return permissions;
        }
        return null;
    }

    /**
     * <p>
     * Description: 返回用户的功能清单
     * </p>
     * 
     * @param loginName 用户名
     * @return 用户名
     */
    public List<String> findUserFunctions(String loginName) {
        List<String> roleList = this.findUserRoles(loginName);
        if (!CollectionUtils.isEmpty(roleList)) {
            List<String> roleFunctions = ism.findRoleFunctions(roleList);
            List<String> userFunctions = ism.findUserFunctions(loginName);
            roleFunctions.addAll(userFunctions);
            return roleFunctions;
        }
        return null;
    }
}
