/**
 * LoginUser.java
 * Created at 2013-12-07
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.model.expand;

import java.io.Serializable;

import com.llsfw.core.model.standard.TtApplicationUser;

/**
 * <p>
 * ClassName: LoginUser
 * </p>
 * <p>
 * Description: 登陆对象
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月7日
 * </p>
 */
public class LoginUser implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 980906678432346642L;

    /**
     * <p>
     * Field user: 当前登陆对象
     * </p>
     */
    private TtApplicationUser user;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param user 用户对象
     */
    public LoginUser(TtApplicationUser user) {
        this.user = user;
    }

    public TtApplicationUser getUser() {
        return this.user;
    }

    public void setUser(TtApplicationUser user) {
        this.user = user;
    }

}
