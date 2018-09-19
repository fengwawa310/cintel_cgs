package com.service.infor.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.consts.Const;
import com.common.utils.IDGenerator;
import com.common.utils.TimeUtil;
import com.entity.alarm.EtAlarm;
import com.entity.infor.EtTipoff;
import com.entity.infor.EtTipoffLog;
import com.entity.infor.EtTipoffMemo;
import com.entity.infor.ResponseVO;
import com.entity.message.MessageList;
import com.mapper.communal.MessageListMapper;
import com.mapper.infor.EtTipoffLogMapper;
import com.mapper.infor.EtTipoffMapper;
import com.mapper.infor.EtTipoffMemoMapper;
import com.service.infor.ReportService;

/**
 * 举报
 * Created by weipc on 2018/2/28.
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Resource
    private EtTipoffMapper etTipoffMapper;
    
    @Resource
    private EtTipoffLogMapper etTipoffLogMapper;
    
    @Resource
    private EtTipoffMemoMapper etTipoffMemoMapper;
    
    @Resource
    private MessageListMapper messageListMapper;
    

    /**
     * 举报添加
     */
    @Override
    public void insert(EtTipoff etTipoff) {
        etTipoffMapper.insert(etTipoff);
    }

    /**
     * 查询单个举报信息
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> findReportById(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("etTipoff",etTipoffMapper.findReportById(map));
        return resultMap;
    }

    /**
     * 举报编辑
     */
    @Override
    public void update(EtTipoff etTipoff) {
        etTipoffMapper.update(etTipoff);
    }

	@Override
	public List<EtTipoff> etTipoffList(Map<String, Object> map) {
		return etTipoffMapper.etTipoffList(map);
	}
	
	@Override
	public List<EtTipoff> etTipoffListCount(Map<String, Object> map) {
		return etTipoffMapper.etTipoffListCount(map);
	}

	@Override
	public int etTipoffUpdate(Map<String, Object> map) {
		EtTipoffLog etTipoffLog= new EtTipoffLog();
		EtTipoffMemo etTipoffMemo= new EtTipoffMemo();
		
		etTipoffLog.setId(IDGenerator.getorderNo());
		etTipoffLog.setOperateType(map.get("operationSign").toString());
		etTipoffLog.setOperator(map.get("userId").toString());
		etTipoffLog.setTipoffId(map.get("tipoffId").toString());
		
		etTipoffMemo.setId(IDGenerator.getorderNo());
		etTipoffMemo.setTipoffId(map.get("tipoffId").toString());
		etTipoffMemo.setOperateType(map.get("operationSign").toString());
		etTipoffMemo.setOperator(map.get("userId").toString());
		etTipoffMemo.setMemoContent(map.get("remark").toString());
		
		Map<String,String> messageMap = new HashMap<>();
		//插入et_topoff_log表
		etTipoffLogMapper.insert(etTipoffLog);   ///update(map);
		//插入et_topoff_memo表
		etTipoffMemoMapper.insert(etTipoffMemo);
		String operationSign = map.get("operationSign").toString();
		
		
		messageMap.put("tipoffId", map.get("tipoffId").toString());
		messageMap.put("sysUserId", map.get("userId").toString());
		
		 //操作标志：1:新增举报 2:审核举报 3:下发举报 4:签收举报 5:研判举报 6:查结反馈 
		if ("2".equals(operationSign)) {
			messageMap.put("message", Const.XIAOXI_JUBAO_SHENHE);
		}
		if ("3".equals(operationSign)) {
			messageMap.put("message", Const.XIAOXI_JUBAO_XIAFA);
		}
		if ("4".equals(operationSign)) {
			messageMap.put("message", Const.XIAOXI_JUBAO_QIANSHOU);
		}
		if ("5".equals(operationSign)) {
			messageMap.put("message", Const.XIAOXI_JUBAO_YANPAN);
		}
		if ("6".equals(operationSign)) {
			messageMap.put("message", Const.XIAOXI_JUBAO_CHAJIE);
		}
		findBeingReport(messageMap);
		return etTipoffMapper.etTipoffUpdate(map);
	}

	  /**
     * 预警
     * @return 
     */
	@Override
	public int findBeingReport(Map<String, String> map) {
		if ("3106".equals(map.get("message"))) {
			Integer i = etTipoffMapper.findBeingReport(map);
			if (i != 0) {
				String createTimeStr = TimeUtil.formatDateToStr(new Date(), null);
				MessageList messageList = new MessageList();
				messageList.setId(IDGenerator.getorderNo());
				messageList.setTitle("有举报信息产生,编号<" + map.get("tipoffId") + ">,请进行处理");
				messageList.setReceiveUnitCode(null);
				messageList.setReceiveUnitName(null);
				messageList.setReceiverCode(map.get("sysUserId"));
				messageList.setRelationNo(map.get("tipoffId"));
				messageList.setRelationClass(map.get("message"));
				messageList.setIsSend(0);
				messageList.setIsRead(0);
				messageList.setCreatTime(createTimeStr);
				int add = messageListMapper.add(messageList);
				return add;
			} else {
				return 0;
			}
		} else {
			String createTimeStr = TimeUtil.formatDateToStr(new Date(), null);
			MessageList messageList = new MessageList();
			messageList.setId(IDGenerator.getorderNo());
			messageList.setTitle("有举报信息产生,编号<" + map.get("tipoffId") + ">,请进行处理");
			messageList.setReceiveUnitCode(null);
			messageList.setReceiveUnitName(null);
			messageList.setReceiverCode(map.get("sysUserId"));
			messageList.setRelationNo(map.get("tipoffId"));
			messageList.setRelationClass(map.get("message"));
			messageList.setIsSend(0);
			messageList.setIsRead(0);
			messageList.setCreatTime(createTimeStr);
			int add = messageListMapper.add(messageList);
			return add;
		}
	}
	/**
     * 查询单个举报信息
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> findetTipoffMemoById(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("etTipoff",etTipoffMapper.findetTipoffMemoById(map));
        return resultMap;
    }

	@Override
	public List<EtTipoffMemo> findetTipoffMemoByIdList(Map<String, Object> map) {
		return etTipoffMemoMapper.findetTipoffMemoByIdList(map);
	}

	@Override
	public List<EtTipoffMemo> findetTipoffMemoByIdCount(Map<String, Object> map) {
		return etTipoffMemoMapper.findetTipoffMemoByIdCount(map);
	}

	@Override
	public List<ResponseVO> findCaseByIdInfo(Map<String, Object> map) {
		return etTipoffMemoMapper.findCaseByIdInfo(map);
	}

	@Override
	public List<ResponseVO> findCaseByIdInfoCount(Map<String, Object> map) {
		return etTipoffMemoMapper.findCaseByIdInfoCount(map); 
	}
 
	@Override
	public List<ResponseVO> findJingQingInfo(Map<String, Object> map) {
		return etTipoffMemoMapper.findJingQingInfo(map);
	}

	@Override
	public List<ResponseVO> findJingQingInfoCount(Map<String, Object> map) {
		return etTipoffMemoMapper.findJingQingInfoCount(map); 
	}
 

}
