/**
 * Copyright &copy; 2015-2020 <a href="http://www.cintel.com.cn/">Cintel</a> All rights reserved.
 */
package com.common;

import com.context.SpringContextHolder;
import com.entity.sys.SysUser;
import com.service.sys.SysRoleService;
import com.service.sys.SysUserService;
import org.apache.commons.lang.StringUtils;

import java.io.File;


/**
 * 用户工具类
 *
 * @author cintel
 * @version 2013-12-05
 */
public class CommonUtils {

//    private SpringContextHolder springContextHolder = null;
//
//    public SpringContextHolder getSpringContextHolder() {
//        return springContextHolder;
//    }
//
//    public void setSpringContextHolder(SpringContextHolder springContextHolder) {
//        this.springContextHolder = springContextHolder;
//    }
//
//    private static SysUserService sysUserService = SpringContextHolder.getBean(SysUserService.class);
//    private static SysRoleService sysRoleService = SpringContextHolder.getBean(SysRoleService.class);
//
//    /**
//     * 根据ID获取用户
//     *
//     * @param id
//     * @return 取不到返回null
//     */
//    public static SysUser get(String id) {
//        SysUser user = sysUserService.findUserById(id);
//        if (user == null) {
//            return null;
//        }
//        return user;
//    }

    /**
     * 获取项目在部署环境中的根路径
     *
     * @return
     */
    public static String getWEBINFPath() {
        String path = CommonUtils.class.getResource("").getPath();
        String rootName = "WEB-INF";
        int tempIndex = path.indexOf(rootName);
        String resultPath = path.substring(0, tempIndex + rootName.length());
        return resultPath;
    }

}
