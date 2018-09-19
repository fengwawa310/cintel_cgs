package com.controller.integral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.services.api.InterfaceCallBack;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.GsonUtils;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.common.utils.httpclient.HttpClientUtil;
import com.common.utils.httpclient.JsonUtil;
import com.controller.BaseHandler;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.entity.integral.StatisticsEntity;
import com.entity.sys.SysUser;
import com.util.SysUserHelp;


@Controller
@RequestMapping("/integral")
public class IntegralHandler extends BaseHandler {
	
	/**
     * 每日通报
     */
    @RequestMapping(value="/dailyBriefing",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String dailyBriefing(@RequestParam String createTime,HttpServletResponse response){
    	String result = "";
    	String serivcePath = APIClientRequest.getSevicesFullPath(Const.SUB_SYSTEM_CODE_INTEGRAL,"integral/dailyBriefing?createTime="+createTime);
    	if (serivcePath != null) {
    		result = HttpClientUtil.doGet(serivcePath, null);
    	}
    	return result;
    }
    
    /**
	 * 每日统计
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/dailyStatistics",produces = "application/json; charset=utf-8")
	@ResponseBody
    public HashMap<String,Object> dailyStatistics(@RequestParam(value = "search[value]") String search,HttpServletResponse response){
		HashMap<String,Object> resultMap =  new HashMap<String,Object>();

		//查询条件
		if(StringUtils.isBlank(search)){
    		resultMap.put("data",new ArrayList<StatisticsEntity>());
    		return resultMap;
    	}
		
		JSONObject searchObj = JSONObject.fromObject(search);
		String creatTime = String.valueOf(searchObj.get("creatTime"));
    	
    	String serivcePath = APIClientRequest.getSevicesFullPath(Const.SUB_SYSTEM_CODE_INTEGRAL,"integral/dailyStatistics?createTime="+creatTime);
    	String result = HttpClientUtil.doGet(serivcePath, null);
    	
    	List<StatisticsEntity> resultList = JsonUtil.jsonToList(result, StatisticsEntity.class);
    	resultMap.put("data", resultList);
		return resultMap;
	}
	
	
	@RequestMapping(value="/findSuspectIntegral",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DatatablesResponse<EtSuspectIntegral> findSuspectIntegral(HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "search[value]") String search,
			@RequestParam(required = true, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "10") Integer length) {
		EtSuspectIntegral jfEntity = GsonUtils.toBean(search, EtSuspectIntegral.class);

		PageVO pageVO = new PageVO(start, length);
		String pageVOStr = GsonUtils.getGson().toJson(pageVO);
		JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
		if (jfEntity == null)
			jfEntity = new EtSuspectIntegral();
		/*
		 * 分权分域-同级单位间不共享；
		 * 数据对上级单位默认授权；
		 */
		SysUser user = (SysUser) request.getAttribute("user");
		jfEntity.setDeceSigns(SysUserHelp.getUserDeceSigns(user, Const.DECE_SIGNS_TYPE_UNIT));
		String tempStr = GsonUtils.getGson().toJson(jfEntity);
		pageVOObj.putAll(JSONObject.fromObject(tempStr));
		
		PageHelpVO<EtSuspectIntegral> pageHelpVO = null;

		try {
			//同步调用
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_INTEGRAL, "integral/findSuspectIntegral", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);

			//异步调用
//			RequestCallBack requestCallBack = new RequestCallBack();
//			APIClientRequest apiClientRequest = new APIClientRequest();
//			apiClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_INTEGRAL, "integral/findSuspectIntegral", pageVOObj,requestCallBack);
//			pageHelpVO = requestCallBack.getPageHelpVO();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
		DatatablesResponse<EtSuspectIntegral> resp = DatatablesResponse.build(dataSet);
		return resp;
	}
	
	private class RequestCallBack implements InterfaceCallBack{
		
		private PageHelpVO pageHelpVO;
		
		public PageHelpVO getPageHelpVO() {
			return pageHelpVO;
		}

		public void setPageHelpVO(PageHelpVO pageHelpVO) {
			this.pageHelpVO = pageHelpVO;
		}

		@Override
		public void OnResult(String jsonStr) {
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			this.pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		}
		
	}
	
	@RequestMapping(value="/findSuspectIntegralforIdCard",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String  findSuspectIntegralforIdCard(HttpSession httpSession, HttpServletRequest request,
			String idcardNum) {
		
		if(StringUtils.isBlank(idcardNum))
			return "";
		
		PageVO pageVO = new PageVO(0, 1);
		String pageVOStr = GsonUtils.getGson().toJson(pageVO);
		JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
		
		EtSuspectIntegral entity = new EtSuspectIntegral();
		entity.setIdcardNum(idcardNum);
		String tempStr = GsonUtils.getGson().toJson(entity);
		pageVOObj.putAll(JSONObject.fromObject(tempStr));
		
		return APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_INTEGRAL, "integral/findSuspectIntegral", pageVOObj);
	}
	
	@RequestMapping(value="/findUnitIntegralStatistics",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DatatablesResponse<EtUnitIntegral> findUnitIntegralStatistics(HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "search[value]") String search,
			@RequestParam(required = true, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "10") Integer length) {
		EtUnitIntegral jfEntity = GsonUtils.toBean(search, EtUnitIntegral.class);
		
		PageVO pageVO = new PageVO(start, length);
		String pageVOStr = GsonUtils.getGson().toJson(pageVO);
		JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
		if (jfEntity != null) {
			String tempStr = GsonUtils.getGson().toJson(jfEntity);
			pageVOObj.putAll(JSONObject.fromObject(tempStr));
		}
		PageHelpVO<EtSuspectIntegral> pageHelpVO = null;
		
		try {
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_INTEGRAL, "integral/findUnitIntegralStatistics", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
		DatatablesResponse<EtUnitIntegral> resp = DatatablesResponse.build(dataSet);
		return resp;
	}

	@RequestMapping(value="/findUnitIntegral",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DatatablesResponse<EtUnitIntegral> findUnitIntegral(HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "search[value]") String search,
			@RequestParam(required = true, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "10") Integer length) {
		EtUnitIntegral jfEntity = GsonUtils.toBean(search, EtUnitIntegral.class);
		
		PageVO pageVO = new PageVO(start, length);
		String pageVOStr = GsonUtils.getGson().toJson(pageVO);
		JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
		if (jfEntity == null)
			jfEntity = new EtUnitIntegral();
		
		SysUser user = (SysUser) request.getAttribute("user");
		//权限控制
		jfEntity.setUnitCode(SysUserHelp.getSysUserDicUnit(user));
		String tempStr = GsonUtils.getGson().toJson(jfEntity);
		pageVOObj.putAll(JSONObject.fromObject(tempStr));
		
		PageHelpVO<EtSuspectIntegral> pageHelpVO = null;
		
		try {
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_INTEGRAL, "integral/findUnitIntegral", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
		DatatablesResponse<EtUnitIntegral> resp = DatatablesResponse.build(dataSet);
		return resp;
	}
	
   /**
    * 单位积分编辑
    */
	@RequestMapping(value="/updateUnitIntegral",produces = "application/json; charset=utf-8")
    @ResponseBody
    public JSONObject updateUnitIntegral(String unitIntegral){
    	EtUnitIntegral etUnitIntegral = GsonUtils.toBean(unitIntegral, EtUnitIntegral.class);
        String etCaseStr = GsonUtils.getGson().toJson(etUnitIntegral);
        JSONObject jsonObj = JSONObject.fromObject(etCaseStr);
        String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_INTEGRAL, "integral/updateUnitIntegral", jsonObj);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }

}
