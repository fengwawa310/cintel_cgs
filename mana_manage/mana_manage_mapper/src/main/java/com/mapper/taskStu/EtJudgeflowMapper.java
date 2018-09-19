package com.mapper.taskStu;


import java.util.HashMap;
import java.util.List;

import com.entity.taskStu.EtJudgeflow;

public interface EtJudgeflowMapper {
    int deleteByPrimaryKey(String id);

    int insert(EtJudgeflow record);

    int insertSelective(EtJudgeflow record);

    EtJudgeflow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtJudgeflow record);

    int updateByPrimaryKey(EtJudgeflow record);

	List<EtJudgeflow> selectEJFList(HashMap<String, Object> map);

	List<EtJudgeflow> countSelectEJFList(HashMap<String, Object> map);

	List<EtJudgeflow> selectEJFListFastEn(HashMap<String, Object> map);

	List<EtJudgeflow> countSelectEJFListFastEn(HashMap<String, Object> map);
}