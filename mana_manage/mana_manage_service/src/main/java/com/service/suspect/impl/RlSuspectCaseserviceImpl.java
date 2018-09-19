package com.service.suspect.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.suspect.RlSuspectCase;
import com.mapper.utils.RlSuspectCaseMapper;
import com.service.suspect.RlSuspectCaseService;

@Service
@Transactional
public class RlSuspectCaseserviceImpl implements RlSuspectCaseService {
	
	
	 @Resource
    protected RlSuspectCaseMapper rlSuspectCaseMapper;

	@Override
	public int update(RlSuspectCase rlSuspectCase) {
		
		int updateByPrimaryKeySelective = rlSuspectCaseMapper.updateByPrimaryKeySelectiveEL(rlSuspectCase);
		return updateByPrimaryKeySelective;
	}

	@Override
	public int insert(RlSuspectCase rlSuspectCase) {
		int insertSelective = rlSuspectCaseMapper.insert(rlSuspectCase);
		return insertSelective;
	}
	 
	 
	 
	 

}
