package com.mapper.infor;

import java.util.List;
import java.util.Map;

import com.entity.infor.EtTipoff;
import com.entity.infor.EtTipoffMemo;
import com.entity.message.MessageList;

public interface EtTipoffMapper {

    /**
     * 举报添加
     * @param etTipoff
     * @return
     */
    int insert(EtTipoff etTipoff);

    /**
     * 查询单个举报信息
     * @return
     */
    EtTipoff findReportById(Map<String, Object> map);

    /**
     * 举报编辑
     * @param etTipoff
     * @return
     */
    int update(EtTipoff etTipoff);

    int delete(String tipoffId);

    EtTipoff select(String tipoffId);

	List<EtTipoff> etTipoffList(Map<String, Object> map);
    
    /**
     * 查询被举报人信息
     * @return
     */
	Integer findBeingReport(Map<String, String> map);
	int etTipoffUpdate(Map<String, Object> map);

	List<EtTipoff> etTipoffListCount(Map<String, Object> map);

	EtTipoff findetTipoffMemoById(Map<String, Object> map);
}