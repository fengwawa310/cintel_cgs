package com.controller.login;

import com.common.consts.Const;
import com.common.enums.AjaxRes;
import com.controller.BaseHandler;
import com.entity.DicCommon;
import com.entity.sys.SysMenu;
import com.entity.sys.SysUser;
import com.service.communal.DicUtilsService;
import com.service.sys.SysMenuService;
import com.service.sys.SysRoleService;
import com.service.sys.SysUserService;
import com.util.MenuTreeUtil;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @描述            :主页跳转
 * @作者            :	sqc
 * @日期            :	2016/12/15
 * @时间            :	9:23
 */

@RequestMapping("/wel")
@Controller
public class WelcomController extends BaseHandler {
    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private DicUtilsService dicUtilsService;
    /***
     *
     * @param model
     * @param request
     * @return
     */
    /*跳转到主页*/
    @RequestMapping("")
    public String goPage( Model model, HttpServletRequest request){
        return  "main";
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value="menu/getMenu", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMenu(HttpServletRequest request){	
		AjaxRes ar = this.getAjaxRes();
		SysUser user = (SysUser)request.getAttribute("user");
		String ref = (String)request.getParameter("ref");
		if(user == null){
			ar.setFailMsg("获取菜单失败");
			return ar;
		}
		
		List<SysMenu> menus= new ArrayList<SysMenu>();
		
		Object menu_o=null;
		try {
			//shiro获取用户信息,shiro管理的session
			Session session=SecurityUtils.getSubject().getSession();
			//设置用户
			session.setAttribute(Const.SESSION_USER,user);
			
			if(!"y".equals(ref)){	
				menu_o=session.getAttribute(Const.SESSION_MENULIST);
			}
			menus=(List<SysMenu>)menu_o;
			if(menus==null){
				List<String> roleids = sysUserService.findRoleIds(user.getId());
				if(!roleids.isEmpty()){
					Set<String> permissions = sysRoleService.findPermissions(roleids);
					if(!permissions.isEmpty()){
						menus = sysRoleService.findMenus(permissions);
						session.setAttribute(Const.SESSION_MENULIST, menus);
					}
				}
			}
			if(menus!=null){
				//将对象处理成树
				String html=MenuTreeUtil.buildTreeHtml(menus);			
				ar.setSucceed(html);
			}
		} catch (Exception e) {
			ar.setFailMsg("获取菜单失败");
		}			
		return ar;
	}


	@SuppressWarnings("unchecked")
	@RequestMapping(value="menu/getMenu02", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMenu02(HttpServletRequest request){
		AjaxRes ar = this.getAjaxRes();
		SysUser user = (SysUser)request.getAttribute("user");
		String ref = (String)request.getParameter("ref");
		if(user == null){
			ar.setFailMsg("获取菜单失败");
			return ar;
		}

		List<SysMenu> menus= new ArrayList<SysMenu>();

		Object menu_o=null;
		try {
			//shiro获取用户信息,shiro管理的session
			Session session=SecurityUtils.getSubject().getSession();
			//设置用户
			session.setAttribute(Const.SESSION_USER,user);

			if(!"y".equals(ref)){
				menu_o=session.getAttribute(Const.SESSION_MENULIST);
			}
			menus=(List<SysMenu>)menu_o;
			if(menus==null){
				List<String> roleids = sysUserService.findRoleIds(user.getId());
				if(!roleids.isEmpty()){
					Set<String> permissions = sysRoleService.findPermissions(roleids);
					if(!permissions.isEmpty()){
						menus = sysRoleService.findMenus(permissions);
						session.setAttribute(Const.SESSION_MENULIST, menus);
					}
				}
			}
			if(menus!=null){
				//将对象处理成树
				String html=MenuTreeUtil.buildTreeHtml02(menus);
				ar.setSucceed(html);
			}
		} catch (Exception e) {
			ar.setFailMsg("获取菜单失败");
		}
		return ar;
	}

    /*跳转到具体页面*/
    @RequestMapping("/page")
    public String goConcretePage(String id){
        if (StringUtils.isEmpty(id)){//用户登录首页展示页面。
            return "home_page";
        }else {
            SysMenu sysMenu = sysMenuService.findSysMenuById(id);
            return sysMenu.getHref();
        }
    }
    
    
    @RequestMapping(value="getDictSelect", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getDictSelect(HttpServletRequest request){
		AjaxRes ar=getAjaxRes();	
		try {
			String ids = (String)request.getParameter("ids");
			String keys = (String)request.getParameter("keys");
			Map<String, List<DicCommon>> idmap = new HashMap<String, List<DicCommon>>();
			if (StringUtils.isNoneBlank(ids) && StringUtils.isNoneBlank(keys)) {
				List<DicCommon> data = dicUtilsService.findDicCommonList(keys);
				idmap.put(ids, data);
			}
			ar.setSucceed(idmap);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		return ar;
	}
}
