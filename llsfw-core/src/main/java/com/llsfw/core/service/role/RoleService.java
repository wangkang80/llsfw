/**
 * RoleService.java
 * Created at 2013-12-14
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.role;

import java.util.Date;
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
import com.llsfw.core.mapper.standard.TtFunctionMapper;
import com.llsfw.core.mapper.standard.TtJobRoleMapper;
import com.llsfw.core.mapper.standard.TtPurviewMapper;
import com.llsfw.core.mapper.standard.TtRoleFunctionMapper;
import com.llsfw.core.mapper.standard.TtRoleMapper;
import com.llsfw.core.model.standard.TtFunction;
import com.llsfw.core.model.standard.TtFunctionCriteria;
import com.llsfw.core.model.standard.TtJobRoleCriteria;
import com.llsfw.core.model.standard.TtRole;
import com.llsfw.core.model.standard.TtRoleCriteria;
import com.llsfw.core.model.standard.TtRoleFunction;
import com.llsfw.core.model.standard.TtRoleFunctionCriteria;
import com.llsfw.core.model.standard.TtRoleFunctionKey;
import com.llsfw.core.service.BaseService;
import com.llsfw.core.service.function.FunctionService;
import com.llsfw.core.service.security.SecurityService;

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
     * Field tjrm: 角色岗位dao
     * </p>
     */
    @Autowired
    private TtJobRoleMapper tjrm;

    /**
     * <p>
     * Field trm: role
     * </p>
     */
    @Autowired
    private TtRoleMapper trm;

    /**
     * <p>
     * Field trfm: 角色功能关联
     * </p>
     */
    @Autowired
    private TtRoleFunctionMapper trfm;

    /**
     * <p>
     * Field ism: 安全服务
     * </p>
     */
    @Autowired
    private SecurityService ss;

    /**
     * <p>
     * Field fs: 功能服务
     * </p>
     */
    @Autowired
    private FunctionService fs;

    /**
     * <p>
     * Field tpm: 操作权限服务
     * </p>
     */
    @Autowired
    private TtPurviewMapper tpm;

    /**
     * <p>
     * Field tfm: 功能dao
     * </p>
     */
    @Autowired
    private TtFunctionMapper tfm;

    /**
     * <p>
     * Description: 删除角色所关联的功能权限
     * </p>
     * 
     * @param roleCode
     * @param functionCode
     * @param purviewCode
     * @return
     */
    public JsonResult<String> deleteRoleFunction(String roleCode, String functionCode, String purviewCode) {
        //如果purviewCode不为空,则代表是只删除操作权限
        if (!StringUtils.isEmpty(purviewCode)) {
            TtRoleFunctionKey trfk = new TtRoleFunctionKey();
            trfk.setFunctionCode(functionCode);
            trfk.setPurviewCode(purviewCode);
            trfk.setRoleCode(roleCode);
            this.trfm.deleteByPrimaryKey(trfk);
        } else {
            //删除
            TtRoleFunctionCriteria trfc = new TtRoleFunctionCriteria();
            trfc.createCriteria().andRoleCodeEqualTo(roleCode).andFunctionCodeEqualTo(functionCode);
            this.trfm.deleteByExample(trfc);
            //获得下级
            TtFunctionCriteria tfc = new TtFunctionCriteria();
            tfc.createCriteria().andParentFunctionCodeEqualTo(functionCode);
            List<TtFunction> fList = this.tfm.selectByExample(tfc);
            if (!CollectionUtils.isEmpty(fList)) {
                for (TtFunction item : fList) {
                    deleteRoleFunction(roleCode, item.getFunctionCode(), null);
                }
            }
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 为角色添加功能和操作权限
     * </p>
     * 
     * @param loginName 用户名
     * @param roleCode 角色名
     * @param functionCode 功能代码
     * @param purviewCodes 权限代码(清单)
     * @return 操作结果
     */
    public JsonResult<String> addRoleFunction(String loginName, String roleCode, String functionCode,
            String purviewCodes) {
        //分解操作权限数组
        String[] purviewCodeArray = null;
        purviewCodeArray = purviewCodes.split(",");
        if (ArrayUtils.isNotEmpty(purviewCodeArray)) {
            for (String purviewCode : purviewCodeArray) {
                //删除
                TtRoleFunctionCriteria trfc = null;
                trfc = new TtRoleFunctionCriteria();
                trfc.createCriteria().andRoleCodeEqualTo(roleCode).andFunctionCodeEqualTo(functionCode)
                        .andPurviewCodeEqualTo(purviewCode);
                this.trfm.deleteByExample(trfc);
                //添加
                TtRoleFunction trf = null;
                trf = new TtRoleFunction();
                trf.setRoleCode(roleCode);
                trf.setFunctionCode(functionCode);
                trf.setPurviewCode(purviewCode);
                trf.setCreateBy(loginName);
                trf.setCreateDate(new Date());
                this.trfm.insert(trf);
            }
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
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
        //返回角色没有的功能清单
        List<Map<String, Object>> notIncludeFunctionPurviewList = this.ss.findNotIncludeFunctionPurviewByRole(roleCode);
        //获得角色没有的完整菜单
        List<Map<String, Object>> tree = this.fs.getFunctionPurviewTreeNode(notIncludeFunctionPurviewList, false);
        //返回
        return tree;
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
        //获得角色已有的功能清单
        List<Map<String, Object>> functionPurviewList = this.ss.findFunctionPurviewByRole(roleCode);
        //获得角色已有的完整菜单
        List<Map<String, Object>> tree = this.fs.getFunctionPurviewTreeNode(functionPurviewList, true);
        //返回
        return tree;
    }

    /**
     * <p>
     * Description: 删除角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 操作结果
     */
    public JsonResult<String> deleteRole(String roleCode) {
        //删除功能关联
        TtRoleFunctionCriteria trfc = new TtRoleFunctionCriteria();
        trfc.createCriteria().andRoleCodeEqualTo(roleCode);
        this.trfm.deleteByExample(trfc);

        //删除岗位关联
        TtJobRoleCriteria tjrc = new TtJobRoleCriteria();
        tjrc.createCriteria().andRoleCodeEqualTo(roleCode);
        tjrm.deleteByExample(tjrc);

        //删除角色
        this.trm.deleteByPrimaryKey(roleCode);

        //设定返回值
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public JsonResult<String> editRole(Map<String, String[]> v, String roleCode, String loginName) throws Exception {
        TtRole tr = this.trm.selectByPrimaryKey(roleCode);
        tr = AutoLoadBean.load(tr, v);
        tr.setUpdateBy(loginName);
        tr.setUpdateDate(new Date());
        this.trm.updateByPrimaryKey(tr);
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public JsonResult<String> addRole(TtRole tr, String loginName) {
        tr.setCreateBy(loginName);
        tr.setCreateDate(new Date());
        this.trm.insertSelective(tr);
        return new JsonResult<String>(Constants.SUCCESS, null);
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

    /**
     * <p>
     * Description: 返回角色功能不包含的权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @return 角色功能不包含的权限列表
     */
    public List<Map<String, Object>> getFunctionPurviewList(String roleCode, String functionCode) {
        return this.ss.findNotIncludePurviewByRoleFunction(roleCode, functionCode);
    }
}
