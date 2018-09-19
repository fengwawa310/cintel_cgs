package com.mapper.taskStu;


import com.entity.xmlparse.EpClue;

import java.util.List;

public interface EpClueMapper {
    int deleteByPrimaryKey(String id);

    int insert(EpClue record);

    int insertSelective(EpClue record);

    EpClue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EpClue record);

    int updateByPrimaryKey(EpClue record);

    void insertEpClueList(List<EpClue> paramList);

    List<EpClue> findEpClueList();

}