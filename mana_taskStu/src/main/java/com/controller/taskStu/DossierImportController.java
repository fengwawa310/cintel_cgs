package com.controller.taskStu;

import com.common.enums.IsReadEnumType;
import com.common.utils.*;
import com.entity.DicUnit;
import com.entity.sys.SysNotice;
import com.entity.sys.SysUser;
import com.entity.sys.SysUserNotice;
import com.param.sys.SysNoticeParam;
import com.request.sys.PageVoReq;
import com.request.sys.SysNoticeReq;
import com.service.communal.DicUtilsService;
import com.service.sys.SysNoticeService;
import com.service.sys.SysUserNoticeService;
import com.service.sys.SysUserService;
import com.service.taskStu.DossierImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dossier")
public class DossierImportController {

    @Resource
    private DossierImportService dossierImportService;


    /**
     *      /dossier/findEpClueList
     *
     * 按用户查询公告列表
     * @return
     */
    @RequestMapping(value="/findEpClueList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO findEpClueList(HttpSession httpSession, HttpServletRequest request, PageVoReq pageVoReq
                               ){

        PageVO pageVO = new PageVO(Integer.parseInt(pageVoReq.getStart()),Integer.parseInt(pageVoReq.getLength()));
        PageHelpVO pageHelpVO =null;
        try {
            pageHelpVO=dossierImportService.findEpClueList(pageVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }


}
