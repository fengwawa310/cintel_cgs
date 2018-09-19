package com.mapper.ticket;

import com.entity.ticket.EtCommunication;

import java.util.List;

public interface EtCommunicationMapper {
    /*批量添加通讯录信息*/
    void insert(List<EtCommunication> list);

    int deleteByPrimaryKey(String id);

    int insertSelective(EtCommunication record);

    EtCommunication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtCommunication record);

    int updateByPrimaryKey(EtCommunication record);


}