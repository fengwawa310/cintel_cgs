package com.mapper.utils;

import java.util.List;

import com.entity.suspect.EtSuspect;

public interface PublicEtSuspectMapper {

	EtSuspect selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(EtSuspect record);

	int updateByPrimaryKey(EtSuspect record);

	List<EtSuspect> findAll(EtSuspect etSuspect);

    int searchIdCard(String idcardnum);

	EtSuspect selectByNo(String suspectNo);
	//获取多有重点人员
	List<EtSuspect> selectByNoAll();
}