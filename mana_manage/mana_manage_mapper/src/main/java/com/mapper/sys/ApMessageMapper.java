package com.mapper.sys;

import com.entity.proposal.ApMessage;

public interface ApMessageMapper {
    int deleteByPrimaryKey(String msgNo);

    int insert(ApMessage record);

    int insertSelective(ApMessage record);

    ApMessage selectByPrimaryKey(String msgNo);

    int updateByPrimaryKeySelective(ApMessage record);

    int updateByPrimaryKeyWithBLOBs(ApMessage record);
}