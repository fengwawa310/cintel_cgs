package com.service.suspect.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.suspect.OperPermission;
import com.mapper.suspect.OperPermissionMapper;
import com.service.suspect.OperPermissionService;

@Service
@Transactional
public class OperPermissionServiceImpl implements OperPermissionService {
	
	@Resource
	private OperPermissionMapper operPermissionMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return operPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OperPermission record) {
		return operPermissionMapper.insert(record);
	}

	@Override
	public int insertSelective(OperPermission record) {
		return operPermissionMapper.insertSelective(record);
	}

	@Override
	public OperPermission selectByPrimaryKey(String id) {
		return operPermissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OperPermission record) {
		return operPermissionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OperPermission record) {
		return operPermissionMapper.updateByPrimaryKey(record);
	}

	@Override
	public OperPermission selectOperPermissionBySuspectNoAndUserNo(String suspectNo, String userNo) {
		return operPermissionMapper.selectOperPermissionBySuspectNoAndUserNo(suspectNo,userNo);
	}

	@Override
	public void deleteBySuspectNoAndUserNo(String suspectNo, String userNo) {
		operPermissionMapper.deleteBySuspectNoAndUserNo(suspectNo,userNo);
	}

	@Override
	public void updateBySuspectNoAndUserNoSelective(OperPermission operPermission2) {
		operPermissionMapper.updateBySuspectNoAndUserNoSelective(operPermission2);
	}

}
