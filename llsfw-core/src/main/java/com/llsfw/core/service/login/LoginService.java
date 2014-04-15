/**
 * LoginService.java
 * Created at 2013-12-15
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.mapper.standard.TtApplicationUserMapper;
import com.llsfw.core.model.standard.TtApplicationUser;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: LoginService
 * </p>
 * <p>
 * Description: 登陆服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月15日
 * </p>
 */
@Service
public class LoginService extends BaseService {
    /**
     * <p>
     * Field tau: 用户操作
     * </p>
     */
    @Autowired
    private TtApplicationUserMapper taum;

    /**
     * <p>
     * Description: 获得用户对象
     * </p>
     * 
     * @param userName 用户名
     * @return 用户
     */
    public TtApplicationUser loadUser(String userName) {
        return this.taum.selectByPrimaryKey(userName);
    }

    /**
     * <p>
     * Description: 验证用户是否正确
     * </p>
     * 
     * @param userName 用户名
     * @param password 密码
     * @return 操作结果
     */
    public Map<String, Object> loginCheck(String userName, String password) {
        //设定返回值
        Map<String, Object> rv = null;
        rv = new HashMap<String, Object>();

        //获得用户
        TtApplicationUser tau = null;
        tau = this.taum.selectByPrimaryKey(userName);

        //校验
        if (null == tau || "0".equals(tau.getUserStatus())) {
            rv.put("code", "error");
            rv.put("errorMessage", "用户不存在或被停用");
            return rv;
        }

        //加密密码
        password = DigestUtils.md5Hex(password);

        //比较密码
        if (!password.equals(tau.getLoginPassword())) {
            rv.put("code", "error");
            rv.put("errorMessage", "密码错误");
            return rv;
        }

        rv.put("code", "success");
        return rv;

    }
}
