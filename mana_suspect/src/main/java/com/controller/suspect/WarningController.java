package com.controller.suspect;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.common.utils.StringHelp;
import com.common.utils.TimeUtil;
import com.entity.suspect.EtWarning;
import com.vo.warning.PageVoWarning;
import com.service.suspect.WarningServiceInfc;
import com.vo.suspect.WarningListItemVo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Auri on 2018/1/6.
 * Desc:
 */
@Controller
@RequestMapping("/warning")
public class WarningController extends BaseControlller {

    @Resource
    private WarningServiceInfc warningServiceInfc;

    /**
     * 分页条件查询预警信心
     *
     * @param pageVO
     * @return
     */
    @RequestMapping(value="/findByPage",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public PageHelpVO findWarningsByPage(HttpSession httpSession, HttpServletRequest request, PageVoWarning pageVO) {
        if (pageVO == null) {
            pageVO = new PageVoWarning();
            pageVO.setStart(0);
            pageVO.setLength(10);
        }
//        JSONObject result;
        // 时间调整
        Date lowerLimitTime = TimeUtil.parseTimeStr(pageVO.getwCreateLowerLimtTimeStr(), "yyyy-MM-dd HH:mm:ss");
        pageVO.setWarningCreateLowerLimitTime(lowerLimitTime);
        Date upperLimitTime = TimeUtil.parseTimeStr(pageVO.getwCreateUpperLimtTimeStr(), "yyyy-MM-dd HH:mm:ss");
        pageVO.setWarningCreateUpperLimitTime(upperLimitTime);

        PageHelpVO<WarningListItemVo> pageHelpVO = new PageHelpVO<>();
        try {
            List<WarningListItemVo> ets = warningServiceInfc.findByPage(pageVO);
            long total = warningServiceInfc.countTotal(pageVO);
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
     * @param pageVO
     * @param No
     * @return
     */
    @RequestMapping("/findByNo")
    @ResponseBody
    public EtWarning findWarningByNo(HttpSession httpSession, HttpServletRequest request, PageVO pageVO, String No) {
        if (StringHelp.isEmpty(No)) {
            return null;
        }
        EtWarning et = null;
        try {
            et = warningServiceInfc.findByNo(No);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return et;
    }

    /**
     * 依照编号查询预警信息
     *
     * @param httpSession
     * @param request
     * @param et
     * @return
     */
    @RequestMapping("/updateWarning")
    @ResponseBody
    public JSONObject updateWarning(HttpSession httpSession, HttpServletRequest request, EtWarning et) {
        if (et == null || StringHelp.isEmpty(et.getWarningId()) || StringHelp.isEmpty(et.getId())) {
            return buildFailMsg("预警信息为空或编号ID为空");
        }
        JSONObject result;
        int num = 0;
        try {
            num = warningServiceInfc.update(et);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0) {
            result = buildSuccessMsg("修改成功");
        } else {
            result = buildFailMsg("修改失败");
        }
        return result;
    }

    /**
     * 依据布控任务编号 取消一条任务
     *
     * @param httpSession
     * @param request
     * @param warningNo
     * @return
     */
    @RequestMapping(value = "/deltWarning", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject removeWarning(HttpSession httpSession, HttpServletRequest request, String warningNo) {
        if (StringHelp.isEmpty(warningNo)) {
            return buildFailMsg("布控任务编号不能为空");
        }
        JSONObject result;
        int num = 0;
        try {
            num = warningServiceInfc.deleteByNo(warningNo);//warningNo
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0) {
            result = buildSuccessMsg("预警信息删除成功");
        } else {
            result = buildFailMsg("预警信息删除失败");
        }
        return result;
    }

    /*
* 保存一条有效的预警信息
*/
    @RequestMapping("/addWarning")
    @ResponseBody
    public JSONObject addWarning(HttpSession httpSession, HttpServletRequest request, EtWarning etWarning) {
        if (!check(etWarning)) {
            return buildFailMsg("预警信息内容有误");
        }
        JSONObject result;
        int saveNum = 0;
        try {
            saveNum = warningServiceInfc.save(etWarning);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (saveNum > 0) {
            result = buildSuccessMsg("预警信息保存成功");
        } else {
            result = buildFailMsg("预警信息保存失败");
        }
        return result;
    }

    private boolean check(EtWarning et) {
        if (et == null || StringHelp.isEmpty(et.getWarningId()) || StringHelp.isEmpty(et.getbCtrlIdcardNum())) {
            return false;
        }
        return true;
    }
}
