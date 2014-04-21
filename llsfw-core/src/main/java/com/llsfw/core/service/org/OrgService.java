/**
 * OrgService.java
 * Created at 2014年5月2日
 * Created by wangkang
 */
package com.llsfw.core.service.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.mapper.expand.IOrgMapper;
import com.llsfw.core.mapper.standard.TtJobMapper;
import com.llsfw.core.mapper.standard.TtJobRoleMapper;
import com.llsfw.core.mapper.standard.TtOrganizationMapper;
import com.llsfw.core.mapper.standard.TtUserJobMapper;
import com.llsfw.core.model.standard.TtJob;
import com.llsfw.core.model.standard.TtJobCriteria;
import com.llsfw.core.model.standard.TtJobRole;
import com.llsfw.core.model.standard.TtJobRoleCriteria;
import com.llsfw.core.model.standard.TtJobRoleKey;
import com.llsfw.core.model.standard.TtOrganization;
import com.llsfw.core.model.standard.TtOrganizationCriteria;
import com.llsfw.core.model.standard.TtUserJobCriteria;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: OrgService
 * </p>
 * <p>
 * Description: 组织机构服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年5月2日
 * </p>
 */
@Service
public class OrgService extends BaseService {

    /**
     * <p>
     * Field tujm: 岗位用户dao
     * </p>
     */
    @Autowired
    private TtUserJobMapper tujm;

    /**
     * <p>
     * Field tjrm: 岗位角色dao
     * </p>
     */
    @Autowired
    private TtJobRoleMapper tjrm;

    /**
     * <p>
     * Field tjm: 岗位dao
     * </p>
     */
    @Autowired
    private TtJobMapper tjm;

    /**
     * <p>
     * Field iom: 组织dao
     * </p>
     */
    @Autowired
    private IOrgMapper iom;

    /**
     * <p>
     * Field tom: 组织dao
     * </p>
     */
    @Autowired
    private TtOrganizationMapper tom;

    /**
     * <p>
     * Description: 将组织结构转换为树形
     * </p>
     * 
     * @param nodes 组织结构
     * @return 树形组织结构
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getOrgNodeTree(List<Map<String, Object>> nodes) {
        if (!CollectionUtils.isEmpty(nodes)) {
            List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> item : nodes) {
                Map<String, Object> rvItem = new HashMap<String, Object>();
                rvItem.put("id", item.get("ORG_CODE"));
                rvItem.put("text", item.get("ORG_NAME"));
                if (item.get("children") != null) {
                    rvItem.put("children", getOrgNodeTree((List<Map<String, Object>>) item.get("children")));
                }
                rv.add(rvItem);
            }
            return rv;
        }
        return null;
    }

    /**
     * <p>
     * Description: 获得组织树
     * </p>
     * 
     * @param parentOrgCode 上级组织代码
     * @return 组织树
     */
    public List<Map<String, Object>> getOrgNode(String parentOrgCode) {
        List<Map<String, Object>> node = null;
        if (StringUtils.isEmpty(parentOrgCode)) {
            node = this.iom.getRootOrg();
        } else {
            node = this.iom.getChildrenOrg(parentOrgCode);
        }
        if (!CollectionUtils.isEmpty(node)) {
            for (int i = 0; i < node.size(); i++) {
                List<Map<String, Object>> children = this.getOrgNode(node.get(i).get("ORG_CODE").toString());
                if (!CollectionUtils.isEmpty(children)) {
                    node.get(i).put("children", children);
                }
            }
        }
        return node;
    }

    /**
     * <p>
     * Description: 判断组织代码是否存在
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 判断结果
     */
    public boolean orgCodeUniqueCheck(String orgCode) {
        return this.tom.selectByPrimaryKey(orgCode) == null ? true : false;
    }

    /**
     * <p>
     * Description: 判断岗位代码是否存在
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 判断结果
     */
    public boolean jobCodeUniqueCheck(String jobCode) {
        return this.tjm.selectByPrimaryKey(jobCode) == null ? true : false;
    }

    /**
     * <p>
     * Description: 添加组织
     * </p>
     * 
     * @param loginName 登陆人
     * @param to 组织对象
     * @return 操作结果
     */
    public JsonResult<String> addOrg(String loginName, TtOrganization to) {
        to.setCreateBy(loginName);
        to.setCreateDate(new Date());
        this.tom.insert(to);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 添加岗位
     * </p>
     * 
     * @param loginName 登陆人
     * @param tj 岗位对象
     * @return 操作结果
     */
    public JsonResult<String> addJob(String loginName, TtJob tj) {
        tj.setCreateBy(loginName);
        tj.setCreateDate(new Date());
        this.tjm.insert(tj);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 加载组织对象
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 组织对象
     */
    public TtOrganization loadOrg(String orgCode) {
        return this.tom.selectByPrimaryKey(orgCode);
    }

    /**
     * <p>
     * Description: 加载岗位对象
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 岗位对象
     */
    public TtJob loadJob(String jobCode) {
        return this.tjm.selectByPrimaryKey(jobCode);
    }

    /**
     * <p>
     * Description: 更新组织
     * </p>
     * 
     * @param valueMap 提交的更新值
     * @param orgCode 组织代码
     * @param loginName 操作人
     * @return 操作结果
     * @throws Exception 异常
     */
    public JsonResult<String> editOrg(Map<String, String[]> valueMap, String orgCode, String loginName)
            throws Exception {
        TtOrganization to = this.tom.selectByPrimaryKey(orgCode);
        to = AutoLoadBean.load(to, valueMap);
        to.setUpdateBy(loginName);
        to.setUpdateDate(new Date());
        this.tom.updateByPrimaryKey(to);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 更新岗位
     * </p>
     * 
     * @param valueMap 提交的更新值
     * @param orgCode 岗位代码
     * @param loginName 操作人
     * @return 操作结果
     * @throws Exception 异常
     */
    public JsonResult<String> editJob(Map<String, String[]> valueMap, String jobCode, String loginName)
            throws Exception {
        TtJob tj = this.tjm.selectByPrimaryKey(jobCode);
        tj = AutoLoadBean.load(tj, valueMap);
        tj.setUpdateBy(loginName);
        tj.setUpdateDate(new Date());
        this.tjm.updateByPrimaryKey(tj);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 删除组织
     * </p>
     * 
     * @param orgCode 组织代码
     * @param parentOrgCode 上级组织代码
     * @return 操作结果
     */
    public JsonResult<String> deleteOrg(String orgCode, String parentOrgCode) {
        TtOrganizationCriteria toc = new TtOrganizationCriteria();
        if (!StringUtils.isEmpty(orgCode)) {//删除本级
            toc.createCriteria().andOrgCodeEqualTo(orgCode);
        } else if (!StringUtils.isEmpty(parentOrgCode)) {//删除下级
            toc.createCriteria().andParentOrgCodeEqualTo(parentOrgCode);
        }
        List<TtOrganization> list = this.tom.selectByExample(toc);
        if (!CollectionUtils.isEmpty(list)) {
            for (TtOrganization item : list) {
                //删除岗位和角色,岗位和用户的关联
                TtJobCriteria tjc = new TtJobCriteria();
                tjc.createCriteria().andOrgCodeEqualTo(item.getOrgCode());
                List<TtJob> jobList = this.tjm.selectByExample(tjc);
                if (!CollectionUtils.isEmpty(jobList)) {
                    for (TtJob jobItem : jobList) {
                        TtJobRoleCriteria tjrc = new TtJobRoleCriteria();
                        tjrc.createCriteria().andJobCodeEqualTo(jobItem.getJobCode());
                        this.tjrm.deleteByExample(tjrc);

                        TtUserJobCriteria tujc = new TtUserJobCriteria();
                        tujc.createCriteria().andJobCodeEqualTo(jobItem.getJobCode());
                        this.tujm.deleteByExample(tujc);
                    }
                }
                //删除岗位
                this.tjm.deleteByExample(tjc);

                //删除组织
                this.tom.deleteByPrimaryKey(item.getOrgCode());

                //递归向下删除
                this.deleteOrg(null, item.getOrgCode());
            }

        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 删除岗位
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 操作结果
     */
    public JsonResult<String> deleteJob(String jobCode) {
        TtJobRoleCriteria tjrc = new TtJobRoleCriteria();
        tjrc.createCriteria().andJobCodeEqualTo(jobCode);
        this.tjrm.deleteByExample(tjrc);

        TtUserJobCriteria tujc = new TtUserJobCriteria();
        tujc.createCriteria().andJobCodeEqualTo(jobCode);
        this.tujm.deleteByExample(tujc);

        this.tjm.deleteByPrimaryKey(jobCode);

        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 根据组织代码反馈岗位列表
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 岗位列表
     */
    public List<Map<String, Object>> getJob(String orgCode) {
        return this.iom.getJobByOrgCode(orgCode);
    }

    /**
     * <p>
     * Description: 根据岗位返回角色列表
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 角色列表
     */
    public List<Map<String, Object>> getRole(String jobCode) {
        return this.iom.getRoleByJobCode(jobCode);
    }

    /**
     * <p>
     * Description: 根据岗位返回不存在角色列表
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 角色列表
     */
    public List<Map<String, Object>> getRoleList(String jobCode) {
        return this.iom.getNotExistsRoleByJobCode(jobCode);
    }

    /**
     * <p>
     * Description: 添加岗位角色关联
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCode 岗位代码
     * @param roleCodeList 角色列表
     * @return 操作结果
     */
    public JsonResult<String> addRole(String loginName, String jobCode, String roleCodeList) {
        String[] roleCodes = null;
        roleCodes = roleCodeList.split(",");
        if (ArrayUtils.isNotEmpty(roleCodes)) {
            for (String roleCode : roleCodes) {
                TtJobRole tjr = new TtJobRole();
                tjr.setCreateBy(loginName);
                tjr.setCreateDate(new Date());
                tjr.setJobCode(jobCode);
                tjr.setRoleCode(roleCode);
                this.tjrm.insert(tjr);
            }
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 删除角色与岗位的关联
     * </p>
     * 
     * @param jobCode 岗位代码
     * @param roleCode 角色代码
     * @return 操作结果
     */
    public JsonResult<String> deleteRole(String jobCode, String roleCode) {
        TtJobRoleKey tjrk = new TtJobRoleKey();
        tjrk.setJobCode(jobCode);
        tjrk.setRoleCode(roleCode);
        this.tjrm.deleteByPrimaryKey(tjrk);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }
}
