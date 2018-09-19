package com.mapper.taskStu;

import com.vo.taskStu.EtAnalyse;

import java.util.List;

public interface EtAnalyseMapper {
    int deleteByPrimaryKey(String id);

    int insert(EtAnalyse record);

    int insertSelective(EtAnalyse record);

    EtAnalyse selectByPrimaryKey(String id);

    //根据场景值以及用户id获取列表，按更新时间降序排列
    List<EtAnalyse> selectByType(EtAnalyse record);

    int updateByPrimaryKeySelective(EtAnalyse record);

    int updateByPrimaryKeyWithBLOBs(EtAnalyse record);

    int updateByPrimaryKey(EtAnalyse record);
}