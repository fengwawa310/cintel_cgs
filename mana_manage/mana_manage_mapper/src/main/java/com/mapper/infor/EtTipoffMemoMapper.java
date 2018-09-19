package com.mapper.infor;

import java.util.List;
import java.util.Map;

import com.entity.alarm.EtAlarm;
import com.entity.infor.EtTipoffMemo;
import com.entity.infor.ResponseVO;

public interface EtTipoffMemoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EtTipoffMemo record);

    int insertSelective(EtTipoffMemo record);

    EtTipoffMemo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtTipoffMemo record);

    int updateByPrimaryKeyWithBLOBs(EtTipoffMemo record);

    int updateByPrimaryKey(EtTipoffMemo record);

	List<EtTipoffMemo> findetTipoffMemoByIdList(Map<String, Object> map);

	List<EtTipoffMemo> findetTipoffMemoByIdCount(Map<String, Object> map);

	List<ResponseVO> findCaseByIdInfo(Map<String, Object> map);

	List<ResponseVO> findCaseByIdInfoCount(Map<String, Object> map);

	List<ResponseVO> findJingQingInfo(Map<String, Object> map);

	List<ResponseVO> findJingQingInfoCount(Map<String, Object> map);
}