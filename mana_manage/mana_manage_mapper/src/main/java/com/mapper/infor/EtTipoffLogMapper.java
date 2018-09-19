package com.mapper.infor;

import com.entity.infor.EtTipoffLog;

public interface EtTipoffLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(EtTipoffLog record);

    int insertSelective(EtTipoffLog record);

    EtTipoffLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtTipoffLog record);

    int updateByPrimaryKey(EtTipoffLog record);
}