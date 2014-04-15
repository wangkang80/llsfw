/**
 * ParamService.java
 * Created at 2013-12-02
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.serverparam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.llsfw.core.common.SystemParamDef;
import com.llsfw.core.mapper.standard.TtServerGlobalParametersMapper;
import com.llsfw.core.model.standard.TtServerGlobalParameters;
import com.llsfw.core.model.standard.TtServerGlobalParametersCriteria;

/**
 * <p>
 * ClassName: ServerParamService
 * </p>
 * <p>
 * Description: 获取参数服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月2日
 * </p>
 */
@Service
public class ParamService {
    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field tgpm: SERVER端参数定义
     * </p>
     */
    @Autowired
    private TtServerGlobalParametersMapper tgpm;

    @Autowired
    @Qualifier("systemParam")
    private SystemParamDef systemParam;

    /**
     * <p>
     * Description: 返回服务端参数<br />
     * 优先获取数据库中的配置,如果数据库中没有配置,则从spring-config.xml配置中获取<br />
     * 如果都没有设置的话,则返回为null
     * </p>
     * 
     * @param code 参数名称
     * @return 值
     */
    public String getServerParamter(String code) {
        TtServerGlobalParameters tsgp = this.getServerGlobalParamters(code);
        if (null != tsgp) {
            return tsgp.getParametersValue();
        }
        return systemParam.getData().get(code);
    }

    /**
     * <p>
     * Description: 返回服务端参数
     * </p>
     * 
     * @param typeCode 参数类型
     * @return 参数列表
     */
    public Map<String, String> getServerParamters(String typeCode) {
        Map<String, String> rv = new HashMap<String, String>();
        List<TtServerGlobalParameters> tsgpList = this.getServerGlobalParamtersByTypeCode(typeCode);
        if (CollectionUtils.isEmpty(tsgpList)) {
            for (TtServerGlobalParameters tsgp : tsgpList) {
                rv.put(tsgp.getParametersCode(), tsgp.getParametersValue());
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 返回服务端参数
     * </p>
     * 
     * @param code 参数名称
     * @return 参数对象
     */
    public TtServerGlobalParameters getServerGlobalParamters(String code) {
        return this.tgpm.selectByPrimaryKey(code);
    }

    /**
     * <p>
     * Description: 返回服务端参数
     * </p>
     * 
     * @param typeCode 参数类型
     * @return 参数列表
     */
    public List<TtServerGlobalParameters> getServerGlobalParamtersByTypeCode(String typeCode) {
        final TtServerGlobalParametersCriteria TSGPC = new TtServerGlobalParametersCriteria();
        TSGPC.createCriteria().andParametersTypeCodeEqualTo(typeCode);
        return this.tgpm.selectByExample(TSGPC);
    }
}
