package com.controller.suspect;

import com.common.consts.Const;
import com.common.utils.*;
import com.entity.DicUnit;
import com.entity.suspect.EtCtrl;
import com.vo.ctrl.PageVoCtrl;
import com.service.communal.DicUtilsService;
import com.service.suspect.CtrlService;
import com.service.suspect.SuspectService;

import com.vo.suspect.CtrlDetailVo;
import com.vo.suspect.CtrlListItemVo;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Auri on 2018/1/5.
 * Desc:
 */
@Controller
@RequestMapping("/ctrl")
public class CtrlController extends BaseControlller {

    @Resource
    private CtrlService ctrlService;
    @Resource
    private DicUtilsService dicUtilsService;
    @Resource
    private SuspectService suspectService;

    /**
     * 分页条件查询预警信心
     *
     * @param pageVO
     * @return
     */
    @RequestMapping(value = "/findByPage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO<CtrlListItemVo> findCtrlsByPage(HttpSession httpSession, HttpServletRequest request, PageVoCtrl pageVO) {
        if (pageVO == null) {
            pageVO = new PageVoCtrl();
            pageVO.setStart(0);
            pageVO.setLength(10);
        }
//        JSONObject result;
        // 时间调整
        Date lowerLimitTime = TimeUtil.parseTimeStr(pageVO.getLowerLimitTimeStr(), "yyyy-MM-dd HH:mm:ss");
        pageVO.setCtrlCreateLowerLimiteTime(lowerLimitTime);
        Date upperLimitTime = TimeUtil.parseTimeStr(pageVO.getUpperLimitTimeStr(), "yyyy-MM-dd HH:mm:ss");
        pageVO.setCtrlCreateUpperLimitTime(upperLimitTime);

        PageHelpVO<CtrlListItemVo> pageHelpVO = new PageHelpVO<>();
        try {
            List<CtrlListItemVo> ets = ctrlService.findByPage(pageVO);
            long total = ctrlService.countTotal(pageVO);
            pageHelpVO.setList(ets);
            pageHelpVO.setTotal(total);
        } catch (Exception e) {
            e.printStackTrace();
//            result = buildFailMsg("分页查询失败");
        }
        return pageHelpVO;
    }

    /**
     * 依照编号查询预警信息
     *
     * @param httpSession
     * @param request
     * @param No
     * @return
     */
    @RequestMapping("/findByNo")
    @ResponseBody
    public EtCtrl findCtrlByNo(HttpSession httpSession, HttpServletRequest request, String No) {
        if (StringHelp.isEmpty(No)) {
            return null;
        }
        EtCtrl et = null;
        try {
            et = ctrlService.findByNo(No);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return et;
    }

    /**
     * 修改一条布控任务
     *
     * @param httpSession
     * @param request
     * @param et
     * @return
     */
    @RequestMapping("/modifyCtrl")
    @ResponseBody
    public JSONObject modifyCtrl(HttpSession httpSession, HttpServletRequest request, EtCtrl et) {
        if (et == null || StringHelp.isEmpty(et.getCtrlId())) {
            return buildFailMsg("布控对象不能为空，并且布控任务编号不能为空");
        }
        JSONObject result;
        int num = 0;
        try {
            num = ctrlService.update(et);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0) {
            result = buildSuccessMsg("布控任务修改成功");
        } else {
            result = buildFailMsg("布控任务修改失败");
        }
        return result;
    }

    /**
     * 依据布控任务编号 取消一条任务
     *
     * @param httpSession
     * @param request
     * @param ctrlNo
     * @return
     */
    @RequestMapping(value = "/cancelCtrl", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject cancelCtrl(HttpSession httpSession, HttpServletRequest request, String ctrlNo) {
        if (StringHelp.isEmpty(ctrlNo)) {
            return buildFailMsg("布控任务编号不能为空");
        }
        JSONObject result;
        int num = 0;
        try {
            EtCtrl etctrl = ctrlService.findByNo(ctrlNo);
            etctrl.getbCtrlPCode();
//            EtSuspect etSuspect = new EtSuspect();
//            etSuspect.setId(etctrl.getbCtrlPCode());
//            etSuspect.setSuspectClass(new Integer(1300));// 重置回 重点人员 类型
            int editStatus = suspectService.changeCtrlState(etctrl.getbCtrlPCode(),Const.SUSPECT_IS_INTL_NO);
            num = ctrlService.cancelCtrlAndCtrlKey(ctrlNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0) {
            result = buildSuccessMsg("任务取消成功");
        } else {
            result = buildFailMsg("任务取消失败");
        }
        return result;
    }

    /*
  * 保存一条有效的布控任务，保存成功后自动添加重点人员监测关键字记录
  */
    @RequestMapping(value = "/addCtrl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject addCtrl(HttpSession httpSession, HttpServletRequest request, EtCtrl etCtrl) {

        String isIntl = request.getParameter("isIntl");
        if (!checkCtrl(etCtrl) || StringUtils.isBlank(isIntl)) {
            return buildFailMsg("布控任务内容有误");
        }
        JSONObject result;
        int saveNum = 0;
        int editStatus = -1;
        try {
        	if(Const.SUSPECT_IS_INTL_YES.intValue() == Integer.parseInt(isIntl)){
        		DicUnit findDicUnitByCode = dicUtilsService.findDicUnitByCode(etCtrl.getApplyUnitCode());
                etCtrl.setId(IDGenerator.getorderNo());
                if(findDicUnitByCode != null)
                	etCtrl.setApplyUnitName(findDicUnitByCode.getName());
        		saveNum = ctrlService.addCtrlAndCtrlKey(etCtrl);
        	}else if(Const.SUSPECT_IS_INTL_NO.intValue() == Integer.parseInt(isIntl)){
        		saveNum = ctrlService.cancelCtrlAndCtrlKeyByIdCardNum(etCtrl.getbCtrlIdcardNum());
        	}
            editStatus = suspectService.changeCtrlState(etCtrl.getbCtrlPCode(),Integer.parseInt(isIntl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (saveNum > 0) {
            result = buildSuccessMsg("任务发布成功");
        } else {
            result = buildFailMsg("任务发布失败");
        }
        return result;
    }

//    private JSONObject buildSuccessMsg(String msgStr) {
//        JSONObject result = new JSONObject();
//        result.put("flag", true);
//        result.put("msg", msgStr);
//        return result;
//    }
//
//    private JSONObject buildFailMsg(String msgStr) {
//        JSONObject result = new JSONObject();
//        result.put("flag", false);
//        result.put("msg", msgStr);
//        return result;
//    }

    private boolean checkCtrl(EtCtrl etCtrl) {
        if (etCtrl == null || StringHelp.isEmpty(etCtrl.getbCtrlIdcardNum())) {
            return false;
        }
        return true;
    }

    /**
     * 查询单个案件信息
     *
     * @return
     */
    @RequestMapping(value = "/findCtrlById", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> findCtrlById(HttpSession httpSession, HttpServletRequest request, String id) {

        Map<String, Object> map = new HashMap<>();
        try {
            CtrlDetailVo detailVo = ctrlService.findDetailById(id);
            map.put("ctrl",detailVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
