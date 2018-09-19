package com.controller.infor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.utils.PageHelpVO;
import com.entity.alarm.EtAlarm;
import com.entity.infor.EtTipoff;
import com.entity.infor.EtTipoffMemo;
import com.entity.infor.ResponseVO;
import com.entity.message.MessageList;
import com.service.infor.ReportService;

import net.sf.json.JSONObject;

/**
 * 举报
 * Created by weipc on 2018/2/28.
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Resource
    private ReportService reportService;

    /**
     * 举报添加
     */
    @RequestMapping(value="/insert",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject insert(HttpSession httpSession, HttpServletRequest request,EtTipoff etTipoff){
        JSONObject result =new JSONObject();
        Boolean warning = false;
        try {
        	  //触发自动预警
        	warning= warning(etTipoff);
		} catch (Exception e) {
            logger.info("触发预警异常！");
            result.put("flag",false);
            result.put("msg","触发预警异常！");
            return result;
		}
        try {
            reportService.insert(etTipoff);
            result.put("flag",true);
            if(warning){
          	  //result.put("msg","添加成功！已触发预警请注意！");
            	result.put("msg","添加成功");
            }else{
            	 result.put("msg","添加成功");
            }

            logger.info("举报添加成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","添加失败！原因："+e);
            logger.info("举报添加失败！原因："+e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  自动预警
     */
    public Boolean warning(EtTipoff etTipoff) {

    	try {
    	Map<String,String> map = new HashMap<>();
    	map.put("reporterIdcard", etTipoff.getBeingReportedIdcard());//被举报人姓名身份证
    	map.put("reporterPhone", etTipoff.getBeingReportedPhone());//被举报人姓名电话号码
    	map.put("tipoffReporter", etTipoff.getBeingReported());//被举报人姓名
    	String sysuser = etTipoff.getSysuser();
    	JSONObject pageVOObj = JSONObject.fromObject(sysuser);
    	String sysUserId =pageVOObj.getString("id");
    	map.put("sysUserId", sysUserId);//登录用户主键
    	map.put("tipoffId", etTipoff.getTipoffId());//录入的对象
    	map.put("message", Const.XIAOXI_JUBAO_XINZENG);
    	int i = reportService.findBeingReport(map);
    	if( i == 1){
    		return true;
    	}else {
    		return false;
    	}
		} catch (Exception e) {
			return false;
		}
	}

	/**
     * 查询单个举报信息
     * @return
     */
    @RequestMapping(value="/findReportById",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> findReportById(HttpSession httpSession, HttpServletRequest request,String tipoffId){
        Map<String,Object> map=new HashMap<>();
        map.put("tipoffId",tipoffId);
        Map<String,Object> resultMap=null;
        try {
            resultMap = reportService.findReportById(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    /**
     * 详情流转列表
     */
    @RequestMapping(value="/findetTipoffMemoByIdList",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<EtTipoffMemo> findetTipoffMemoByIdList(@RequestParam Map<String,Object> map){
    	return reportService.findetTipoffMemoByIdList(map);
    }
    /**
     * 详情流转统计
     */
    @RequestMapping(value="/findetTipoffMemoByIdCount",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<EtTipoffMemo> findetTipoffMemoByIdCount(@RequestParam Map<String,Object> map){
    	return reportService.findetTipoffMemoByIdCount(map);
    }
    /**
     * 举报编辑
     */
    @RequestMapping(value="/update",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject update(HttpSession httpSession, HttpServletRequest request,EtTipoff etTipoff){
        JSONObject result =new JSONObject();
        try {
            reportService.update(etTipoff);
            result.put("flag",true);
            result.put("msg","编辑成功！");
            logger.info("举报编辑成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","编辑失败！原因："+e);
            logger.info("举报编辑失败！原因："+e);
            e.printStackTrace();
        }
        return result;
    }
    
    
    /**
     * 举报列表
     *  /report/etTipoffList
     */
    @RequestMapping(value="/etTipoffList",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public PageHelpVO etTipoffList(@RequestParam Map<String,Object> map){
        List<EtTipoff> etTipoffs = reportService.etTipoffList(map);
        PageHelpVO pageHelpVO = new PageHelpVO<EtTipoff>( etTipoffs);
        return pageHelpVO;
    }
    /**
     * 举报列表总数统计
     */
    @RequestMapping(value="/etTipoffListCount",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<EtTipoff> etTipoffListCount(@RequestParam Map<String,Object> map){
    	return reportService.etTipoffListCount(map);
    }
    
    
    /**
     * 举报功能（按钮功能）
     */
    @RequestMapping(value="/etTipoffUpdate",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public int etTipoffUpdate(@RequestParam Map<String,Object> map){
    	return reportService.etTipoffUpdate(map);
    }
    
    
    /**
     * 案件详情
     */
    @RequestMapping(value="/findCaseByIdInfo",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<ResponseVO> findCaseByIdInfo(@RequestParam Map<String,Object> map){
    	return  reportService.findCaseByIdInfo(map);
    }
    /**
     * 案件详情统计
     */
    @RequestMapping(value="/findCaseByIdInfoCount",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<ResponseVO> findCaseByIdInfoCount(@RequestParam Map<String,Object> map){
    	return reportService.findCaseByIdInfoCount(map);
    }
    
    /**
     * 警情详情
     */
    @RequestMapping(value="/findJingQingInfo",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<ResponseVO> findJingQingInfo(@RequestParam Map<String,Object> map){
    	return  reportService.findJingQingInfo(map);
    }
    /**
     * 警情详情统计
     */
    @RequestMapping(value="/findJingQingInfoCount",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<ResponseVO> findJingQingInfoCount(@RequestParam Map<String,Object> map){
    	return reportService.findJingQingInfoCount(map);
    }
    
}
