package com.controller.login.filter;

import com.controller.login.ConstantsShiro;
import com.controller.login.shiro.ShiroRealm;
import com.entity.sys.SysUser;
import com.service.sys.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

    @Resource
    private SysUserService sysUserService;

    /**
     * webSocketHandShake之前调用，RedisSessionDAO之后调用
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        if(StringUtils.isNotBlank(username)) {
            SysUser sysUser = sysUserService.findUserByUsername(username);
            if(sysUser != null){
            	request.setAttribute(ConstantsShiro.CURRENT_USER, sysUser);
            	request.setAttribute("uid", sysUser.getId());
            	request.setAttribute("user", sysUser);
            }
        }
        return true;
    }
}
