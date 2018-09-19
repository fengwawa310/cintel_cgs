package com.controller.cas;

import com.common.consts.Const;
import com.common.utils.GsonUtils;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.caseInfo.ApStaff;
import com.entity.caseInfo.CaseSeries;
import com.entity.caseInfo.EtCase;
import com.entity.suspect.EtWarning;
import com.service.caseInfo.CaseService;
import com.service.utils.EWSurveilService;

import net.sf.json.JSONObject;
import net.sf.jsqlparser.statement.select.FromItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weipc on 2018/1/2.
 */
@Controller
@RequestMapping("/case")
public class CaseControlller {
    @Resource
    private CaseService caseService;
    
    @Resource
    private EWSurveilService eWSurveilService;

    /**
     * 案件添加
     */
    @RequestMapping(value="/save",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject save(HttpSession httpSession, HttpServletRequest request, EtCase etCase,String apStaffs){
    	
        JSONObject result =new JSONObject();
        try {
            Date date = new Date();
            //案件编号 modify on 18-02-03 页面输入
            //etCase.setCaseNo(IDGenerator.getorderNo());
            //案件状态
            etCase.setCaseState(1802);
            caseService.save(etCase,apStaffs);
            result.put("flag",true);
            result.put("msg","添加成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","添加失败！原因："+e);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 彻底删除案件
     */
    @RequestMapping(value="/delete",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject delete(HttpSession httpSession, HttpServletRequest request, String id){
        JSONObject result =new JSONObject();
        try {
            caseService.delete(id);
            result.put("flag",true);
            result.put("msg","案件删除成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","删除失败！原因："+e);
            e.printStackTrace();
        }
        return result;

    }
    /**
     * 案件删除到回收站/恢复回收站中的案件
     */
    @RequestMapping(value="/setIsAbandon",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject setIsAbandon(HttpSession httpSession, HttpServletRequest request, EtCase etCase){
        JSONObject result =new JSONObject();
        try {
            etCase.setModifyTime(new Date());
            caseService.update(etCase);
            result.put("flag",true);
            result.put("msg",(etCase.getIsAbandon()==1?"案件已删除到回收站！":"案件已恢复！"));
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg",(etCase.getIsAbandon()==1?"删除失败！原因：":"恢复失败！原因：")+e);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 案件关注和取消关注
     */
    @RequestMapping(value="/setIsFollow",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject setIsFollow(HttpSession httpSession, HttpServletRequest request, EtCase etCase){
        JSONObject result =new JSONObject();
        try {
            etCase.setModifyTime(new Date());
            caseService.update(etCase);
            result.put("flag",true);
            result.put("msg",(etCase.getIsFollow()==1?"案件关注成功！":"案件取消关注成功！"));
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg",(etCase.getIsFollow()==1?"关注失败！原因：":"取消关注失败！原因：")+e);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 案件加入历史库
     */
    @RequestMapping(value="/setIsArchive",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject setIsArchive(HttpSession httpSession, HttpServletRequest request, EtCase etCase){
        JSONObject result =new JSONObject();
        try {
            etCase.setModifyTime(new Date());
            caseService.update(etCase);
            result.put("flag",true);
            result.put("msg","案件加入历史成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","加入历史失败！原因："+e);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 案件编辑
     */
    @RequestMapping(value="/update",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject update(HttpSession httpSession, HttpServletRequest request,String caseinfo,String apStaffs,String apStaffIds){
        JSONObject result =new JSONObject();
        EtCase etCase = GsonUtils.toBean(caseinfo, EtCase.class);
        try {
            etCase.setModifyTime(new Date());
            caseService.update(etCase,apStaffs,apStaffIds);
            result.put("flag",true);
            result.put("msg","案件编辑成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","编辑失败！原因："+e);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 查询单个案件信息
     * @return
     */
    @RequestMapping(value="/findCaseById",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> findCaseById(HttpSession httpSession, HttpServletRequest request, String id,String caseNo){
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("caseNo",caseNo);
        Map<String,Object> resultMap=null;
        try {
            resultMap = caseService.findCaseById(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 查询所有案件信息
     * @return
     */
    @RequestMapping(value="/findCase",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public PageHelpVO findCase(HttpSession httpSession, HttpServletRequest request,PageVO pageVO,EtCase etCas,String search){
        //模糊查询条件
        EtCase etCase = GsonUtils.toBean(search, EtCase.class);
        //其他地方查询案件列表条件
        if(etCase==null){
            etCase= new EtCase();
            //案件id
            etCase.setId(etCas.getId());
            //审批标识
            etCase.setIsApproval(etCas.getIsApproval());
            //删除标识
            etCase.setIsAbandon(etCas.getIsAbandon());
            //归档标识
            etCase.setIsArchive(etCas.getIsArchive());
            //关注标识
            etCase.setIsFollow(etCas.getIsFollow());
            //人工确认标识
            etCase.setRelation(etCas.getRelation());
            //人工标记标识
            etCase.setLable(etCas.getLable());

            etCase.setSuspectIDCardNo(etCas.getSuspectIDCardNo());
            etCase.setManaUnitCode(etCas.getManaUnitCode());
        }
        /*
         * 同级单位间不共享；
         * 数据对上级单位默认授权；
         */
        etCase.setDeceSigns(etCas.getDeceSigns());
        etCase.setCaseClass("0".equals(etCase.getCaseClass())?null:etCase.getCaseClass());
        PageHelpVO pageHelpVO =null;
        try {
            pageHelpVO=caseService.findCase(pageVO,etCase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }
    /**
     * 案件串并
     * @return
     */
    @RequestMapping(value="/caseSeries",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public PageHelpVO caseSeries(HttpSession httpSession, HttpServletRequest request,PageVO pageVO,CaseSeries caseSeries){
        PageHelpVO pageHelpVO =null;
        try {
            pageHelpVO=caseService.caseSeries(pageVO,caseSeries);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }


    /**
     * @Author: sky
     * @Description:根据人员身份证号查询相关涉案人员
     * @Date: 上午11:25 2018/4/20
     * @param: httpSession
    request
    apStaff
     */
    @RequestMapping(value="/findInvolveByIdcard",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<ApStaff> findInvolveByIdcard(HttpSession httpSession, HttpServletRequest request, ApStaff apStaff){
        List<ApStaff> apStaffs = caseService.findInvolveByIdcard(apStaff);
        //循环判断人员涉案涉警情况并打标签
        for(ApStaff apStaff02 : apStaffs){
            boolean sameCase = false,sameAlarm = false;
            //获取每位人员的案件警情编号
            List<String> caseNos = caseService.findCaseNoByStaffId(apStaff02.getStaffId());
            for(String caseNo : caseNos){
                if (caseNo.startsWith("A")){
                    //编号以A开头  案件
                    sameCase = true;
                }
                if (caseNo.startsWith("J")){
                    //编号以J开头 警情
                    sameAlarm = true;
                }
            }
            /**
             * 涉案  涉警类型，1、涉案；2、涉警；3及涉案又涉警
             */
            if (sameCase){
                apStaff02.setTagType("1");
            }
            if (sameAlarm){
                apStaff02.setTagType("2");
            }
            if (sameAlarm && sameCase){
                apStaff02.setTagType("3");
            }
        }

        return apStaffs;

    }



    /**
     * @Author: sky
     * @Description:获取案件数量  在研案件数
     * @Date: 10:01 2018/5/24
     * @param: httpSession
    request
     */
    @RequestMapping(value="/getCaseNum",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> getCaseNum(HttpSession httpSession, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<>();
        int caseNum = caseService.getCaseNum();
        resultMap.put("caseNum",caseNum);
        return resultMap;
    }

}
