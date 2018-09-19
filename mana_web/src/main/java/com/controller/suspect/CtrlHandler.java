package com.controller.suspect;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.*;
import com.entity.sys.SysUser;
import com.google.gson.JsonSyntaxException;
import com.util.SysUserHelp;
import com.vo.ctrl.PageVoCtrl;
import com.vo.suspect.CtrlListItemVo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Auri on 2018/1/5.
 * Desc:
 */
@Controller
@RequestMapping("ctrlHandler")
public class CtrlHandler extends BaseSHandler {

    @RequestMapping(value = "/handlePage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DatatablesResponse pageFind(HttpSession httpSession, HttpServletRequest request, Integer start, Integer length, PageVoCtrl pageVo) {
        String uid = (String) request.getAttribute("uid");
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        String serverName = "ctrl/findByPage";
        // 测试时间段
//        String upperLimit = "2018-01-08 09:00:00";
//        String lowerLimit = "2018-01-08 10:00:00";
//        String upperLimit = "";
//        String lowerLimit = "";
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("applicantCode", user.getId());
        hashMap.put("start", "" + pageVo.getStart());
        hashMap.put("length", "" + pageVo.getLength());
        hashMap.put("suspectNo", pageVo.getSuspectNo());
        hashMap.put("suspectIDCardNo", pageVo.getSuspectIDCardNo());
        hashMap.put("suspectName", pageVo.getSuspectName());
        hashMap.put("upperLimitTimeStr", pageVo.getUpperLimitTimeStr());
        hashMap.put("lowerLimitTimeStr", pageVo.getLowerLimitTimeStr());
        PageHelpVO<CtrlListItemVo> pageHelpVO = null;
        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serverName, hashMap);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            String respContentStr = jsonObject.get("data").toString();
            pageHelpVO = GsonUtils.toBean(respContentStr, PageHelpVO.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
        DatatablesResponse<CtrlListItemVo> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /**
     *
     */
    @RequestMapping(value = "/handleCancel", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject cancel(String id) {
        String serverName = "ctrl/cancelCtrl";
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ctrlNo", id);
        String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serverName, hashMap);
        return JSONObject.fromObject(jsonStr);
    }

    /**
     * OneDove
     * 重点人员布控录入
     */
    @RequestMapping("/ctrsave")
    @ResponseBody
    public String ctrsave(HttpSession httpSession, HttpServletRequest request, String bCtrlPCode, String bCtrlName, String bCtrlIdcardNum,
                          String bCtrlGender, String bCtrlPhone, String bCtrlPlateNum, String bCtrlPClass) {
        //获取User 的ID
        String uid = (String) request.getAttribute("uid");
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ctrlId", IDGenerator.getorderNo());
        hashMap.put("bCtrlPCode", bCtrlPCode);
        hashMap.put("bCtrlName", bCtrlName);
        hashMap.put("bCtrlIdcardNum", bCtrlIdcardNum);
        hashMap.put("bCtrlGender", bCtrlGender);
        hashMap.put("bCtrlPhone", bCtrlPhone);
        hashMap.put("applyUnitCode", SysUserHelp.getSysUserDicUnit(user));
        hashMap.put("applicantCode", uid);
        hashMap.put("applicantName", user.getName());
        hashMap.put("ctrlTaskState", new Integer(1500));
        hashMap.put("ctrlTaskClass", new Integer(1601));
        hashMap.put("bCtrlPClass", bCtrlPClass);
        hashMap.put("bCtrlPlateNum", bCtrlPlateNum);
        hashMap.put("isIntl", request.getParameter("isIntl"));
        String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "ctrl/addCtrl", hashMap);
        //String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, "ctrl/cancelCtrl", hashMap);
        return s;
    }

    @RequestMapping(value = "/findCtrlById", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> findCtrlById(HttpSession httpSession, HttpServletRequest request, String id) {
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        String jsonStr = "";
        try {
            jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "ctrl/findCtrlById", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("data", jsonStr);
        return map;
    }

}
