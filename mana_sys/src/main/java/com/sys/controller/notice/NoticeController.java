package com.sys.controller.notice;

import com.common.enums.IsReadEnumType;
import com.common.enums.UnitLevelEnumType;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    SysNoticeService sysNoticeService;

    @Resource
    SysUserNoticeService sysUserNoticeService;

    @Resource
    private DicUtilsService dicUtilsService;

    @Resource
    private SysUserService sysUserService;

    /**
     * 按用户查询公告列表
     * @return
     */
    @RequestMapping(value="/findNoticeList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO findCase(HttpSession httpSession, HttpServletRequest request, PageVoReq pageVoReq,
                               SysNoticeReq sysNoticeReq){

        SysUser sysUser = new SysUser();
        sysUser.setId(sysNoticeReq.getUserId());
        SysNotice sysNotice=new SysNotice();
        PageVO pageVO = new PageVO(Integer.parseInt(pageVoReq.getStart()),Integer.parseInt(pageVoReq.getLength()));
        PageHelpVO pageHelpVO =null;
        try {
            SysNoticeParam sysNoticeParam = new SysNoticeParam( sysUser,sysNotice);
            pageHelpVO=sysNoticeService.findNoticeList(pageVO,sysNoticeParam);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }


    /**
     *
     * 按id查询公告详情
     * @return
     */
    @RequestMapping(value="/findNoticeById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SysNotice findNoticeById(HttpSession httpSession, HttpServletRequest request,SysNoticeReq sysNoticeReq){
        SysNotice sysNotice = new SysNotice();
        sysNotice = sysNoticeService.findNoticeById(sysNoticeReq.getId());
        //用戶与公告关联表
        SysUserNotice sysUserNotice = new SysUserNotice();
        String userNoticId = sysNoticeReq.getUserNoticId();
        sysUserNotice.setId(userNoticId);
        sysUserNotice.setIsRead("1");
        int data = sysUserNoticeService.updateByPrimaryKeySelective(sysUserNotice);
        return sysNotice;
    }
    /**
     * 按id查询公告详情
     * @return
     */
    @RequestMapping(value="/addSysNoticeSysUnit",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseStateVO addSysNoticeSysUnit(HttpSession httpSession, HttpServletRequest request, SysNoticeReq sysNoticeReq){

        SysNotice sysNotice=SysNoticeReq.valueOf(sysNoticeReq);
        String sendUnitCode = sysNoticeReq.getReceiveUnitCode();
        DicUnit dicUnitByCode = dicUtilsService.findDicUnitByCode(sendUnitCode);
        List<SysUser> sysUserList= sysUserService.findUserBySysDict(dicUnitByCode.getGrade(),dicUnitByCode.getCode());
        for (SysUser sysUser : sysUserList) {//接收方用户
            SysUserNotice sysUserNotice = new SysUserNotice(
                    IDGenerator.getorderNo(),
                    sysNotice.getId(),
                    sysUser.getId(),
                    String.valueOf(IsReadEnumType.NO_READ.getValue()));
            if (!sysNoticeReq.getUserId().equals(sysUser.getId())) {//发现是自己不添加
                sysUserNoticeService.addSysUserNotice(sysUserNotice);
            }
        }
        SysUserNotice sysUserNotice = new SysUserNotice(
                IDGenerator.getorderNo(),
                sysNotice.getId(),
                sysNoticeReq.getUserId(),
                String.valueOf(IsReadEnumType.NO_READ.getValue()));
        sysUserNoticeService.addSysUserNotice(sysUserNotice);//发送方用户
        sysNoticeService.addSysNoticeSysUnit(sysNotice);
        ResponseStateVO responseStateVO = new ResponseStateVO(ResponseStateVO.SUCCESS, "");
        return responseStateVO;
    }

    /**
     *  /notice/countSysUserNoticeNoRead
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "/countSysUserNoticeNoRead",produces = "application/json;charset=UTF-8")
    private @ResponseBody
    ResponseVO countSysUserNoticeNoRead(HttpServletRequest request, String userId){
        Integer count=sysNoticeService.countSysUserNoticeNoRead(userId);
        ResponseVO responseVO = new ResponseVO(ResponseVO.SUCCESS, "", Arrays.asList(count));
        return responseVO;
    }

    @RequestMapping(value = "/getNoticeList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<String> getNoticeList(HttpServletRequest request){
        List<String> notices = new ArrayList<>();
        notices = sysNoticeService.getNoticeList();
        return notices;
    }


}
