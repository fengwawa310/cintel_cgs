package com.service.infor;

import java.util.List;
import java.util.Map;

import com.entity.alarm.EtAlarm;
import com.entity.caseInfo.EtCase;
import com.entity.infor.EtTipoff;
import com.entity.infor.EtTipoffMemo;
import com.entity.infor.ResponseVO;
import com.entity.message.MessageList;

/**
 * 举报
 * Created by weipc on 2018/2/28.
 */
public interface ReportService {
    /**
     * 举报添加
     */
    void insert(EtTipoff etTipoff);

    /**
     * 查询单个举报信息
     * @param map
     * @return
     */
    Map<String,Object> findReportById(Map<String, Object> map);

    /**
     * 举报编辑
     */
    void update(EtTipoff etTipoff);

    /**
     * 预警前的查询
     * @return 
     */
	int findBeingReport(Map<String, String> map);
	List<EtTipoff> etTipoffList(Map<String, Object> map);


	int etTipoffUpdate(Map<String, Object> map);
	List<EtTipoff> etTipoffListCount(Map<String, Object> map);

	Map<String, Object> findetTipoffMemoById(Map<String, Object> map);

	List<EtTipoffMemo> findetTipoffMemoByIdList(Map<String, Object> map);

	List<EtTipoffMemo> findetTipoffMemoByIdCount(Map<String, Object> map);

	List<ResponseVO> findCaseByIdInfo(Map<String, Object> map);

	List<ResponseVO> findCaseByIdInfoCount(Map<String, Object> map);
	
	List<ResponseVO> findJingQingInfo(Map<String, Object> map);

	List<ResponseVO> findJingQingInfoCount(Map<String, Object> map);

}
