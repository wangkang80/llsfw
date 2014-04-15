/**
 * RoleService.java
 * Created at 2013-12-14
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.role;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.mapper.standard.TtRoleFunctionMapper;
import com.llsfw.core.mapper.standard.TtRoleMapper;
import com.llsfw.core.mapper.standard.TtUserRoleMapper;
import com.llsfw.core.model.standard.TtRole;
import com.llsfw.core.model.standard.TtRoleCriteria;
import com.llsfw.core.model.standard.TtRoleFunction;
import com.llsfw.core.model.standard.TtRoleFunctionCriteria;
import com.llsfw.core.model.standard.TtUserRoleCriteria;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: RoleService
 * </p>
 * <p>
 * Description: 角色服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月14日
 * </p>
 */
@Service
public class RoleService extends BaseService {
    /**
     * <p>
     * Field trm: role
     * </p>
     */
    @Autowired
    private TtRoleMapper trm;

    /**
     * <p>
     * Field turm: 用户角色关联
     * </p>
     */
    @Autowired
    private TtUserRoleMapper turm;

    /**
     * <p>
     * Field trfm: 角色功能关联
     * </p>
     */
    @Autowired
    private TtRoleFunctionMapper trfm;

    /**
     * <p>
     * Description: 删除功能
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @return 操作结果
     */
    public Map<String, Object> deleteRoleFunction(String roleCode, String functionCode) {
        //删除
        TtRoleFunctionCriteria trfc = null;
        trfc = new TtRoleFunctionCriteria();
        trfc.createCriteria().andRoleCodeEqualTo(roleCode).andFunctionCodeEqualTo(functionCode);
        this.trfm.deleteByExample(trfc);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 为角色添加功能关联
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCodeList 功能ID列表
     * @param loginName 登录名
     * @return 操作结果
     */
    public Map<String, Object> addRoleFunction(String roleCode, String functionCodeList, String loginName) {
        String[] functionCodes = null;
        functionCodes = functionCodeList.split(",");
        if (ArrayUtils.isNotEmpty(functionCodes)) {
            for (String functionCode : functionCodes) {
                //删除
                TtRoleFunctionCriteria trfc = null;
                trfc = new TtRoleFunctionCriteria();
                trfc.createCriteria().andRoleCodeEqualTo(roleCode).andFunctionCodeEqualTo(functionCode);
                this.trfm.deleteByExample(trfc);

                //添加
                TtRoleFunction trf = null;
                trf = new TtRoleFunction();
                trf.setRoleCode(roleCode);
                trf.setFunctionCode(functionCode);
                trf.setCreateBy(loginName);
                trf.setCreateDate(new Date());
                this.trfm.insert(trf);
            }
        }

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 返回功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    public List<Map<String, Object>> getFunctionList(String roleCode) {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append(" SELECT A.FUNCTION_CODE,FUNCTION_NAME FROM TT_FUNCTION A");
        sql.append(" WHERE A.STATUS=1"); //只查询启用的功能
        sql.append(" AND LEVEL_NO=3"); //只查询level为三的节点(功能节点)
        sql.append(" AND NOT EXISTS("); //只查询没有被添加的节点
        sql.append("     SELECT FUNCTION_CODE ");
        sql.append("     FROM TT_ROLE_FUNCTION B");
        sql.append("     WHERE B.ROLE_CODE='" + roleCode + "'");
        sql.append("     AND B.FUNCTION_CODE=A.FUNCTION_CODE");
        sql.append(" )");
        sql.append(" ORDER BY A.FUNCTION_CODE ASC");
        return this.getImqm().queryMap(sql.toString());
    }

    /**
     * <p>
     * Description: 根据角色代码返回所关联的功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    public List<Map<String, Object>> getRoleFunctionList(String roleCode) {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append(" SELECT A.ROLE_CODE,B.FUNCTION_CODE,B.FUNCTION_NAME,A.CREATE_BY,A.CREATE_DATE ");
        sql.append(" FROM TT_ROLE_FUNCTION A,TT_FUNCTION B");
        sql.append(" WHERE A.FUNCTION_CODE=B.FUNCTION_CODE");
        sql.append(" AND B.STATUS=1"); //只查询启用的功能
        sql.append(" AND A.ROLE_CODE='" + roleCode + "'"); //根据rolecode查询
        sql.append(" ORDER BY A.CREATE_DATE DESC");
        return this.getImqm().queryMap(sql.toString());
    }

    /**
     * <p>
     * Description: 删除角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 操作结果
     */
    public Map<String, Object> deleteRole(String roleCode) {
        //删除用户关联
        TtUserRoleCriteria turc = null;
        turc = new TtUserRoleCriteria();
        turc.createCriteria().andRoleCodeEqualTo(roleCode);
        this.turm.deleteByExample(turc);

        //删除功能关联
        TtRoleFunctionCriteria trfc = null;
        trfc = new TtRoleFunctionCriteria();
        trfc.createCriteria().andRoleCodeEqualTo(roleCode);
        this.trfm.deleteByExample(trfc);

        //删除角色
        this.trm.deleteByPrimaryKey(roleCode);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 更新角色
     * </p>
     * 
     * @param v 更新数据
     * @param roleCode 角色代码
     * @param loginName 登录名
     * @return 操作结果
     * @throws Exception 异常
     */
    public Map<String, Object> editRole(Map<String, String[]> v, String roleCode, String loginName) throws Exception {
        TtRole tr = this.trm.selectByPrimaryKey(roleCode);
        tr = AutoLoadBean.load(tr, v);
        tr.setUpdateBy(loginName);
        tr.setUpdateDate(new Date());
        this.trm.updateByPrimaryKey(tr);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 加载单个角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 角色
     */
    public TtRole loadRole(String roleCode) {
        return this.trm.selectByPrimaryKey(roleCode);
    }

    /**
     * <p>
     * Description: 创建角色
     * </p>
     * 
     * @param tr 角色
     * @param loginName 登陆名
     * @return 操作结果
     */
    public Map<String, Object> addRole(TtRole tr, String loginName) {
        tr.setCreateBy(loginName);
        tr.setCreateDate(new Date());
        this.trm.insertSelective(tr);

        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 校验角色是否唯一
     * </p>
     * 
     * @param roleCode 角色代码
     * @return true:通过,false:不通过
     */
    public boolean roleCodeUniqueCheck(String roleCode) {
        TtRoleCriteria trc = null;
        trc = new TtRoleCriteria();
        trc.createCriteria().andRoleCodeEqualTo(roleCode);
        return this.trm.countByExample(trc) > 0 ? false : true;
    }

    /**
     * <p>
     * Description: 返回角色列表
     * </p>
     * 
     * @return 角色列表
     */
    public List<Map<String, Object>> getRoleList() {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append(" SELECT * FROM TT_ROLE");
        sql.append(" ORDER BY CREATE_DATE DESC");
        return this.getImqm().queryMap(sql.toString());
    }
}
