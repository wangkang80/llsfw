/**
 * AuthService.java
 * Created at 2013-12-16
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * ClassName: AuthService
 * </p>
 * <p>
 * Description: 安全服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月16日
 * </p>
 */
@Service
public class AuthService extends BaseService {

    /**
     * <p>
     * Description: 返回用户所能使用的功能
     * </p>
     * 
     * @param loginName 用户名
     * @return 功能列表
     */
    public List<Map<String, Object>> getUserFunction(String loginName) {
        StringBuffer sql = null;
        sql = new StringBuffer();
        sql.append(" SELECT C.FUNCTION_CODE FROM TT_USER_ROLE A,TT_ROLE_FUNCTION B,TT_FUNCTION C");
        sql.append(" WHERE A.ROLE_CODE=B.ROLE_CODE");
        sql.append(" AND B.FUNCTION_CODE=C.FUNCTION_CODE");
        sql.append(" AND C.STATUS=1"); //只查询启用的功能
        sql.append(" AND A.LOGIN_NAME='" + loginName + "'");
        sql.append(" GROUP BY C.FUNCTION_CODE");
        return this.getImqm().queryMap(sql.toString());
    }
}
