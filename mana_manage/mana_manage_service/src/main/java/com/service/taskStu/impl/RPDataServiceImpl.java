package com.service.taskStu.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.taskStu.RelationalPeople;
import com.mapper.taskStu.RelationalPeopleMapper;
import com.service.taskStu.RPDataService;

@Service("rPDataService")
@Transactional
public class RPDataServiceImpl implements RPDataService {

	@Resource
	private RelationalPeopleMapper relationalPeopleMapper;

	@Override
	public int insertExcelRPData(List<Map<String, String>> listmap) {
		return relationalPeopleMapper.insertExcelRPData(listmap);
	}

	@Override
	public List<RelationalPeople> selectByGangId(HashMap<String, Object> map) {
		return relationalPeopleMapper.selectByGangId(map);
	}

	@Override
	public long countSelectByGangId(HashMap<String, Object> map) {
		return relationalPeopleMapper.countSelectByGangId(map);
	}

	@Override
	public List<RelationalPeople> selectBySuspectId(HashMap<String, Object> map) {
		return relationalPeopleMapper.selectBySuspectId(map);
	}

	@Override
	public long countSelectBySuspectId(HashMap<String, Object> map) {
		return relationalPeopleMapper.countSelectBySuspectId(map);
	}

}
