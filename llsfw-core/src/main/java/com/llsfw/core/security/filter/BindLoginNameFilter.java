package com.llsfw.core.security.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.llsfw.core.common.Constants;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>
 * ClassName: BindLoginNameFilter
 * </p>
 * <p>
 * Description: 登陆后,将当前登陆名放入请求对象中
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月21日
 * </p>
 */
public class BindLoginNameFilter extends PathMatchingFilter {

    protected boolean onPreHandle(ServletRequest req, ServletResponse res, Object mappedValue) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        req.setAttribute(Constants.CURRENT_LOGIN_NAME, username);
        return true;
    }
}
