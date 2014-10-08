/**
 * UserService.java
 * Created at 2013-12-15
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.common.SystemParam;
import com.llsfw.core.mapper.standard.TtApplicationUserMapper;
import com.llsfw.core.mapper.standard.TtFunctionMapper;
import com.llsfw.core.mapper.standard.TtOrganizationMapper;
import com.llsfw.core.mapper.standard.TtPurviewMapper;
import com.llsfw.core.mapper.standard.TtUserFunctionMapper;
import com.llsfw.core.mapper.standard.TtUserJobMapper;
import com.llsfw.core.model.expand.PageResult;
import com.llsfw.core.model.standard.TtApplicationUser;
import com.llsfw.core.model.standard.TtApplicationUserCriteria;
import com.llsfw.core.model.standard.TtFunction;
import com.llsfw.core.model.standard.TtOrganization;
import com.llsfw.core.model.standard.TtOrganizationCriteria;
import com.llsfw.core.model.standard.TtPurview;
import com.llsfw.core.model.standard.TtPurviewKey;
import com.llsfw.core.model.standard.TtUserFunction;
import com.llsfw.core.model.standard.TtUserFunctionCriteria;
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
     * Field tom: 组织机构dao
     * </p>
     */
    @Autowired
    private TtOrganizationMapper tom;

    /**
     * <p>
     * Field tfm: 功能dao
     * </p>
     */
    @Autowired
    private TtFunctionMapper tfm;

    /**
     * <p>
     * Field tpm: 权限dao
     * </p>
     */
    @Autowired
    private TtPurviewMapper tpm;

    @Autowired
    private TtUserFunctionMapper tufm;

    /**
     * <p>
     * Description: 加载完整的功能权限树
     * </p>
     * 
     * @param userName 用户名
     * @return 功能权限树
     */
    public List<Map<String, Object>> loadUserFunctionTree(String userName) {
        //获得功能的数据
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT D.FUNCTION_CODE,D.PURVIEW_CODE FROM TT_PURVIEW D ");
        sql.append(" WHERE NOT EXISTS ( ");
        sql.append("    SELECT A.FUNCTION_CODE,A.PURVIEW_CODE FROM TT_ROLE_FUNCTION A,TT_JOB_ROLE B,TT_USER_JOB C ");
        sql.append("    WHERE A.ROLE_CODE=B.ROLE_CODE AND B.JOB_CODE=C.JOB_CODE ");
        sql.append("    AND C.LOGIN_NAME='" + userName + "' ");
        sql.append("    AND A.FUNCTION_CODE=D.FUNCTION_CODE ");
        sql.append("    AND A.PURVIEW_CODE=D.PURVIEW_CODE ");
        sql.append(" ) ");
        List<Map<String, Object>> functionList = this.getImqm().queryMap(sql.toString());

        //如有数据,在继续操作
        List<String> eqrv = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(functionList)) {
            for (Map<String, Object> item : functionList) {
                TtFunction f = this.tfm.selectByPrimaryKey(item.get("FUNCTION_CODE").toString());
                if (!eqrv.contains(f.getFunctionCode())) {
                    Map<String, Object> functionItem = new HashMap<String, Object>();
                    functionItem.put("FUNCTION_CODE", f.getFunctionCode());
                    functionItem.put("FUNCTION_NAME", f.getFunctionName());
                    functionItem.put("PARENT_FUNCTION_CODE", f.getParentFunctionCode());
                    rv.add(functionItem);
                    eqrv.add(f.getFunctionCode());
                    rv = this.getParentFunction(f.getParentFunctionCode(), rv, eqrv);
                }

                TtPurviewKey pk = new TtPurviewKey();
                pk.setFunctionCode(item.get("FUNCTION_CODE").toString());
                pk.setPurviewCode(item.get("PURVIEW_CODE").toString());
                TtPurview purview = tpm.selectByPrimaryKey(pk);
                if (!eqrv.contains(purview.getFunctionCode() + "_" + purview.getPurviewCode())) {
                    Map<String, Object> purviewItem = new HashMap<String, Object>();
                    purviewItem.put("FUNCTION_CODE", purview.getFunctionCode() + "_" + purview.getPurviewCode());
                    purviewItem.put("FUNCTION_NAME", purview.getPurviewName());
                    purviewItem.put("PARENT_FUNCTION_CODE", purview.getFunctionCode());
                    purviewItem.put("F_CODE", purview.getFunctionCode());
                    purviewItem.put("P_CODE", purview.getPurviewCode());
                    //判断是否已经勾选
                    TtUserFunctionCriteria tufc = null;
                    tufc = new TtUserFunctionCriteria();
                    tufc.createCriteria().andLoginNameEqualTo(userName)
                            .andFunctionCodeEqualTo(purview.getFunctionCode())
                            .andPurviewCodeEqualTo(purview.getPurviewCode());
                    if (this.tufm.countByExample(tufc) > 0) {
                        purviewItem.put("checked", true);
                    } else {
                        purviewItem.put("checked", false);
                    }
                    //添加结果集
                    rv.add(purviewItem);
                    eqrv.add(purview.getFunctionCode() + "_" + purview.getPurviewCode());
                }

            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 加载岗位清单
     * </p>
     * 
     * @param loginName 用户名
     * @param orgCodeList 组织清单
     * @return 结果集
     */
    public List<Map<String, Object>> loadJobList(String loginName, String orgCodeList) {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT B.LOGIN_NAME,A.JOB_CODE,A.JOB_NAME,C.ORG_CODE,C.ORG_NAME ");
        sb.append(" FROM TT_JOB A ");
        sb.append(" LEFT JOIN TT_USER_JOB B ON A.JOB_CODE=B.JOB_CODE AND B.LOGIN_NAME='" + loginName + "' ");
        sb.append(" LEFT JOIN TT_ORGANIZATION C ON A.ORG_CODE=C.ORG_CODE ");
        sb.append(" WHERE  C.ORG_CODE IN (" + orgCodeList + ") ");
        sb.append(" ORDER BY A.CREATE_DATE DESC ");
        return this.getImqm().queryMap(sb.toString());
    }

    /**
     * <p>
     * Description: 加载组织机构树
     * </p>
     * 
     * @param userName 用户名
     * @return 结果集
     */
    public List<Map<String, Object>> loadAllOrgTree(String userName) {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        TtOrganizationCriteria toc = new TtOrganizationCriteria();
        toc.createCriteria().andParentOrgCodeIsNull();
        toc.setOrderByClause(" ORG_SORT ASC");
        List<TtOrganization> orgList = this.tom.selectByExample(toc);
        if (!CollectionUtils.isEmpty(orgList)) {
            for (TtOrganization org : orgList) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("id", org.getOrgName());
                item.put("ORG_CODE", org.getOrgCode());
                item.put("ORG_NAME", org.getOrgName());
                item.put("PARENT_ORG_CODE", org.getParentOrgCode());
                String sql = "SELECT COUNT(*) FROM TT_JOB A,TT_USER_JOB B WHERE A.JOB_CODE=B.JOB_CODE AND A.ORG_CODE='"
                        + org.getOrgCode() + "' AND B.LOGIN_NAME='" + userName + "'";
                if (Integer.parseInt(this.getImqm().queryOneRowOneValue(sql)) > 0) {
                    item.put("MAIN_ORG", "1");
                } else {
                    item.put("MAIN_ORG", "0");
                }
                rv.add(item);
                rv = loadAllOrgTree(org.getOrgCode(), userName, rv);
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 递归加载下级组织
     * </p>
     * 
     * @param orgCode 组织代码
     * @param userName 用户名
     * @param rv 结果集
     * @return 结果集
     */
    private List<Map<String, Object>> loadAllOrgTree(String orgCode, String userName, List<Map<String, Object>> rv) {
        if (!StringUtils.isEmpty(orgCode)) {
            //获得下级组织
            TtOrganizationCriteria toc = new TtOrganizationCriteria();
            toc.createCriteria().andParentOrgCodeEqualTo(orgCode);
            toc.setOrderByClause("ORG_SORT ASC");
            List<TtOrganization> orgList = this.tom.selectByExample(toc);
            //递归处理
            if (!CollectionUtils.isEmpty(orgList)) {
                for (TtOrganization org : orgList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", org.getOrgName());
                    item.put("ORG_CODE", org.getOrgCode());
                    item.put("ORG_NAME", org.getOrgName());
                    item.put("PARENT_ORG_CODE", org.getParentOrgCode());
                    String sql = "SELECT COUNT(*) FROM TT_JOB A,TT_USER_JOB B WHERE A.JOB_CODE=B.JOB_CODE AND A.ORG_CODE='"
                            + org.getOrgCode() + "' AND B.LOGIN_NAME='" + userName + "'";
                    if (Integer.parseInt(this.getImqm().queryOneRowOneValue(sql)) > 0) {
                        item.put("MAIN_ORG", "0");
                    } else {
                        item.put("MAIN_ORG", "0");
                    }
                    rv.add(item);
                    rv = loadAllOrgTree(org.getOrgCode(), userName, rv);
                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 取消直接授权
     * </p>
     * 
     * @param userName 用户名
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    public JsonResult<String> deleteUserFunction(String userName, String functionCode, String purviewCode) {
        //删除 
        TtUserFunctionCriteria tufc = null;
        tufc = new TtUserFunctionCriteria();
        tufc.createCriteria().andLoginNameEqualTo(userName).andFunctionCodeEqualTo(functionCode)
                .andPurviewCodeEqualTo(purviewCode);
        this.tufm.deleteByExample(tufc);
        return new JsonResult<String>(Constants.SUCCESS, "取消授权成功");
    }

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
        return new JsonResult<String>(Constants.SUCCESS, "取消授权成功");
    }

    /**
     * <p>
     * Description: 添加直接权限
     * </p>
     * 
     * @param loginName 登陆人
     * @param userName 用户名
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    public JsonResult<String> addUserFunction(String loginName, String userName, String functionCode, String purviewCode) {
        //删除 
        TtUserFunctionCriteria tufc = null;
        tufc = new TtUserFunctionCriteria();
        tufc.createCriteria().andLoginNameEqualTo(userName).andFunctionCodeEqualTo(functionCode)
                .andPurviewCodeEqualTo(purviewCode);
        this.tufm.deleteByExample(tufc);
        //添加
        TtUserFunction tuf = null;
        tuf = new TtUserFunction();
        tuf.setCreateBy(loginName);
        tuf.setCreateDate(new Date());
        tuf.setLoginName(userName);
        tuf.setFunctionCode(functionCode);
        tuf.setPurviewCode(purviewCode);
        this.tufm.insertSelective(tuf);
        return new JsonResult<String>(Constants.SUCCESS, "授权成功");
    }

    /**
     * <p>
     * Description: 添加用户岗位关联
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCode 岗位 代码
     * @param ln 操作任务
     * @return 操作结果
     */
    public JsonResult<String> addUserJob(String loginName, String userName, String jobCode) {
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
        return new JsonResult<String>(Constants.SUCCESS, "授权成功");
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
        if (!StringUtils.isEmpty(loginName)) {
            sql = new StringBuffer();
            sql.append(" SELECT A.LOGIN_NAME,B.JOB_CODE,B.JOB_NAME,C.ORG_NAME,A.CREATE_DATE,A.CREATE_BY ");
            sql.append(" FROM TT_USER_JOB A,TT_JOB B,TT_ORGANIZATION C ");
            sql.append(" WHERE A.JOB_CODE=B.JOB_CODE AND B.ORG_CODE=C.ORG_CODE ");
            sql.append(" AND A.LOGIN_NAME='" + loginName + "' ");
            sql.append(" ORDER BY A.CREATE_DATE DESC ");
            return this.getImqm().queryMap(sql.toString());
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    /**
     * <p>
     * Description: 返回角色清单
     * </p>
     * 
     * @param loginName 用户名
     * @param jobName 岗位名
     * @return 角色列表
     */
    public List<Map<String, Object>> getUserJobRoleList(String loginName, String jobName) {
        StringBuffer sql = null;
        if (!StringUtils.isEmpty(loginName)) {
            sql = new StringBuffer();
            sql.append(" SELECT A.ROLE_CODE,A.ROLE_NAME,C.JOB_NAME,B.CREATE_BY,B.CREATE_DATE ");
            sql.append(" FROM TT_ROLE A,TT_JOB_ROLE B,TT_JOB C,TT_USER_JOB D ");
            sql.append(" WHERE A.ROLE_CODE=B.ROLE_CODE AND B.JOB_CODE=C.JOB_CODE AND C.JOB_CODE=D.JOB_CODE ");
            sql.append(" AND D.LOGIN_NAME='" + loginName + "' ");
            if (!StringUtils.isEmpty(jobName)) {
                sql.append(" AND B.JOB_CODE IN (" + jobName + ") ");
            }
            sql.append(" ORDER BY A.CREATE_DATE DESC ");
            return this.getImqm().queryMap(sql.toString());
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> loadAddUserJobFunctionTree(String jobCode) {
        //获得功能的数据
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT A.FUNCTION_CODE,A.PURVIEW_CODE FROM TT_ROLE_FUNCTION A,TT_JOB_ROLE B ");
        sql.append(" WHERE A.ROLE_CODE=B.ROLE_CODE ");
        sql.append(" AND B.JOB_CODE = '" + jobCode + "' ");
        sql.append(" GROUP BY A.FUNCTION_CODE,A.PURVIEW_CODE ");
        List<Map<String, Object>> functionList = this.getImqm().queryMap(sql.toString());

        //如有数据,在继续操作
        List<String> eqrv = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(functionList)) {
            for (Map<String, Object> item : functionList) {
                TtFunction f = this.tfm.selectByPrimaryKey(item.get("FUNCTION_CODE").toString());
                if (!eqrv.contains(f.getFunctionCode())) {
                    Map<String, Object> functionItem = new HashMap<String, Object>();
                    functionItem.put("FUNCTION_CODE", f.getFunctionCode());
                    functionItem.put("FUNCTION_NAME", f.getFunctionName());
                    functionItem.put("PARENT_FUNCTION_CODE", f.getParentFunctionCode());
                    rv.add(functionItem);
                    eqrv.add(f.getFunctionCode());
                    rv = this.getParentFunction(f.getParentFunctionCode(), rv, eqrv);
                }

                TtPurviewKey pk = new TtPurviewKey();
                pk.setFunctionCode(item.get("FUNCTION_CODE").toString());
                pk.setPurviewCode(item.get("PURVIEW_CODE").toString());
                TtPurview purview = tpm.selectByPrimaryKey(pk);
                if (!eqrv.contains(purview.getFunctionCode() + "_" + purview.getPurviewCode())) {
                    Map<String, Object> purviewItem = new HashMap<String, Object>();
                    purviewItem.put("FUNCTION_CODE", purview.getFunctionCode() + "_" + purview.getPurviewCode());
                    purviewItem.put("FUNCTION_NAME", purview.getPurviewName());
                    purviewItem.put("PARENT_FUNCTION_CODE", purview.getFunctionCode());
                    rv.add(purviewItem);
                    eqrv.add(purview.getFunctionCode() + "_" + purview.getPurviewCode());
                }

            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得功能树
     * </p>
     * 
     * @param loginName 用户名
     * @param jobName 岗位名
     * @param roleName 角色名
     * @param loadFunctionType 加载类别
     * @return 功能树
     */
    public List<Map<String, Object>> getUserJobRoleFunctionTree(String loginName, String jobName, String roleName,
            String loadFunctionType) {
        //获得功能的数据
        StringBuffer sqla = null;
        StringBuffer sqlb = null;
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        if (!StringUtils.isEmpty(loginName)) {
            sqla = new StringBuffer();
            sqlb = new StringBuffer();

            //岗位权限
            sqla.append(" SELECT A.FUNCTION_CODE,A.PURVIEW_CODE FROM TT_ROLE_FUNCTION A,TT_JOB_ROLE B,TT_USER_JOB C ");
            sqla.append(" WHERE A.ROLE_CODE=B.ROLE_CODE AND B.JOB_CODE=C.JOB_CODE ");
            sqla.append(" AND C.LOGIN_NAME='" + loginName + "' ");
            if (!StringUtils.isEmpty(jobName)) {
                sqla.append("    AND C.JOB_CODE IN (" + jobName + ") ");
            }
            if (!StringUtils.isEmpty(roleName)) {
                sqla.append("    AND B.ROLE_CODE IN (" + roleName + ") ");
            }
            sqla.append(" GROUP BY A.FUNCTION_CODE,A.PURVIEW_CODE ");

            //用户权限
            sqlb.append(" SELECT A.FUNCTION_CODE,A.PURVIEW_CODE FROM TT_USER_FUNCTION A ");
            sqlb.append(" WHERE A.LOGIN_NAME='" + loginName + "' ");
            sqlb.append(" GROUP BY A.FUNCTION_CODE,A.PURVIEW_CODE ");

            //判断加载类别
            List<Map<String, Object>> functionList = new ArrayList<Map<String, Object>>();
            if (StringUtils.isEmpty(loadFunctionType)) {
                List<Map<String, Object>> functionLista = this.getImqm().queryMap(sqla.toString());
                List<Map<String, Object>> functionListb = this.getImqm().queryMap(sqlb.toString());
                functionList.addAll(functionLista);
                functionList.addAll(functionListb);
            } else if ("1".equals(loadFunctionType)) {
                List<Map<String, Object>> functionLista = this.getImqm().queryMap(sqla.toString());
                functionList.addAll(functionLista);
            } else if ("2".equals(loadFunctionType)) {
                List<Map<String, Object>> functionListb = this.getImqm().queryMap(sqlb.toString());
                functionList.addAll(functionListb);
            } else {
                this.log.error("未知功能加载类别");
            }

            //如有数据,在继续操作
            List<String> eqrv = new ArrayList<String>();
            if (!CollectionUtils.isEmpty(functionList)) {
                for (Map<String, Object> item : functionList) {
                    TtFunction f = this.tfm.selectByPrimaryKey(item.get("FUNCTION_CODE").toString());
                    if (!eqrv.contains(f.getFunctionCode())) {
                        Map<String, Object> functionItem = new HashMap<String, Object>();
                        functionItem.put("FUNCTION_CODE", f.getFunctionCode());
                        functionItem.put("FUNCTION_NAME", f.getFunctionName());
                        functionItem.put("PARENT_FUNCTION_CODE", f.getParentFunctionCode());
                        rv.add(functionItem);
                        eqrv.add(f.getFunctionCode());
                        rv = this.getParentFunction(f.getParentFunctionCode(), rv, eqrv);
                    }

                    TtPurviewKey pk = new TtPurviewKey();
                    pk.setFunctionCode(item.get("FUNCTION_CODE").toString());
                    pk.setPurviewCode(item.get("PURVIEW_CODE").toString());
                    TtPurview purview = tpm.selectByPrimaryKey(pk);
                    if (!eqrv.contains(purview.getFunctionCode() + "_" + purview.getPurviewCode())) {
                        Map<String, Object> purviewItem = new HashMap<String, Object>();
                        purviewItem.put("FUNCTION_CODE", purview.getFunctionCode() + "_" + purview.getPurviewCode());
                        purviewItem.put("FUNCTION_NAME", purview.getPurviewName());
                        purviewItem.put("PARENT_FUNCTION_CODE", purview.getFunctionCode());
                        rv.add(purviewItem);
                        eqrv.add(purview.getFunctionCode() + "_" + purview.getPurviewCode());
                    }

                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得上级菜单
     * </p>
     * 
     * @param parentFunctionCode 上级菜单代码
     * @param rv 结果集
     * @param eqrv 比较结果集
     * @return 结果集
     */
    private List<Map<String, Object>> getParentFunction(String parentFunctionCode, List<Map<String, Object>> rv,
            List<String> eqrv) {
        if (!StringUtils.isEmpty(parentFunctionCode)) {
            //获得上级组织
            TtFunction function = this.tfm.selectByPrimaryKey(parentFunctionCode);
            //递归处理
            if (function != null) {
                Map<String, Object> functionItem = new HashMap<String, Object>();
                functionItem.put("FUNCTION_CODE", function.getFunctionCode());
                functionItem.put("FUNCTION_NAME", function.getFunctionName());
                functionItem.put("PARENT_FUNCTION_CODE", function.getParentFunctionCode());
                if (!eqrv.contains(function.getFunctionCode())) {
                    rv.add(functionItem);
                    eqrv.add(function.getFunctionCode());
                    rv = getParentFunction(function.getParentFunctionCode(), rv, eqrv);
                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得job关联的组织机构
     * </p>
     * 
     * @param loginName 用户名
     * @param jobName 岗位代码
     * @param loadOrgType 加载组织类别(空为全部组织,1为上级组织,2为下级组织)
     * @return 组织机构树
     */
    public List<Map<String, Object>> getUserJobOrgTree(String loginName, String jobName, String loadOrgType) {
        //获得所关联的组织数据
        StringBuffer sql = null;
        if (!StringUtils.isEmpty(loginName)) {
            sql = new StringBuffer();
            sql.append(" SELECT AA.ORG_CODE,AA.ORG_NAME,AA.PARENT_ORG_CODE,AA.ORG_SORT FROM ( ");
            sql.append("    SELECT A.ORG_CODE,A.ORG_NAME,A.PARENT_ORG_CODE,A.ORG_SORT ");
            sql.append("    FROM TT_ORGANIZATION A,TT_JOB B,TT_USER_JOB C ");
            sql.append("    WHERE A.ORG_CODE=B.ORG_CODE AND B.JOB_CODE=C.JOB_CODE ");
            sql.append("    AND C.LOGIN_NAME='" + loginName + "' ");
            if (!StringUtils.isEmpty(jobName)) {
                sql.append("    AND C.JOB_CODE IN (" + jobName + ") ");
            }
            sql.append("    GROUP BY A.ORG_CODE,A.ORG_NAME,A.PARENT_ORG_CODE,A.ORG_SORT ");
            sql.append(" ) AA ");
            sql.append(" ORDER BY AA.ORG_SORT ASC ");
            List<Map<String, Object>> orgList = this.getImqm().queryMap(sql.toString());

            //如果有数据,则进行后续的操作
            if (!CollectionUtils.isEmpty(orgList)) {
                //返回值
                List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
                List<String> eqrv = new ArrayList<String>();
                //获得上级组织和下级组织
                for (Map<String, Object> item : orgList) {
                    Map<String, Object> orgItem = new HashMap<String, Object>();
                    orgItem.put("ORG_CODE", item.get("ORG_CODE"));
                    orgItem.put("ORG_NAME", item.get("ORG_NAME"));
                    orgItem.put("PARENT_ORG_CODE", item.get("PARENT_ORG_CODE"));
                    orgItem.put("MAIN_ORG", "1");
                    rv.add(orgItem);
                    eqrv.add(item.get("ORG_CODE").toString());

                    //判断加载类别
                    if (StringUtils.isEmpty(loadOrgType)) {
                        rv = getParentOrg(item.get("PARENT_ORG_CODE") == null ? null : item.get("PARENT_ORG_CODE")
                                .toString(), rv, eqrv);
                        rv = getOrg(item.get("ORG_CODE") == null ? null : item.get("ORG_CODE").toString(), rv, eqrv);
                    } else if ("1".equals(loadOrgType)) {
                        rv = getParentOrg(item.get("PARENT_ORG_CODE") == null ? null : item.get("PARENT_ORG_CODE")
                                .toString(), rv, eqrv);
                    } else if ("2".equals(loadOrgType)) {
                        rv = getOrg(item.get("ORG_CODE") == null ? null : item.get("ORG_CODE").toString(), rv, eqrv);
                    } else {
                        this.log.error("未知组织加载类别");
                    }
                }
                return rv;
            } else {
                return new ArrayList<Map<String, Object>>();
            }
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    /**
     * <p>
     * Description: 获得下级组织
     * </p>
     * 
     * @param orgCode 组织代码
     * @param rv 结果集
     * @return 结果集
     */
    private List<Map<String, Object>> getOrg(String orgCode, List<Map<String, Object>> rv, List<String> eqrv) {
        if (!StringUtils.isEmpty(orgCode)) {
            //获得下级组织
            TtOrganizationCriteria toc = new TtOrganizationCriteria();
            toc.createCriteria().andParentOrgCodeEqualTo(orgCode);
            toc.setOrderByClause("ORG_SORT ASC");
            List<TtOrganization> orgList = this.tom.selectByExample(toc);
            //递归处理
            if (!CollectionUtils.isEmpty(orgList)) {
                for (TtOrganization org : orgList) {
                    Map<String, Object> orgItem = new HashMap<String, Object>();
                    orgItem.put("ORG_CODE", org.getOrgCode());
                    orgItem.put("ORG_NAME", org.getOrgName());
                    orgItem.put("PARENT_ORG_CODE", org.getParentOrgCode());
                    orgItem.put("MAIN_ORG", "0");
                    if (!eqrv.contains(org.getOrgCode())) {
                        rv.add(orgItem);
                        eqrv.add(org.getOrgCode());
                        rv = getOrg(org.getOrgCode(), rv, eqrv);
                    }
                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得上级组织
     * </p>
     * 
     * @param parentOrgCode 上级组织代码
     * @param rv 结果集
     * @return 结果集
     */
    private List<Map<String, Object>> getParentOrg(String parentOrgCode, List<Map<String, Object>> rv, List<String> eqrv) {
        if (!StringUtils.isEmpty(parentOrgCode)) {
            //获得上级组织
            TtOrganization org = this.tom.selectByPrimaryKey(parentOrgCode);
            //递归处理
            if (org != null) {
                Map<String, Object> orgItem = new HashMap<String, Object>();
                orgItem.put("ORG_CODE", org.getOrgCode());
                orgItem.put("ORG_NAME", org.getOrgName());
                orgItem.put("PARENT_ORG_CODE", org.getParentOrgCode());
                orgItem.put("MAIN_ORG", "0");
                if (!eqrv.contains(org.getOrgCode())) {
                    rv.add(orgItem);
                    eqrv.add(org.getOrgCode());
                    rv = getParentOrg(org.getParentOrgCode(), rv, eqrv);
                }
            }
        }
        return rv;
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
        TtUserFunctionCriteria tuf = new TtUserFunctionCriteria();
        tuf.createCriteria().andLoginNameEqualTo(loginName);
        this.tujm.deleteByExample(tujc);
        this.taum.deleteByPrimaryKey(loginName);
        this.tufm.deleteByExample(tuf);
        return new JsonResult<String>(Constants.SUCCESS, "删除成功");
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
        return new JsonResult<String>(Constants.SUCCESS, "初始化成功");
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
        return new JsonResult<String>(Constants.SUCCESS, "修改成功");
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

        return new JsonResult<String>(Constants.SUCCESS, "保存成功");
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
     * @param page 当前页
     * @param rows 每页行数
     * 
     * @return 用户列表
     * @throws Exception
     */
    public Map<String, Object> getUserList(int page, int rows) throws Exception {
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
        PageResult pr = this.getPrs().pageQuery(sql.toString(), rows, page);
        Map<String, Object> rv = new HashMap<String, Object>();
        rv.put("total", pr.getTotalRecords());
        rv.put("rows", pr.getRecords());
        return rv;
    }
}
