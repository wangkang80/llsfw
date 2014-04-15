/**
 * ServiceParamService.java
 * Created at 2013-12-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.serverparam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.mapper.standard.TtServerGlobalParametersMapper;
import com.llsfw.core.model.standard.TtServerGlobalParameters;
import com.llsfw.core.model.standard.TtServerGlobalParametersCriteria;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: ServiceParamService
 * </p>
 * <p>
 * Description: 参数维护
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月4日
 * </p>
 */
@Service
public class ServiceParamService extends BaseService {

    /**
     * <p>
     * Field tsgp: 参数dao
     * </p>
     */
    @Autowired
    private TtServerGlobalParametersMapper tsgpm;

    /**
     * <p>
     * Description: 删除参数
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 操作结果
     */
    public Map<String, Object> deleteParam(String parametersCode) {
        final Map<String, Object> RV = new HashMap<String, Object>();
        this.tsgpm.deleteByPrimaryKey(parametersCode);
        RV.put("code", "1");
        return RV;
    }

    /**
     * <p>
     * Description: 存储逻辑
     * </p>
     * 
     * @param tsgp 参数对象
     * @param isupdate true:更新,false:新增
     * @return 返回操作结果
     */
    private Map<String, Object> saveLogic(TtServerGlobalParameters tsgp, boolean isupdate) {
        final Map<String, Object> RV = new HashMap<String, Object>();

        //将CODE都转换为大写
        tsgp.setParametersCode(tsgp.getParametersCode().toUpperCase());
        tsgp.setParametersTypeCode(tsgp.getParametersTypeCode().toUpperCase());

        //查看类型代码是否存在
        final TtServerGlobalParametersCriteria TSGPC = new TtServerGlobalParametersCriteria();
        TSGPC.createCriteria().andParametersTypeCodeEqualTo(tsgp.getParametersTypeCode());
        if (this.tsgpm.countByExample(TSGPC) > 0) { //存在
            //获取现有的类型描述(保证类型描述统一)
            final TtServerGlobalParameters ITEM = this.tsgpm.selectByExample(TSGPC).get(0);
            tsgp.setParametersTypeName(ITEM.getParametersTypeName());
        } else { //不存在
            //查看类型描述是否存在(保证描述唯一)
            final TtServerGlobalParametersCriteria TSGPC2 = new TtServerGlobalParametersCriteria();
            TSGPC2.createCriteria().andParametersTypeNameEqualTo(tsgp.getParametersTypeName());
            if (this.tsgpm.countByExample(TSGPC2) > 0) { //存在
                RV.put("code", "-1");
                RV.put("message", "类型描述已经存在,请修改类型描述");
                return RV;
            }
        }

        //更新
        if (isupdate) {
            this.tsgpm.updateByPrimaryKey(tsgp);
        } else {
            this.tsgpm.insert(tsgp);
        }

        RV.put("code", "1");
        return RV;
    }

    /**
     * <p>
     * Description: 修改参数
     * </p>
     * 
     * @param valueMap 请求参数
     * @param parametersCode 主键
     * @return 修改结果
     * @throws Exception 异常
     */
    public Map<String, Object> editParameters(Map<String, String[]> valueMap, String parametersCode) throws Exception {
        TtServerGlobalParameters tsgp = this.tsgpm.selectByPrimaryKey(parametersCode);
        tsgp = AutoLoadBean.load(tsgp, valueMap);
        return this.saveLogic(tsgp, true);
    }

    /**
     * <p>
     * Description:加载参数对象
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 参数对象
     */
    public TtServerGlobalParameters loadParam(String parametersCode) {
        return this.tsgpm.selectByPrimaryKey(parametersCode);
    }

    /**
     * <p>
     * Description: 保存参数
     * </p>
     * 
     * @param tsgp 需保存参数的对象
     * @return 返回保存结果
     */
    public Map<String, Object> addParameters(TtServerGlobalParameters tsgp) {
        return this.saveLogic(tsgp, false);
    }

    /**
     * <p>
     * Description: 校验参数代码是否重复
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 是否重复 true:通过,false:不通过
     */
    public boolean parametersCodeUniqueCheck(String parametersCode) {
        final TtServerGlobalParametersCriteria TSGPC = new TtServerGlobalParametersCriteria();
        TSGPC.createCriteria().andParametersCodeEqualTo(parametersCode);
        return this.tsgpm.countByExample(TSGPC) > 0 ? false : true;
    }

    /**
     * <p>
     * Description: 返回参数列表
     * </p>
     * 
     * @param parametersCode 参数代码
     * @param parametersDesc 参数描述
     * @param parametersTypeCode 参数类型
     * @return 参数列表
     */
    public List<Map<String, Object>> getParamsList(String parametersCode, String parametersDesc,
            String parametersTypeCode) {

        final StringBuffer SQL = new StringBuffer("");
        SQL.append("    SELECT * FROM TT_SERVER_GLOBAL_PARAMETERS WHERE 1=1 ");
        if (StringUtils.hasText(parametersCode)) {
            SQL.append(" and UPPER(PARAMETERS_CODE) like UPPER('%" + parametersCode + "%') ");
        }
        if (StringUtils.hasText(parametersDesc)) {
            SQL.append(" and UPPER(PARAMETERS_DESC) like UPPER('%" + parametersDesc + "%') ");
        }
        if (StringUtils.hasText(parametersTypeCode)) {
            SQL.append(" and PARAMETERS_TYPE_CODE = '" + parametersTypeCode + "'");
        }
        SQL.append(" ORDER BY PARAMETERS_TYPE_CODE ASC ");

        return this.getImqm().queryMap(SQL.toString());
    }

    /**
     * <p>
     * Description: 返回所有的参数类型
     * </p>
     * 
     * @param hasAll 是否包含全部
     * @return 所有的参数类型
     */
    public List<Map<String, Object>> getAllParamType(boolean hasAll) {
        final StringBuffer SQL = new StringBuffer("");
        SQL.append("    SELECT  ");
        SQL.append("    PARAMETERS_TYPE_CODE,   ");
        SQL.append("    PARAMETERS_TYPE_NAME    ");
        SQL.append("    FROM TT_SERVER_GLOBAL_PARAMETERS    ");
        SQL.append("    GROUP BY PARAMETERS_TYPE_CODE,PARAMETERS_TYPE_NAME  ");
        SQL.append("    ORDER BY PARAMETERS_TYPE_CODE ASC   ");

        //获得获得全部参数类型
        final List<Map<String, Object>> TYPELIST = this.getImqm().queryMap(SQL.toString());

        //是否包含[全部]的选项
        if (hasAll) {
            //定义[全部]查询条件
            final Map<String, Object> ALL = new HashMap<String, Object>();
            ALL.put("PARAMETERS_TYPE_CODE", "");
            ALL.put("PARAMETERS_TYPE_NAME", "全部");

            //将[全部]添加到列表的第一个
            TYPELIST.add(0, ALL);
        }

        return TYPELIST;
    }
}
