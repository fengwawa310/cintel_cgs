package com.controller.sys;

import com.common.consts.Global;
import com.controller.login.annote.CurrentUser;
import com.entity.sys.SysUser;
import com.service.sys.SysRoleService;
import com.service.sys.SysUserService;
import com.vo.sys.SysMenuVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绯荤粺鑿滃崟
 *
 * @author admin
 * @create 2017-11-30 11:11
 **/
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    /**
     *  /sysMenu/list
     *  @鎻忚堪:鍚庡彴鍐呭绠＄悊鑿滃崟灞曠ず
     *  @浣滆��: sqc
     *  @鏃ユ湡: 2016骞�7鏈�13鏃�
     *  @鏃堕棿: 涓嬪崍4:12:08
     */
    @RequestMapping("/list")
    public  @ResponseBody
    List<SysMenuVO> list(@CurrentUser SysUser loginUser, HttpServletRequest request, HttpServletResponse response) {
        SysUser user = (SysUser)request.getAttribute("user");
        List<SysMenuVO> list= sysUserService.findUserMenu(user);
        return list;
    }

    @RequestMapping("getSysConfig")
    public @ResponseBody
    Map<String,String> getSysConfig(HttpServletRequest request, HttpServletResponse response, Model model){
        String sysName = Global.getUnicodeConfig("sys.name");
        String sysLogo = Global.getConfig("sys.logo");
        //System.out.println(sysLogo+sysName);
        Map<String,String> sysMap = new HashMap<>();
        sysMap.put("sysName",sysName);
        sysMap.put("sysLogo",sysLogo);
        return sysMap;
    }

    @RequestMapping("/getFastHref")
    @ResponseBody
    public Map<String,Object> getFastHref(HttpServletRequest request, HttpServletResponse response, Model model){
        Map<String,Object> resultMap = new HashMap<>();
        String fastHref = Global.getUnicodeConfig("fastHref");
        String[] hrefs = fastHref.split(";");
        StringBuffer sb = new StringBuffer();
        sb.append("<option value=\"\">请选择快速链接</option>");
        for (int i = 0;i<hrefs.length;i++){
            String hrefName = hrefs[i].split(",")[0];
            String hrefLink = hrefs[i].split(",")[1];
            String option = "<option value=\""+hrefLink+"\">"+hrefName+"</option>";
            sb.append(option);
        }
        model.addAttribute("fastHref",sb.toString());
        resultMap.put("fastHref",sb.toString());
        return resultMap;
    }

}
