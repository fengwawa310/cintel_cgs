package com.controller.suspect;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.GsonUtils;
import com.common.utils.IDNoHelp;
import com.common.utils.PageHelpVO;
import com.entity.suspect.EtCtrl;
import com.entity.suspect.EtSuspect;
import com.entity.suspect.EtWarning;
import com.entity.sys.SysUser;
import com.google.gson.JsonSyntaxException;
import com.vo.warning.PageVoWarning;
import com.vo.suspect.WarningListItemVo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Auri on 2018/1/8.
 * Desc:预警信息 Handler
 */
@Controller
@RequestMapping("warningHandler")
public class WarningHandler extends BaseSHandler{

    @RequestMapping("/handleAdd")
    public String add() {
        String serverName = "warning/addWarning";
        HashMap<String, Object> mm = new HashMap<>();
        mm.put("id", IDNoHelp.buildID());
        mm.put("warningId", IDNoHelp.buildNO(EtWarning.class));
        mm.put("ctrlTaskId", IDNoHelp.buildNO(EtCtrl.class));
        mm.put("bCtrlPCode", IDNoHelp.buildNO(EtSuspect.class));
        mm.put("bCtrlName", "Auri" + (int) Math.random() * 100);
        mm.put("bCtrlIdcardNum", "45030417660808" + (int) Math.random() * 10000);
        mm.put("manaUnitCode", "" + (int) Math.random() * 1000000);
        mm.put("manaUnitName", "管控单位名称");
        mm.put("manaPCode", "" + (int) Math.random() * 1000000);
        mm.put("manaPName", "管控人姓名");
        mm.put("warningDetal", "这是预警详情");
        String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serverName, mm);
        return s;
    }

    @RequestMapping(value = "/handleRemove", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String remove(HttpSession httpSession, HttpServletRequest request, String id) {
        String serverName = "warning/deltWarning";
        HashMap<String, Object> mm = new HashMap<>();
        mm.put("warningNo", id);
        String s =  APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serverName, mm);
        return s;
    }

    @RequestMapping(value = "/handlePage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DatatablesResponse pageFind(HttpSession httpSession, HttpServletRequest request, Integer start, Integer length, PageVoWarning pageVo) {
        String uid = (String) request.getAttribute("uid");
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        //String regionCodeStr = identifyRegionCode(user);
        String serverName = "warning/findByPage";
        HashMap<String, Object> mm = new HashMap<>();
        mm.put("start", "" + pageVo.getStart());
        mm.put("length", "" + pageVo.getLength());
        mm.put("suspectNo", pageVo.getSuspectNo());
        mm.put("suspectIDCardNo", pageVo.getSuspectIDCardNo());
        mm.put("suspectName", pageVo.getSuspectName());
        mm.put("applicantCode", user.getId());
        mm.put("wCreateUpperLimtTimeStr", pageVo.getwCreateUpperLimtTimeStr());
        mm.put("wCreateLowerLimtTimeStr", pageVo.getwCreateLowerLimtTimeStr());
        mm.put("needDetail", pageVo.getNeedDetail().toString());
        //mm.put("regionCodeStr", regionCodeStr);
        PageHelpVO<WarningListItemVo> pageHelpVO = null;
        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serverName, mm);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            String respContentStr = jsonObject.get("data").toString();
            pageHelpVO = GsonUtils.toBean(respContentStr, PageHelpVO.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
        DatatablesResponse<WarningListItemVo> resp = DatatablesResponse.build(dataSet);
        return resp;
    }
}
