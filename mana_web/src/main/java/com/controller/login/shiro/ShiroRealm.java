package com.controller.login.shiro;

import com.common.utils.DecriptUtil;
import com.entity.sys.SysUser;
import com.service.shiro.RedisSessionDAO;
import com.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;


/**
 * 自定义Realm
 */

public class ShiroRealm extends AuthorizingRealm {
	// 注入dao，访问数据库
	@Resource
	private SysUserService sysUserService;


    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(sysUserService.findRoles(username));
        authorizationInfo.setStringPermissions(sysUserService.findPermissions(username));
        return authorizationInfo;
    }
    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        Subject subject = SecurityUtils.getSubject();
        SysUser user = sysUserService.findUserByUsername(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,user.getPassword(), this.getClass().getSimpleName());
        return authenticationInfo;
    }
    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
	
}







