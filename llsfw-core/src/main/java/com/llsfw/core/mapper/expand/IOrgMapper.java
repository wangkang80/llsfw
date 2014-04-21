/**
 * IOrgMapper.java
 * Created at 2014年4月19日
 * Created by wangkang
 */
package com.llsfw.core.mapper.expand;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ClassName: IOrgMapper
 * </p>
 * <p>
 * Description: 组织机构
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月19日
 * </p>
 */
public interface IOrgMapper {

    /**
     * <p>
     * Description: 返回所有的根节点
     * </p>
     * 
     * @return 所有的根节点
     */
    public List<Map<String, Object>> getRootOrg();

    /**
     * <p>
     * Description: 返回所有的子节点
     * </p>
     * 
     * @param parentOrgCode 父节点代码
     * @return 所有的子节点
     */
    public List<Map<String, Object>> getChildrenOrg(@Param("parentOrgCode") String parentOrgCode);

    /**
     * <p>
     * Description: 根据组织返回岗位列表
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 岗位列表
     */
    public List<Map<String, Object>> getJobByOrgCode(@Param("orgCode") String orgCode);

    /**
     * <p>
     * Description: 根据岗位返回角色列表
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 角色列表
     */
    public List<Map<String, Object>> getRoleByJobCode(@Param("jobCode") String jobCode);

    /**
     * <p>
     * Description: 返回job不存在的role
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return role列表
     */
    public List<Map<String, Object>> getNotExistsRoleByJobCode(@Param("jobCode") String jobCode);

}
