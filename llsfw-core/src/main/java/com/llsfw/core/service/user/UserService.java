/**
 * UserService.java
 * Created at 2013-12-15
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.SystemParam;
import com.llsfw.core.mapper.standard.TtApplicationUserMapper;
import com.llsfw.core.mapper.standard.TtUserRoleMapper;
import com.llsfw.core.model.standard.TtApplicationUser;
import com.llsfw.core.model.standard.TtApplicationUserCriteria;
import com.llsfw.core.model.standard.TtUserRole;
import com.llsfw.core.model.standard.TtUserRoleCriteria;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: UserService
 * </p>
 * <p>
 * Description: 用户维护
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月15日
 * </p>
 */
@Service
public class UserService extends BaseService {
    /**
     * <p>
     * Field taum: 用户
     * </p>
     */
    @Autowired
    private TtApplicationUserMapper taum;

    /**
     * <p>
     * Field turm: 用户角色关联
     * </p>
     */
    @Autowired
    private TtUserRoleMapper turm;

    /**
     * <p>
     * Description: 删除用户角色关联
     * </p>
     * 
     * @param loginName 用户
     * @param roleCode 角色
     * @return 操作结果
     */
    public Map<String, Object> deleteUserRole(String loginName, String roleCode) {
        TtUserRoleCriteria turc = null;
        turc = new TtUserRoleCriteria();
        turc.createCriteria().andLoginNameEqualTo(loginName).andRoleCodeEqualTo(roleCode);
        this.turm.deleteByExample(turc);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 添加用户角色关联
     * </p>
     * 
     * @param loginName 用户名
     * @param roleCodeList 角色列表
     * @param ln 操作任务
     * @return 操作结果
     */
    public Map<String, Object> addUserRole(String loginName, String roleCodeList, String ln) {
        String[] roleCodes = null;
        roleCodes = roleCodeList.split(",");
        if (ArrayUtils.isNotEmpty(roleCodes)) {
            for (String roleCode : roleCodes) {
                //删除
                TtUserRoleCriteria turc = null;
                turc = new TtUserRoleCriteria();
                turc.createCriteria().andLoginNameEqualTo(loginName).andRoleCodeEqualTo(roleCode);
                this.turm.deleteByExample(turc);

                //添加
                TtUserRole tur = null;
                tur = new TtUserRole();
                tur.setCreateBy(ln);
                tur.setCreateDate(new Date());
                tur.setLoginName(loginName);
                tur.setRoleCode(roleCode);
                this.turm.insert(tur);
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
     * Description: 返回角色列列表
     * </p>
     * 
     * @param loginName 登录名
     * @return 角色列表
     */
    public List<Map<String, Object>> getRoleList(String loginName) {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append(" SELECT A.ROLE_CODE,A.ROLE_NAME FROM TT_ROLE A");
        sql.append(" WHERE NOT EXISTS(");
        sql.append("     SELECT B.ROLE_CODE");
        sql.append("     FROM TT_USER_ROLE B");
        sql.append("     WHERE B.LOGIN_NAME='" + loginName + "'");
        sql.append("     AND B.ROLE_CODE=A.ROLE_CODE");
        sql.append(" )");
        sql.append(" ORDER BY A.ROLE_CODE ASC");
        return this.getImqm().queryMap(sql.toString());
    }

    /**
     * <p>
     * Description: 返回用户角色列表
     * </p>
     * 
     * @param loginName 当前用户名
     * @return 角色列表
     */
    public List<Map<String, Object>> getUserRoleList(String loginName) {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append(" SELECT A.LOGIN_NAME,B.ROLE_CODE,B.ROLE_NAME,A.CREATE_DATE,A.CREATE_BY");
        sql.append(" FROM TT_USER_ROLE A,TT_ROLE B");
        sql.append(" WHERE A.ROLE_CODE=B.ROLE_CODE");
        sql.append(" AND A.LOGIN_NAME='" + loginName + "'");
        sql.append(" ORDER BY A.CREATE_DATE DESC");
        return this.getImqm().queryMap(sql.toString());
    }

    /**
     * <p>
     * Description: 修改密码
     * </p>
     * 
     * @param loginName 用户名
     * @param newPswd 新密码
     * @return 操作结果
     */
    public Map<String, Object> changePswd(String loginName, String newPswd) {
        TtApplicationUser tau = null;
        tau = new TtApplicationUser();
        tau.setLoginName(loginName);
        tau.setUpdateBy(loginName);
        tau.setUpdateDate(new Date());

        //设置密码(MD5加密)
        newPswd = DigestUtils.md5Hex(newPswd);
        tau.setLoginPassword(newPswd);

        this.taum.updateByPrimaryKeySelective(tau);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("returnCode", "success");
        return rv;
    }

    /**
     * <p>
     * Description: 校验密码是否一致
     * </p>
     * 
     * @param loginName 登录名
     * @param oldPswd 登陆密码
     * @return ture:正确,false:错误
     */
    public boolean pswdCheck(String loginName, String oldPswd) {
        TtApplicationUser tau = null;
        tau = this.taum.selectByPrimaryKey(loginName);
        oldPswd = DigestUtils.md5Hex(oldPswd);
        return tau.getLoginPassword().equals(oldPswd);
    }

    /**
     * <p>
     * Description: 初始化密码
     * </p>
     * 
     * @param loginName 用户名
     * @param ln 操作人
     * @return 操作结果
     */
    public Map<String, Object> saveDefPswd(String loginName, String ln) {
        TtApplicationUser tau = null;
        tau = new TtApplicationUser();
        tau.setLoginName(loginName);
        tau.setUpdateBy(ln);
        tau.setUpdateDate(new Date());

        //设置初始化密码(MD5加密)
        String defPswd = null;
        defPswd = this.getPs().getServerParamter(SystemParam.SYSTEM_DEF_PSWD.name());
        defPswd = DigestUtils.md5Hex(defPswd);
        tau.setLoginPassword(defPswd);

        this.taum.updateByPrimaryKeySelective(tau);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 修改用户
     * </p>
     * 
     * @param v 修改值
     * @param loginName 用户名
     * @param ln 操作名
     * @return 操作结果
     * @throws Exception 异常
     */
    public Map<String, Object> editUser(Map<String, String[]> v, String loginName, String ln) throws Exception {
        TtApplicationUser tau = this.taum.selectByPrimaryKey(loginName);
        tau = AutoLoadBean.load(tau, v);
        tau.setUpdateBy(ln);
        tau.setUpdateDate(new Date());
        this.taum.updateByPrimaryKey(tau);

        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 加载用户数据
     * </p>
     * 
     * @param loginName 登陆名字
     * @return 用户
     */
    public TtApplicationUser loadUser(String loginName) {
        return this.taum.selectByPrimaryKey(loginName);
    }

    /**
     * <p>
     * Description: 保存用户(密码初始化)
     * </p>
     * 
     * @param tau 用户对象
     * @param loginName 登录名
     * @return 操作结果
     */
    public Map<String, Object> addUser(TtApplicationUser tau, String loginName) {
        //设置初始化密码(MD5加密)
        String defPswd = null;
        defPswd = this.getPs().getServerParamter(SystemParam.SYSTEM_DEF_PSWD.name());
        defPswd = DigestUtils.md5Hex(defPswd);
        tau.setLoginPassword(defPswd);

        tau.setCreateBy(loginName);
        tau.setCreateDate(new Date());
        this.taum.insertSelective(tau);

        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();
        rv.put("code", "1");
        return rv;
    }

    /**
     * <p>
     * Description: 校验登录名是否维护
     * </p>
     * 
     * @param loginName 登录名
     * @return true:通过,false:不通过
     */
    public boolean loginNameUniqueCheck(String loginName) {
        TtApplicationUserCriteria tauc = null;
        tauc = new TtApplicationUserCriteria();
        tauc.createCriteria().andLoginNameEqualTo(loginName);
        return this.taum.countByExample(tauc) > 0 ? false : true;
    }

    /**
     * <p>
     * Description: 返回用户列表
     * </p>
     * 
     * @return 用户列表
     */
    public List<Map<String, Object>> getUserList() {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append("    SELECT  ");
        sql.append("    LOGIN_NAME, ");
        sql.append("    USER_NAME,  ");
        sql.append("    USER_PHONE, ");
        sql.append("    USER_M_PHOME,   ");
        sql.append("    USER_EMAIL, ");
        sql.append("    USER_STATUS,    ");
        sql.append("    CREATE_BY,  ");
        sql.append("    CREATE_DATE,    ");
        sql.append("    UPDATE_BY,  ");
        sql.append("    UPDATE_DATE ");
        sql.append("    FROM TT_APPLICATION_USER    ");
        sql.append("    ORDER BY LOGIN_NAME ASC   ");
        return this.getImqm().queryMap(sql.toString());
    }
}
