package com.llsfw.core.security.annotation;

import java.lang.annotation.*;

import com.llsfw.core.common.Constants;

/**
 * <p>
 * ClassName: CurrentUser
 * </p>
 * <p>
 * Description: 绑定当前登录的用户
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月22日
 * </p>
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     * 
     * @return
     */
    String value() default Constants.CURRENT_LOGIN_NAME;

}
