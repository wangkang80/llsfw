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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.common.SystemParam;
import com.llsfw.core.mapper.standard.TtApplicationUserMapper;
import com.llsfw.core.mapper.standard.TtUserJobMapper;
import com.llsfw.core.model.standard.TtApplicationUser;
import com.llsfw.core.model.standard.TtApplicationUserCriteria;
import com.llsfw.core.model.standard.TtUserJob;
import com.llsfw.core.model.standard.TtUserJobCriteria;
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
     * Field hashAlgorithmName: 加密算法
     * </p>
     */
    @Value("#{configProperties['security.hashAlgorithmName']}")
    private String hashAlgorithmName;

    /**
     * <p>
     * Field hashIterations: 迭代次数
     * </p>
     */
    @Value("#{configProperties['security.hashIterations']}")
    private int hashIterations;

    /**
     * <p>
     * Field tujm: 岗位用户dao
     * </p>
     */
    @Autowired
    private TtUserJobMapper tujm;

    /**
     * <p>
     * Field taum: 用户
     * </p>
     */
    @Autowired
    private TtApplicationUserMapper taum;

    /**
     * <p>
     * Description: 删除用户岗位关联
     * </p>
     * 
     * @param loginName 用户
     * @param jobCode 岗位
     * @return 操作结果
     */
    public JsonResult<String> deleteUserJob(String loginName, String jobCode) {
        TtUserJobCriteria turc = null;
        turc = new TtUserJobCriteria();
        turc.createCriteria().andLoginNameEqualTo(loginName).andJobCodeEqualTo(jobCode);
        this.tujm.deleteByExample(turc);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 添加用户岗位关联
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCodeList 岗位列表
     * @param ln 操作任务
     * @return 操作结果
     */
    public JsonResult<String> addUserJob(String loginName, String userName, String jobCodeList) {
        String[] jobCodes = null;
        jobCodes = jobCodeList.split(",");
        if (ArrayUtils.isNotEmpty(jobCodes)) {
            for (String jobCode : jobCodes) {
                //删除 
                TtUserJobCriteria turc = null;
                turc = new TtUserJobCriteria();
                turc.createCriteria().andLoginNameEqualTo(userName).andJobCodeEqualTo(jobCode);
                this.tujm.deleteByExample(turc);
                //添加
                TtUserJob tur = null;
                tur = new TtUserJob();
                tur.setCreateBy(loginName);
                tur.setCreateDate(new Date());
                tur.setLoginName(userName);
                tur.setJobCode(jobCode);
                this.tujm.insert(tur);
            }
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 返回岗位列列表
     * </p>
     * 
     * @param loginName 登录名
     * @return 岗位列表
     */
    public List<Map<String, Object>> getJobList(String loginName) {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append(" SELECT A.JOB_CODE,A.JOB_NAME FROM TT_JOB A");
        sql.append(" WHERE NOT EXISTS(");
        sql.append("     SELECT B.JOB_CODE");
        sql.append("     FROM TT_USER_JOB B");
        sql.append("     WHERE B.LOGIN_NAME='" + loginName + "'");
        sql.append("     AND B.JOB_CODE=A.JOB_CODE");
        sql.append(" )");
        sql.append(" ORDER BY A.JOB_CODE ASC");
        return this.getImqm().queryMap(sql.toString());
    }

    /**
     * <p>
     * Description: 返回用户岗位列表
     * </p>
     * 
     * @param loginName 当前用户名
     * @return 岗位列表
     */
    public List<Map<String, Object>> getUserJobList(String loginName) {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append(" SELECT A.LOGIN_NAME,B.JOB_CODE,B.JOB_NAME,A.CREATE_DATE,A.CREATE_BY");
        sql.append(" FROM TT_USER_JOB A,TT_JOB B");
        sql.append(" WHERE A.JOB_CODE=B.JOB_CODE");
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

        //设置密码
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = new SimpleHash(hashAlgorithmName, newPswd, ByteSource.Util.bytes(salt), hashIterations);
        String encodedPassword = hash.toHex();
        tau.setLoginPassword(encodedPassword);
        tau.setSalt(salt);

        //保存
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
        ByteSource salt = ByteSource.Util.bytes(tau.getSalt());
        SimpleHash hash = new SimpleHash(this.hashAlgorithmName, oldPswd, salt, this.hashIterations);
        String oldPswdEncodedPassword = hash.toHex();
        return tau.getLoginPassword().equals(oldPswdEncodedPassword);
    }

    /**
     * <p>
     * Description: 删除用户
     * </p>
     * 
     * @param loginName 用户名
     * @return 操作结果
     */
    public JsonResult<String> userDelete(String loginName) {
        TtUserJobCriteria tujc = new TtUserJobCriteria();
        tujc.createCriteria().andLoginNameEqualTo(loginName);
        this.tujm.deleteByExample(tujc);
        this.taum.deleteByPrimaryKey(loginName);
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public JsonResult<String> saveDefPswd(String loginName, String ln) {
        TtApplicationUser tau = null;
        tau = new TtApplicationUser();
        tau.setLoginName(loginName);
        tau.setUpdateBy(ln);
        tau.setUpdateDate(new Date());

        //设置初始化密码(MD5加密)
        String defPswd = null;
        defPswd = this.getPs().getServerParamter(SystemParam.SYSTEM_DEF_PSWD.name());
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = new SimpleHash(hashAlgorithmName, defPswd, ByteSource.Util.bytes(salt), hashIterations);
        String encodedPassword = hash.toHex();
        tau.setLoginPassword(encodedPassword);
        tau.setSalt(salt);

        //更新
        this.taum.updateByPrimaryKeySelective(tau);

        //设定返回值
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public JsonResult<String> editUser(Map<String, String[]> v, String loginName, String ln) throws Exception {
        TtApplicationUser tau = this.taum.selectByPrimaryKey(loginName);
        tau = AutoLoadBean.load(tau, v);
        tau.setUpdateBy(ln);
        tau.setUpdateDate(new Date());
        this.taum.updateByPrimaryKey(tau);
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public JsonResult<String> addUser(TtApplicationUser tau, String loginName) {
        //获得默认密码
        String defPswd = null;
        defPswd = this.getPs().getServerParamter(SystemParam.SYSTEM_DEF_PSWD.name());

        //设置密码
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = new SimpleHash(hashAlgorithmName, defPswd, ByteSource.Util.bytes(salt), hashIterations);
        String encodedPassword = hash.toHex();

        //填充值,并且保存
        tau.setLoginPassword(encodedPassword);
        tau.setSalt(salt);
        tau.setCreateBy(loginName);
        tau.setCreateDate(new Date());
        this.taum.insertSelective(tau);

        return new JsonResult<String>(Constants.SUCCESS, null);
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
