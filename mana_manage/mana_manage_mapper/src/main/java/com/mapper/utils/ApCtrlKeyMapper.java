package com.mapper.utils;

import com.entity.suspect.ApCtrlKey;

public interface ApCtrlKeyMapper {
    int deleteByPrimaryKey(String ctrlId);

    int deleteByIDCardNum(String idCardNum);

    int insert(ApCtrlKey record);

    int insertSelective(ApCtrlKey record);

    ApCtrlKey selectByPrimaryKey(String ctrlId);

    int updateByPrimaryKeySelective(ApCtrlKey record);

    int updateByPrimaryKey(ApCtrlKey record);
}