package com.controller.sys;

import com.common.consts.Const;
import com.common.enums.IsSendEnumType;
import com.common.enums.SysMessageEnumType;
import com.common.services.api.APIClientRequest;
import com.common.utils.*;
import com.common.utils.httpclient.ObjectToMapGen;
import com.entity.DicUnit;
import com.entity.sys.SysNotice;
import com.entity.sys.SysUser;
import com.service.communal.DicUtilsService;
import com.util.SysUserHelp;
import com.vo.sys.SysNoticeVO;
import com.vo.taskStu.ResultEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class SysNoticeHandler {

    @Resource
    private DicUtilsService dicUtilsService;


    /**     /notice/findNoticeList
     *
        查询系统公告列表
     */
    @RequestMapping(value="/findNoticeList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DatatablesResponse findCase(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start,
                                       @RequestParam(required=false,defaultValue="10")Integer length, SysNotice notice){
        SysUser user = (SysUser) request.getAttribute("user");
        String userVOStr = GsonUtils.getGson().toJson(user);
        JSONObject userVOObj = JSONObject.fromObject(userVOStr);
        //查询条件
        SysNotice sysNotice = GsonUtils.toBean(search,SysNotice.class);
        //分页条件
        if (sysNotice == null){
            sysNotice = new SysNotice();
        }
        /*查询条件*/
//        String sysNoticeStr = GsonUtils.getGson().toJson(sysNotice);
//        JSONObject sysNoticeObj = JSONObject.fromObject(sysNoticeStr);
//        sysNoticeObj.putAll(userVOObj);
        ObjectToMapGen objectToMapGen = new ObjectToMapGen(sysNotice);
        Map map = objectToMapGen.getMap();
        map.put("userId",user.getId());
        map.put("start",""+start);
        map.put("length",""+length);
        PageHelpVO pageHelpVO = null;
        try {
//            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, "/notice/findNoticeList", pageVOObj);
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, "/notice/findNoticeList", map);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
        DatatablesResponse<SysNoticeVO> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /**
     *   /notice/findNoticeById
     * @param httpSession
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value="/findNoticeById",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String findNoticeById(HttpSession httpSession, HttpServletRequest request, String id,String userNoticId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("userNoticId",userNoticId);
        String jsonStr = "";
        try {
            jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, "notice/findNoticeById", jsonObject);
            System.out.println(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /*
    *   根据查询当前用户的机构编码
    *
    *   /notice/addSysNoticeSysUnit
    * */
    @RequestMapping(value = "/addSysNoticeSysUnit",produces = "application/json;charset=UTF-8")
    public @ResponseBody ResponseVO addSysNoticeSysUnit(HttpServletRequest request,SysNotice sysNotice){
        SysUser user = (SysUser) request.getAttribute("user");
        //获取用户地区编码
        String userDicUnit = SysUserHelp.getSysUserDicUnit(user);
        DicUnit dicUnitByCode = dicUtilsService.findDicUnitByCode(userDicUnit);//发送方编码，名称
        sysNotice.setSendUnitCode(dicUnitByCode.getCode());
        sysNotice.setSendUnitName(dicUnitByCode.getName());
        sysNoticeValueS(sysNotice, user);
        DicUnit dicUnitByCode1 = dicUtilsService.findDicUnitByCode(sysNotice.getReceiveUnitCode());//接收方编码，名称
        sysNotice.setReceiveUnitName(dicUnitByCode1.getName());
        ObjectToMapGen objectToMapGen = new ObjectToMapGen(sysNotice);
        Map map = objectToMapGen.getMap();
        map.put("userId",user.getId());
        String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, "notice/addSysNoticeSysUnit", map);
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONObject respContent = (JSONObject) jsonObject.get("data");
        Object code = respContent.get("code");
        ResponseVO responseVO = new ResponseVO(ResponseVO.SUCCESS, "", Arrays.asList());
        if ((Integer)respContent.get("code")==ResponseVO.SUCCESS){

        }else {
            responseVO=  new ResponseVO(ResponseVO.FAIL, "", Arrays.asList());
        }
        return  responseVO;
    }

    private void sysNoticeValueS(SysNotice sysNotice, SysUser user) {
        sysNotice.setId(IDGenerator.getorderNo());
        sysNotice.setTitle("公告");
        sysNotice.setSenderNo(user.getId());
        sysNotice.setSenderName(user.getName());
        sysNotice.setMsgClass(SysMessageEnumType.GONG_GAO.getValue());
        sysNotice.setIsSend(IsSendEnumType.HAS_SEND.getValue());
        sysNotice.setCreatTime(new Date());
        sysNotice.setSendingTime(new Date());
    }

    /*
    *   根据查询当前用户的机构编码
    *
    *   /notice/countSysUserNoticeNoRead
    * */


//    @RequestMapping(value = "/countSysUserNoticeNoRead",produces = "application/json;charset=UTF-8")
//    private @ResponseBody ResponseVO countSysUserNoticeNoRead(HttpServletRequest request){
//        SysUser user = (SysUser) request.getAttribute("user");
//        Map<String, Object> map = new HashMap<>();
//        map.put("userId",user.getId());
//        String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, "notice/countSysUserNoticeNoRead", map);
//        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
//        JSONObject respContent = (JSONObject) jsonObject.get("data");
//        ResponseVO responseVO = new ResponseVO(ResponseVO.SUCCESS, "", Arrays.asList(respContent.get("data")));
//        if ((Integer)respContent.get("code")==ResponseVO.SUCCESS){
//
//        }else {
//            responseVO=  new ResponseVO(ResponseVO.FAIL, "", Arrays.asList());
//        }
//        return responseVO;
//    }

    @RequestMapping(value = "/getNoticeList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultEntity getNoticeList(HttpServletRequest request){
        ResultEntity resultData = new ResultEntity();
        JSONObject objectOrg = new JSONObject();
        try {
            String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, "notice/getNoticeList",objectOrg);
            JSONObject object = JSONObject.fromObject(jsonStr);
            JSONArray respContent = (JSONArray) object.get("data");
            resultData.setCode("200");
            resultData.setMsg("公告获取成功！");
            resultData.setData(respContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

}
