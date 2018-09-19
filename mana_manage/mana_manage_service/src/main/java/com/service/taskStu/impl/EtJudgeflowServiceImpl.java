package com.service.taskStu.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.common.enums.EnumTypeVO;
import com.common.enums.EtJudgeEnumType;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.caseInfo.CaseSeries;
import com.entity.taskStu.ApCaseSeriesEvent;
import com.entity.taskStu.ApCaseSeriesResult;
import com.entity.taskStu.ApJudgelog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.taskStu.ApCaseSeriesEventMapper;
import com.mapper.taskStu.ApCaseSeriesResultMapper;
import com.mapper.taskStu.ApJudgelogMapper;
import com.vo.sys.SysNoticeVO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.taskStu.EtJudgeflow;
import com.mapper.taskStu.EtJudgeflowMapper;
import com.service.taskStu.EtJudgeflowService;

@Transactional
@Service("etJudgeflowService")
public class EtJudgeflowServiceImpl implements EtJudgeflowService {

	@Resource
	private EtJudgeflowMapper etJudgeflowMapper;

	@Resource
	private ApJudgelogMapper apJudgelogMapper;

	//案件串并结果
	@Resource
	private ApCaseSeriesEventMapper apCaseSeriesEventMapper;

	//案件串并事件
	@Resource
	private ApCaseSeriesResultMapper apCaseSeriesResultMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(EtJudgeflow record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(EtJudgeflow record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EtJudgeflow selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return etJudgeflowMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EtJudgeflow record) {
		// TODO Auto-generated method stub
		return etJudgeflowMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EtJudgeflow record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EtJudgeflow> selectEJFList(HashMap<String, Object> map) {
		return etJudgeflowMapper.selectEJFList(map);
	}
	
	@Override
	public List<EtJudgeflow> countSelectEJFList(HashMap<String, Object> map) {
		return etJudgeflowMapper.countSelectEJFList(map);
	}

	@Override
	public PageHelpVO selectJudgeDetailLog(PageVO pageVO, String id) {
		Integer start = pageVO.getStart();
		Integer length = pageVO.getLength();
		PageHelper.startPage(start, length);
		List<ApJudgelog> list = apJudgelogMapper.selectJudgeDetailLog(id);
//		List<SysNoticeVO> lists = new ArrayList<>();
		for (ApJudgelog apJudgelog : list) {
			EtJudgeEnumType valueof = EtJudgeEnumType.valueof(apJudgelog.getOperType());
			apJudgelog.setOperTypeEnum(new EnumTypeVO(valueof.getName(),String.valueOf(valueof.getValue())));
		}
		PageInfo<ApJudgelog> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		PageHelpVO pageHelpVO = new PageHelpVO<ApJudgelog>(total, list);
		return pageHelpVO;
	}

	@Override
	public List<EtJudgeflow> selectEJFListFastEn(HashMap<String, Object> map) {
		return etJudgeflowMapper.selectEJFListFastEn(map);
	}

	@Override
	public List<EtJudgeflow> countSelectEJFListFastEn(HashMap<String, Object> map) {
		return etJudgeflowMapper.countSelectEJFListFastEn(map);
	}

	@Override
	public String insertCaseSeries(JSONObject json, HashMap<String,Object> paramMap) {

		ApCaseSeriesEvent event=new ApCaseSeriesEvent();
		//事件ID
		String id = IDGenerator.getorderNo();
		event.setId(id);
		event.setCaseNo(paramMap.get("ajbh").toString());
		event.setSimilar(paramMap.get("similar").toString());
		event.setOperatorName(paramMap.get("name").toString());
		event.setOperatorCode(paramMap.get("code").toString());
		//保存案件串并事件
		apCaseSeriesEventMapper.insert(event);
		List<ApCaseSeriesResult> resultList= new ArrayList<>();
		List<Map<String, Object>> RSP_SET = (List<Map<String, Object>>) JSONArray.parse(json.get("RSP_SET").toString());
		for (Map<String, Object> map:RSP_SET) {
			ApCaseSeriesResult result=new  ApCaseSeriesResult();
			result.setId(IDGenerator.getorderNo());
			result.setCaseEventId(id);
			result.setAjbh(map.get("AJBH").toString());
			result.setSimilar(map.get("SIMILAR").toString());
			resultList.add(result);
		}
		//保存案件串并结果
		apCaseSeriesResultMapper.insert(resultList);
		return id;
	}

	@Override
	public PageHelpVO selectSeriesResult(PageVO pageVO,String eventId) {
		Integer start = pageVO.getStart();
		Integer length = pageVO.getLength();
		PageHelper.startPage(start, length);
		List<ApCaseSeriesResult> list = apCaseSeriesResultMapper.selectSeriesResult(eventId);
		PageInfo<ApCaseSeriesResult> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		PageHelpVO pageHelpVO = new PageHelpVO<ApCaseSeriesResult>(total, list);
		return pageHelpVO;
	}

	@Override
	public PageHelpVO selectSeriesEvent(PageVO pageVO, String caseNo) {
		Integer start = pageVO.getStart();
		Integer length = pageVO.getLength();
		PageHelper.startPage(start, length);
		List<ApCaseSeriesEvent> list = apCaseSeriesEventMapper.selectSeriesEvent(caseNo);
		PageInfo<ApCaseSeriesEvent> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		PageHelpVO pageHelpVO = new PageHelpVO<ApCaseSeriesEvent>(total, list);
		return pageHelpVO;
	}

}
