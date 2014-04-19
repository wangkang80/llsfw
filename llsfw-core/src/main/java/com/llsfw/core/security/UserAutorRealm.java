/**
 * UserAutorRealm.java
 * Created at 2014年4月19日
 * Created by wangkang
 */
package com.llsfw.core.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.mapper.expand.ISecurityMapper;
import com.llsfw.core.mapper.standard.TtApplicationUserMapper;
import com.llsfw.core.model.standard.TtApplicationUser;

/**
 * <p>
 * ClassName: UserAutorRealm
 * </p>
 * <p>
 * Description: 用户权限验证
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月19日
 * </p>
 */
public class UserAutorRealm extends AuthorizingRealm {

    /**
     * <p>
     * Field taum: 用户dao
     * </p>
     */
    @Autowired
    private TtApplicationUserMapper taum;

    @Autowired
    private ISecurityMapper ism;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获得所关联的权限
        String username = (String) principals.getPrimaryPrincipal();
        List<String> roleList = ism.findUserRoles(username);
        List<String> rolePermissions = ism.findRolePermissions(roleList);
        List<String> userPermissions = ism.findUserPermissions(username);
        Set<String> permissions = new HashSet<String>();
        permissions.addAll(rolePermissions);
        permissions.addAll(userPermissions);

        //设置所关联的权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<String>(roleList));
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户信息
        String username = (String) token.getPrincipal();
        TtApplicationUser user = taum.selectByPrimaryKey(username);

        //判断账号是否正常
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if ("0".equals(user.getUserStatus())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(), //用户名
                user.getLoginPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),//salt=username+salt
                getName() //realm name
        );
        return authenticationInfo;
    }
}
