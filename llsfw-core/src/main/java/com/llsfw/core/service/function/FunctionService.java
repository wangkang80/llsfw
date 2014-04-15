/**
 * FunctionService.java
 * Created at 2013-12-06
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.function;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.mapper.expand.IFunctionExpMapper;
import com.llsfw.core.mapper.standard.TtFunctionMapper;
import com.llsfw.core.mapper.standard.TtRoleFunctionMapper;
import com.llsfw.core.model.standard.TtFunction;
import com.llsfw.core.model.standard.TtFunctionCriteria;
import com.llsfw.core.model.standard.TtRoleFunctionCriteria;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: FunctionService
 * </p>
 * <p>
 * Description: 功能维护
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月6日
 * </p>
 */
@Service
public class FunctionService extends BaseService {

    /**
     * <p>
     * Field tfm: 服务
     * </p>
     */
    @Autowired
    private TtFunctionMapper tfm;

    /**
     * <p>
     * Field trfm: 服务
     * </p>
     */
    @Autowired
    private TtRoleFunctionMapper trfm;

    /**
     * <p>
     * Field ifem: 功能操作扩展
     * </p>
     */
    @Autowired
    private IFunctionExpMapper ifem;

    /**
     * <p>
     * Description: 返回菜单树
     * </p>
     * 
     * @param loginName 当前登录名
     * @param appCode 应用代码
     * @return 菜单树脚本
     */
    public String getMenuTreeAccordion(String loginName, String appCode) {
        StringBuffer script = null;
        script = new StringBuffer();
        script.append(" <div id=\"menu_tree_accordion\" "
                + "class=\"easyui-accordion\" data-options=\"fit:false,border:false\"> ");
        //获得menu
        List<Map<String, Object>> menuList = null;
        menuList = this.getFunctionListMenu(loginName, appCode);
        if (!CollectionUtils.isEmpty(menuList)) {
            for (Map<String, Object> menu : menuList) {
                String menuName = null;
                String menuCode = null;
                menuName = menu.get("FUNCTION_NAME").toString();
                menuCode = menu.get("FUNCTION_CODE").toString();
                script.append(" <div title=\"" + menuName + "\" data-options=\"fit:false,border:false\"> ");
                script.append(" <ul class=\"easyui-tree menu-tree\"> ");
                //获得function
                List<Map<String, Object>> functionList = null;
                functionList = this.getFunctionList(loginName, menuCode);
                if (!CollectionUtils.isEmpty(functionList)) {
                    for (Map<String, Object> function : functionList) {
                        String functionCode = null;
                        String functionName = null;
                        String reqeustUrl = null;
                        functionCode = function.get("FUNCTION_CODE").toString();
                        functionName = function.get("FUNCTION_NAME").toString();
                        reqeustUrl = function.get("REQUEST_URL").toString();
                        script.append(" <li><span> ");
                        script.append(" <a href=\"#\" onclick=\"addTab('" + functionCode + "','" + functionName + "','"
                                + reqeustUrl + "');\">" + functionName + "</a> ");
                        script.append(" </span></li> ");
                    }
                }
                script.append(" </ul> ");
                script.append(" </div> ");
            }
        }
        script.append(" </div> ");

        return script.toString();
    }

    /**
     * <p>
     * Description: 返回应用列表
     * </p>
     * 
     * @param loginName 当前登陆人
     * @return 结果集
     */
    public List<Map<String, Object>> loadAppData(String loginName) {
        //获得当前用户已经有的功能
        List<String> hasFunction = ifem.getUserHasFunctionCode(loginName);
        if (!CollectionUtils.isEmpty(hasFunction)) {
            //获得当前用户已经有的目录
            String hasFunctionCodes = org.apache.commons.lang3.StringUtils.join(hasFunction.toArray(), ",");
            List<String> functionMenu = ifem.getFunctionListCode(hasFunctionCodes);
            if (!CollectionUtils.isEmpty(functionMenu)) {
                String functionMenuCodes = org.apache.commons.lang3.StringUtils.join(functionMenu.toArray(), ",");
                List<Map<String, Object>> functionApp = ifem.getFunctionList(functionMenuCodes, null);
                return functionApp;
            }
        }
        return null;
    }

    /**
     * <p>
     * Description: 返回用户已有的功能目录
     * </p>
     * 
     * @param loginName 用户名
     * @param parentFunctionCode 上级代码
     * @return 结果集
     */
    public List<Map<String, Object>> getFunctionListMenu(String loginName, String parentFunctionCode) {
        //获得当前用户已经有的功能
        List<String> hasFunction = ifem.getUserHasFunctionCode(loginName);
        if (!CollectionUtils.isEmpty(hasFunction)) {
            //获得当前用户已经有的目录
            String hasFunctionCodes = org.apache.commons.lang3.StringUtils.join(hasFunction.toArray(), ",");
            List<Map<String, Object>> functionMenu = ifem.getFunctionList(hasFunctionCodes, parentFunctionCode);
            return functionMenu;
        }
        return null;
    }

    /**
     * <p>
     * Description: 返回用户已有的功能
     * </p>
     * 
     * @param loginName 用户名
     * @param parentFunctionCode 上级代码
     * @return 结果集
     */
    public List<Map<String, Object>> getFunctionList(String loginName, String parentFunctionCode) {
        List<Map<String, Object>> hasFunction = ifem.getUserHasFunction(loginName, parentFunctionCode);
        return hasFunction;
    }

    /**
     * <p>
     * Description: 根据需求返回节点数据
     * </p>
     * 
     * @param loginName 当前登陆人
     * @param levelNo 级别
     * @param parentFunctionCode 上级代码(可以为空)
     * @return 节点数据
     */
    //    public List<Map<String, Object>> getFunctionList(String loginName, int levelNo, String parentFunctionCode) {
    //        final StringBuffer SQL = new StringBuffer("");
    //        SQL.append("    SELECT DISTINCT ");
    //        SQL.append("    D.FUNCTION_CODE,    ");
    //        SQL.append("    D.FUNCTION_NAME,    ");
    //        SQL.append("    D.STATUS,   ");
    //        SQL.append("    D.PARENT_FUNCTION_CODE, ");
    //        SQL.append("    D.SORT, ");
    //        SQL.append("    D.LEVEL_NO, ");
    //        SQL.append("    D.REQUEST_URL   ");
    //        SQL.append("    FROM TT_FUNCTION D  ");
    //        SQL.append("    WHERE D.LEVEL_NO=" + levelNo + "  ");
    //        if (!StringUtils.isEmpty(parentFunctionCode)) {
    //            SQL.append("    AND D.PARENT_FUNCTION_CODE='" + parentFunctionCode + "'  ");
    //        }
    //        SQL.append("    START WITH D.FUNCTION_CODE IN ( ");
    //        SQL.append("    SELECT C.FUNCTION_CODE FROM TT_USER_ROLE A,TT_ROLE_FUNCTION B,TT_FUNCTION C ");
    //        SQL.append("    WHERE A.ROLE_CODE=B.ROLE_CODE   ");
    //        SQL.append("    AND B.FUNCTION_CODE=C.FUNCTION_CODE ");
    //        SQL.append("    AND C.STATUS=1  ");
    //        SQL.append("    AND A.LOGIN_NAME='" + loginName + "'    ");
    //        SQL.append("    GROUP BY C.FUNCTION_CODE    ");
    //        SQL.append("    )   ");
    //        SQL.append("    CONNECT BY PRIOR D.PARENT_FUNCTION_CODE=D.FUNCTION_CODE ");
    //        SQL.append("    ORDER BY D.SORT ASC ");
    //        return this.getImqm().queryMap(SQL.toString());
    //    }

    /**
     * <p>
     * Description: 获得功能对象
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 功能对象
     */
    public TtFunction loadFunction(String functionCode) {
        return this.tfm.selectByPrimaryKey(functionCode);
    }

    /**
     * <p>
     * Description: 更新功能
     * </p>
     * 
     * @param valueMap 提交的更新值
     * @param functionCode 功能代码
     * @param loginName 操作人
     * @throws Exception 异常
     */
    public void editFunction(Map<String, String[]> valueMap, String functionCode, String loginName) throws Exception {
        //更新当前功能
        TtFunction tf = this.tfm.selectByPrimaryKey(functionCode);
        tf = AutoLoadBean.load(tf, valueMap);
        tf.setUpdateBy(loginName);
        tf.setUpdateDate(new Date());
        this.tfm.updateByPrimaryKey(tf);

        //更新子节点
        this.enableOrDisableLogic(tf, loginName);
    }

    /**
     * <p>
     * Description: 删除功能
     * </p>
     * 
     * @param functionCode 功能代码
     */
    public void deleteFunction(String functionCode) {
        this.deleteLogic(functionCode, null);
    }

    /**
     * <p>
     * Description: 删除节点逻辑
     * </p>
     * 
     * @param functionCode 功能代码
     * @param parentFunctionCode 上级功能代码
     */
    private void deleteLogic(String functionCode, String parentFunctionCode) {
        //获得所有关联的节点
        TtFunctionCriteria tfc = new TtFunctionCriteria();
        if (!StringUtils.isEmpty(functionCode)) {//删除本级
            tfc.createCriteria().andFunctionCodeEqualTo(functionCode);
        } else if (!StringUtils.isEmpty(parentFunctionCode)) {//删除下级
            tfc.createCriteria().andParentFunctionCodeEqualTo(parentFunctionCode);
        }
        List<TtFunction> functionList = this.tfm.selectByExample(tfc);
        if (!CollectionUtils.isEmpty(functionList)) {
            for (int i = 0; i < functionList.size(); i++) {
                //删除角色功能关联表
                TtRoleFunctionCriteria trfc = null;
                trfc = new TtRoleFunctionCriteria();
                trfc.createCriteria().andFunctionCodeEqualTo(functionList.get(i).getFunctionCode());
                this.trfm.deleteByExample(trfc);

                //删除功能
                this.tfm.deleteByPrimaryKey(functionList.get(i).getFunctionCode());

                //递归向下删除
                deleteLogic(null, functionList.get(i).getFunctionCode());
            }
        }
    }

    /**
     * <p>
     * Description: 获得所有子节点
     * </p>
     * 
     * @param functionCode 当前节点
     * @param parentFunctionCode 父节点
     * @return 结果集
     */
    private List<Map<String, Object>> getAllChildNode(String functionCode, String parentFunctionCode) {
        //返回值
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        //获得所有关联的节点
        TtFunctionCriteria tfc = new TtFunctionCriteria();
        if (!StringUtils.isEmpty(functionCode)) {//本级
            tfc.createCriteria().andFunctionCodeEqualTo(functionCode);
        } else if (!StringUtils.isEmpty(parentFunctionCode)) {//下级
            tfc.createCriteria().andParentFunctionCodeEqualTo(parentFunctionCode);
        } else {
            return rv;
        }
        List<TtFunction> functionList = this.tfm.selectByExample(tfc);
        if (!CollectionUtils.isEmpty(functionList)) {
            for (int i = 0; i < functionList.size(); i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("FUNCTION_CODE", functionList.get(i).getFunctionCode());
                item.put("STATUS", functionList.get(i).getStatus());
                rv.add(item);

                //循环添加下级
                rv.addAll(getAllChildNode(null, functionList.get(i).getFunctionCode()));
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 返回所有的父节点
     * </p>
     * 
     * @param functionCode 当前节点
     * @param parentFunctionCode 父节点
     * @return 结果集
     */
    private List<Map<String, Object>> getAllParentNode(String functionCode, String parentFunctionCode) {
        //返回值
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        //获得所有关联的节点
        TtFunctionCriteria tfc = new TtFunctionCriteria();
        if (!StringUtils.isEmpty(functionCode)) {//本级
            tfc.createCriteria().andFunctionCodeEqualTo(functionCode);
        } else if (!StringUtils.isEmpty(parentFunctionCode)) {//上级
            tfc.createCriteria().andFunctionCodeEqualTo(parentFunctionCode);
        } else {
            return rv;
        }
        List<TtFunction> functionList = this.tfm.selectByExample(tfc);
        if (!CollectionUtils.isEmpty(functionList)) {
            for (int i = 0; i < functionList.size(); i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("FUNCTION_CODE", functionList.get(i).getFunctionCode());
                item.put("STATUS", functionList.get(i).getStatus());
                rv.add(item);

                //循环添加下级
                rv.addAll(getAllParentNode(null, functionList.get(i).getParentFunctionCode()));
            }
        }
        return rv;
    }

    /**
     * 
     * <p>
     * Description: 启用停用逻辑
     * </p>
     * 
     * @param f 功能代码
     * @param l 操作人
     */
    private void enableOrDisableLogic(TtFunction f, String l) {
        final String DISABLEVALUE = "0"; //停用
        final String ENABLEVALUE = "1"; //启用
        if (DISABLEVALUE.equals(f.getStatus())) { //如果节点为停用,则子节点通通停用
            //获得所有子节点
            List<Map<String, Object>> nodeList = null;
            nodeList = getAllChildNode(f.getFunctionCode(), null);

            //依次更新子节点,更新为停用
            if (!CollectionUtils.isEmpty(nodeList)) {
                for (int i = 0; i < nodeList.size(); i++) {
                    //只有状态为[启用]的时候在更新
                    if (ENABLEVALUE.equals(nodeList.get(i).get("STATUS").toString())) {
                        TtFunction tf = null;
                        tf = new TtFunction();
                        tf.setFunctionCode(nodeList.get(i).get("FUNCTION_CODE").toString());
                        tf.setStatus(DISABLEVALUE);
                        tf.setUpdateBy(l);
                        tf.setUpdateDate(new Date());
                        this.tfm.updateByPrimaryKeySelective(tf);
                    }
                }
            }
        } else if (ENABLEVALUE.equals(f.getStatus())) { //启用,如果启用,则依次启用所有关联的父节点
            //获得所有父节点
            List<Map<String, Object>> nodeList = null;
            nodeList = getAllParentNode(f.getFunctionCode(), null);

            //依次更新父节点,更新为启用
            if (!CollectionUtils.isEmpty(nodeList)) {
                for (int i = 0; i < nodeList.size(); i++) {
                    //只有状态为[停用]的时候在更新
                    if (DISABLEVALUE.equals(nodeList.get(i).get("STATUS").toString())) {
                        TtFunction tf = null;
                        tf = new TtFunction();
                        tf.setFunctionCode(nodeList.get(i).get("FUNCTION_CODE").toString());
                        tf.setStatus(ENABLEVALUE);
                        tf.setUpdateBy(l);
                        tf.setUpdateDate(new Date());
                        this.tfm.updateByPrimaryKeySelective(tf);
                    }
                }
            }
        }
    }

    /**
     * <p>
     * Description: 保存功能
     * </p>
     * 
     * @param tf 功能对象
     * @param loginName 登录名
     * @return 操作结果
     */
    public Map<String, Object> addFunction(TtFunction tf, String loginName) {
        //定义返回参数
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();

        //保存
        tf.setCreateBy(loginName);
        tf.setCreateDate(new Date());
        this.tfm.insert(tf);

        //设定返回值
        rv.put("code", 1);

        return rv;
    }

    /**
     * <p>
     * Description: 校验功能代码是否存在
     * </p>
     * 
     * @param functionCode 功能代码
     * @return false:不通过,true:通过
     */
    public boolean functionCodeUniqueCheck(String functionCode) {
        TtFunctionCriteria tfc = null;
        tfc = new TtFunctionCriteria();
        tfc.createCriteria().andFunctionCodeEqualTo(functionCode);
        return this.tfm.countByExample(tfc) > 0 ? false : true;
    }

    /**
     * <p>
     * Description: 返回系统节点
     * </p>
     * 
     * @param hasChildren 是否包含子节点 true:包含,false:不包含
     * @return 系统节点
     */
    public List<Map<String, Object>> getAppNode(boolean hasChildren) {

        //获得系统节点(系统节点的PARENT_FUNCTION_CODE为空,深度级别(LEVEL_NO)为1)
        final StringBuffer APP_SQL = new StringBuffer("");
        APP_SQL.append(" SELECT * FROM TT_FUNCTION");
        APP_SQL.append(" WHERE PARENT_FUNCTION_CODE IS NULL");
        APP_SQL.append(" AND LEVEL_NO=1");
        APP_SQL.append(" ORDER BY SORT ASC");
        List<Map<String, Object>> appNode = null;
        appNode = this.getImqm().queryMap(APP_SQL.toString());

        //加载菜单节点(accordion层)
        if (hasChildren) {
            if (!CollectionUtils.isEmpty(appNode)) {
                for (int i = 0; i < appNode.size(); i++) {
                    List<Map<String, Object>> accordionNode = null;
                    accordionNode = this.getAccordionNode(appNode.get(i).get("FUNCTION_CODE").toString(), true);

                    //如果有子节点,则添加子节点
                    if (!CollectionUtils.isEmpty(accordionNode)) {
                        appNode.get(i).put("children", accordionNode);
                    }
                }
            }
        }

        return appNode;
    }

    /**
     * <p>
     * Description: 获得菜单清单
     * </p>
     * 
     * @param parentFunctionCode 上级应用代码
     * @param hasChildren 是否包含子集
     * @return 菜单清单
     */
    public List<Map<String, Object>> getAccordionNode(String parentFunctionCode, boolean hasChildren) {
        //获得菜单节点(accordion层,深度级别(LEVEL_NO)为2)
        final StringBuffer ACCORDION_NODE_SQL = new StringBuffer("");
        ACCORDION_NODE_SQL.append(" SELECT * FROM TT_FUNCTION");
        ACCORDION_NODE_SQL.append(" WHERE PARENT_FUNCTION_CODE = '" + parentFunctionCode + "'");
        ACCORDION_NODE_SQL.append(" AND LEVEL_NO=2");
        ACCORDION_NODE_SQL.append(" ORDER BY SORT ASC");
        List<Map<String, Object>> accordionNode = null;
        accordionNode = this.getImqm().queryMap(ACCORDION_NODE_SQL.toString());

        //加载功能节点(function层)
        if (hasChildren) {
            if (!CollectionUtils.isEmpty(accordionNode)) {
                if (!CollectionUtils.isEmpty(accordionNode)) {
                    for (int i = 0; i < accordionNode.size(); i++) {
                        Map<String, Object> item = null;
                        item = accordionNode.get(i);
                        List<Map<String, Object>> functionNode = null;
                        functionNode = this.getFunctionNode(item.get("FUNCTION_CODE").toString(), hasChildren);

                        //如果有子节点,则添加子节点
                        if (!CollectionUtils.isEmpty(functionNode)) {
                            accordionNode.get(i).put("children", functionNode);
                        }
                    }
                }
            }
        }

        return accordionNode;
    }

    /**
     * <p>
     * Description: 获得功能清单
     * </p>
     * 
     * @param parentFunctionCode 上级菜单代码
     * @param hasChildren 是否包含子集(暂未启用)
     * @return 功能清单
     */
    public List<Map<String, Object>> getFunctionNode(String parentFunctionCode, boolean hasChildren) {
        //获得功能节点(function层,深度级别(LEVEL_NO)为3)
        final StringBuffer FUNCTION_NODE_SQL = new StringBuffer("");
        FUNCTION_NODE_SQL.append(" SELECT * FROM TT_FUNCTION");
        FUNCTION_NODE_SQL.append(" WHERE PARENT_FUNCTION_CODE = '" + parentFunctionCode + "'");
        FUNCTION_NODE_SQL.append(" AND LEVEL_NO=3");
        FUNCTION_NODE_SQL.append(" ORDER BY SORT ASC");
        List<Map<String, Object>> functionNode = null;
        functionNode = this.getImqm().queryMap(FUNCTION_NODE_SQL.toString());
        return functionNode;
    }
}
