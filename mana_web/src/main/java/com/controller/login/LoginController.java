package com.controller.login;

import com.common.consts.Global;
import com.common.utils.DecriptUtil;
import com.controller.login.filter.LogonStatistics;
import com.controller.login.logincert.GenerateOraginalData;
import com.entity.sys.SysUser;
import com.service.shiro.RedisSessionDAO;
import com.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

/**
 * @描述:shiro控制用户登录
 * @作者: sqc
 * @日期: 2016年7月6日
 */
@RequestMapping("/login")
@Controller
public class LoginController {

	/** 认证地址 */
	private final String KEY_AUTHURL = "authURL";
	/** 应用标识 */
	private final String KEY_APP_ID = "appId";
	/** 获取属性列表控制   none：不获取属性；all 获取全部属性 ；portion 获取指定属性 */
	private final String kEY_ATTR_TYPE ="attrType";
	/** 当配置文件中attrType 值为portion时 获取查询指定属性   当且仅当attrType为portion时有效*/
	private final String KEY_ATTRIBUTES ="attributes";

	// 注入dao，访问数据库
	@Resource
	private SysUserService sysUserService;

//	@Resource
//	private RedisSessionDAO redisSessionDAO;

	@RequestMapping("/page.do")
	public String loginPage(HttpServletRequest req,HttpServletResponse resp){
		return  "/login";

		/** 证书认证流程 */
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//
//		try{
//			this.setProperties(KEY_APP_ID,request.getSession());
//			this.setProperties(KEY_AUTHURL,request.getSession());
//			this.setProperties(kEY_ATTR_TYPE, request.getSession());
//			this.setProperties(KEY_ATTRIBUTES, request.getSession());
//		}catch(Exception e){
//			System.out.println("从配置文件中获得应用标识，网关地址，属性列表控制项，认证方式发生异常");
//			System.err.println(e);
//		}
//
//		// 获取认证原文
//		String randNum = "";
//		try {
//			//服务器生成认证原文
//			randNum = GenerateOraginalData.generateRandomNum();
////			//验证网关生成认证原文
////			randNum = GenerateOraginalData.generateRandomNumFromGagewayServer();
//		} catch (Exception e) {
//			System.out.println("获取认证原文失败。" + e);
//		}
//		if (randNum == null || randNum.trim().equals("")) {
//			System.out.println("证书认证数据不完整！");
//			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		}
//
//		// 设置认证原文到session，用于程序向后传递，通讯报文中使用
//		request.getSession().setAttribute("original_data", randNum);
//
//		// 设置认证原文到页面，给页面程序提供参数，用于产生认证请求数据包
//		request.setAttribute("original", randNum);
//
//		return  "/loginCert";
	}


	/**
	 * 获取message.properties文件中的属性值赋予session
	 * @param httpSession
	 */
	private String setProperties(String key, HttpSession httpSession) {
		httpSession.setAttribute(key, Global.getMessage(key) == null ? null : Global.getMessage(key));
		return Global.getMessage(key) == null ? null : Global.getMessage(key);
	}

	@RequestMapping("/login.do")
	public  String login(Model model, SysUser user, HttpServletRequest request) {
		try{
			//使用shiro提供的方式进行认证
			String username = user.getUserName();
			String password = user.getPassword();
			if(username==null||password==null){
				return "login";
			}
			password = DecriptUtil.MD5(password);
			//获得、得到Subject===当前用户对象===,现在subject的状态为“未认证”状态
			Subject subject = SecurityUtils.getSubject();
			//创建用户名/密码身份验证Token（即用户身份/凭证）
			AuthenticationToken token = new UsernamePasswordToken(username, password);
			 //4、登录，即身份验证 
			subject.login(token);//调用到SecurityManager安全管理器，安全管理器调用Realm，在Realm中进行认证
			//SecurityManager接受到token(令牌)信息后会委托内置的Authenticator的实例（通常都是ModularRealmAuthenticator类的实例）调用authenticator.authenticate(token)

			/*uid存入redis缓存*/
			SysUser sysUser = sysUserService.findUserByUsername(username);
			String id = request.getSession().getId();
			//获取访问的ip
			/*String remoteAddr = request.getRemoteAddr();
			String forwarded = request.getHeader("X-Forwarded-For");
			String realIp = request.getHeader("X-Real-IP");
			String ip = LogonStatistics.getIp(remoteAddr, forwarded, realIp);
			sysUser.setLogonIp(ip);//设置登录ip
			sysUser.setLogonTime(new Date());//设置登录时间
			if(!LogonStatistics.logon(sysUser)) {
				return "login2";//不能异地登录
			}*/

//			Session session = redisSessionDAO.getSession(id);
//			session.setAttribute("uid", sysUser.getId());
//			session.setAttribute("user", sysUser);
//			redisSessionDAO.setSession(session);
//			return "redirect:/welcom/index.do";
			return "redirect:/wel";
			/*return "index-shiro";*/
		}catch (UnknownAccountException e) {//用户名不存在
			e.printStackTrace();
			//this.addActionError("用户名不存在！");
			return "login";
		}catch (IncorrectCredentialsException e) {//密码错误异常
			//this.addActionError("密码错误！");
			e.printStackTrace();
			return "login";
		}

	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value="/logout.do")
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		Subject currentUser = SecurityUtils.getSubject();

		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		SysUser loginUser = (SysUser)session.getAttribute("loginUser");
		if(loginUser!=null) {
			//删除登录信息

//			Global.userSocketSessionMap.remove(loginUser.getId());
		}
//		String username = (String) SecurityUtils.getSubject().getPrincipal();
//		LogonStatistics.unLogon(username);
		currentUser.logout();
		return "login";
//		return "loginCert";
	}



}
