package com.service.taskStu;

import java.util.HashMap;
import java.util.List;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.taskStu.EtJudgeflow;
import net.sf.json.JSONObject;

public interface EtJudgeflowService {
	
	int deleteByPrimaryKey(String id);

    int insert(EtJudgeflow record);

    int insertSelective(EtJudgeflow record);

    EtJudgeflow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtJudgeflow record);

    int updateByPrimaryKey(EtJudgeflow record);
    
    List<EtJudgeflow> selectEJFList(HashMap<String,Object> map);

	List<EtJudgeflow> countSelectEJFList(HashMap<String, Object> map);

    PageHelpVO selectJudgeDetailLog(PageVO pageVO, String id);

	List<EtJudgeflow> selectEJFListFastEn(HashMap<String, Object> map);
	
	List<EtJudgeflow> countSelectEJFListFastEn(HashMap<String, Object> map);

    /*案件串并后生成事件并将结果保存 */
    String insertCaseSeries(JSONObject json, HashMap<String,Object> paramMap) throws Exception;
    /*查询案件串并结果*/
    PageHelpVO selectSeriesResult(PageVO pageVO,String eventId);
    /*查询案件串并事件*/
    PageHelpVO selectSeriesEvent(PageVO pageVO, String caseNo);
}
