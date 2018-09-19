package com.mapper.utils;



import java.util.List;

import com.entity.ticket.EtTicket;

public interface EtTicketMapper {
    /*批量添加话单信息*/
    void insert(List<EtTicket> list);

    int deleteByPrimaryKey(String id);

    int insertSelective(EtTicket record);

    EtTicket selectByPrimaryKey(String id);
    
	List<EtTicket> selectByCallingNumber(EtTicket etTicket);

    int updateByPrimaryKeySelective(EtTicket record);

    int updateByPrimaryKey(EtTicket record);
}