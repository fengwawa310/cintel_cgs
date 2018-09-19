package com.mapper.utils;

import java.util.List;

import com.entity.caseInfo.EtCase;
import com.entity.suspect.EtSuspect;
import com.entity.suspect.RlSuspectCase;

public interface RlSuspectCaseMapper {
    /*//人-案对应关系查询*/
    List<EtCase> findCaseByIDCardNum(EtSuspect entity);
    /*人-案对应关系建立*/
    int insert(RlSuspectCase record);

    int deleteByPrimaryKey(String id);




    RlSuspectCase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RlSuspectCase record);
    int updateByPrimaryKeySelectiveEL(RlSuspectCase record);

    int updateByPrimaryKey(RlSuspectCase record);
	void deleteByCaseId(String id);

}