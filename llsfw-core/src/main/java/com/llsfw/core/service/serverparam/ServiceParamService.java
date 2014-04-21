/**
 * ServiceParamService.java
 * Created at 2013-12-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.serverparam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
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
    public JsonResult<String> deleteParam(String parametersCode) {
        this.tsgpm.deleteByPrimaryKey(parametersCode);
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public JsonResult<String> editParameters(Map<String, String[]> valueMap, String parametersCode) throws Exception {
        TtServerGlobalParameters tsgp = this.tsgpm.selectByPrimaryKey(parametersCode);
        tsgp = AutoLoadBean.load(tsgp, valueMap);
        this.tsgpm.updateByPrimaryKey(tsgp);
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public JsonResult<String> addParameters(TtServerGlobalParameters tsgp) {
        this.tsgpm.insert(tsgp);
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public List<Map<String, Object>> getParamsList(String parametersCode, String parametersDesc) {
        final StringBuffer SQL = new StringBuffer("");
        SQL.append("    SELECT * FROM TT_SERVER_GLOBAL_PARAMETERS WHERE 1=1 ");
        if (StringUtils.hasText(parametersCode)) {
            SQL.append(" and UPPER(PARAMETERS_CODE) like UPPER('%" + parametersCode + "%') ");
        }
        if (StringUtils.hasText(parametersDesc)) {
            SQL.append(" and UPPER(PARAMETERS_DESC) like UPPER('%" + parametersDesc + "%') ");
        }
        SQL.append(" ORDER BY PARAMETERS_CODE ASC ");

        return this.getImqm().queryMap(SQL.toString());
    }
}
