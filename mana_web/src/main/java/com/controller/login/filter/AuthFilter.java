package com.controller.login.filter;

import com.entity.sys.SysUser;
import com.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

//	@Resource
//	private SysUserService sysUserService;

	public void destroy() {

	}

	// 1,doFilter  方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括表单数据、cookie和HTTP请求头）的完全访问。
	//第二个参数为ServletResponse，通常在简单的过滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或 JSP页。
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		// 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中无法得到的方法，就要把此request对象构造成HttpServletRequest
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 取得根目录所对应的绝对路径:
	/*	String currentURL = request.getRequestURI();
		String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length()); // 截取到当前文件名用于比较
		SysUser sysUser = new SysUser();
		sysUser.setUserName(username);

		if(username!=null&&!"".equals(username))
		{
			if(!(targetURL.indexOf("/view/error.html")!=-1||
				 targetURL.indexOf("/MessageListHandler/count.action")!=-1||
				 targetURL.indexOf("/login/login.do")!=-1||
				 targetURL.indexOf("/view/login2.html")!=-1||
				 targetURL.indexOf("/login/page.do")!=-1||
				 targetURL.indexOf("mana_web/view")!=-1||
				 targetURL.indexOf(".js")!=-1||
				 targetURL.indexOf(".css")!=-1||
				 targetURL.indexOf(".png")!=-1||
				 targetURL.indexOf(".jpg")!=-1||
				 targetURL.indexOf(".woff")!=-1||
				 targetURL.indexOf(".ttf")!=-1))
			{//定时任务除外 判断操作人是否在使用 在使用时，记录最后使用时间，超过5分钟后异地可以登录
				//获取访问的ip
				String remoteAddr = request.getRemoteAddr();
				String forwarded = request.getHeader("X-Forwarded-For");
				String realIp = request.getHeader("X-Real-IP");
				String ip = LogonStatistics.getIp(remoteAddr, forwarded, realIp);
				sysUser.setLogonIp(ip);//设置登录ip
				sysUser.setLogonTime(new Date());//设置登录时间
				if(!LogonStatistics.refresh(sysUser)){
					response.sendRedirect(request.getContextPath() + "/view/error.html");
					return;
				}

			}
		}*/
		// 加入filter链继续向下执行
		filterChain.doFilter(request, response);// .调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作为它的一个参数。在调用此对象的doFilter方法时，激活下一个相关的过滤器。如果没有另一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活。

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}