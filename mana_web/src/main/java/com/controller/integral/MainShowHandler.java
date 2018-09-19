package com.controller.integral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.SortList;
import com.common.utils.httpclient.HttpClientUtil;
import com.common.utils.httpclient.JsonUtil;
import com.entity.integral.StatisticsEntity;


@Controller
@RequestMapping("/mainShow")
public class MainShowHandler {
	
	/**
	 * 首页统计
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/statistics",produces = "application/json; charset=utf-8")
	@ResponseBody
    public HashMap<String,Object> statistics(HttpServletResponse response){
		HashMap<String,Object> resultMap =  new HashMap<String,Object>();

    	String serivcePath = APIClientRequest.getSevicesFullPath(Const.SUB_SYSTEM_CODE_INTEGRAL,"integral/mainStatistics");
    	String result = HttpClientUtil.doGet(serivcePath, null);
    	
    	List<StatisticsEntity> tempList = JsonUtil.jsonToList(result, StatisticsEntity.class);
    	List<StatisticsEntity> resultList = new ArrayList<StatisticsEntity>();
    	List<String> suspectLegendList = new ArrayList<>();
    	List<String> caseLegendList = new ArrayList<>();
    	List<StatisticsEntity> caseTop10List = new ArrayList<>();
    	List<Integer> caseNumList = new ArrayList<>();
    	StatisticsEntity totalEntity = new StatisticsEntity();
    	if(tempList == null){
    		
    	}else{
    		for(StatisticsEntity entity:tempList){
    			//案件柱状图
    			if(caseTop10List.size() <= 10){
    				caseTop10List.add(entity);
    			}
    			
    			//计算合计
        		totalEntity.setCaseNum(Integer.valueOf(totalEntity.getCaseNum().intValue()+entity.getCaseNum().intValue()));
        		totalEntity.setSuspectNum(Integer.valueOf(totalEntity.getSuspectNum().intValue()+entity.getSuspectNum().intValue()));
        		totalEntity.setInforNum(Integer.valueOf(totalEntity.getInforNum().intValue()+entity.getInforNum().intValue()));
        		totalEntity.setAlarmNum(Integer.valueOf(totalEntity.getAlarmNum().intValue()+entity.getAlarmNum().intValue()));
        		
        		//人员饼图
        		if(entity.getSuspectNum() > 0){
        			suspectLegendList.add(entity.getZoneName());
        			resultList.add(entity);
        		}
    		}
    		
    		SortList<StatisticsEntity> sortList = new SortList<StatisticsEntity>();  
    		sortList.sort(caseTop10List, "getCaseNum", "asc"); 
    		for(StatisticsEntity one : caseTop10List){
				caseLegendList.add(one.getZoneName());
				caseNumList.add(one.getCaseNum());
			}
    	}
    	
    	resultMap.put("totalAlarmNum", totalEntity.getAlarmNum());
    	resultMap.put("totalCaseNum", totalEntity.getCaseNum());
    	resultMap.put("totalInforNum", totalEntity.getInforNum());
    	resultMap.put("totalSuspectNum", totalEntity.getSuspectNum());

    	resultMap.put("seriesData", resultList);
    	resultMap.put("suspectLegendData", suspectLegendList);
    	resultMap.put("caseLegendData", caseLegendList);
    	resultMap.put("caseNumData", caseNumList);
    	
		return resultMap;
	}
}
