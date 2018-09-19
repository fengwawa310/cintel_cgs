package com.service.ticket;

import com.entity.ticket.EtCommunication;
import com.entity.ticket.EtTicket;

import java.util.List;

/**
 * Created by weipc on 2018/1/31.
 */
public interface TicketService {
    /*批量添加通讯录信息*/
    void insertCommunication(List<EtCommunication> list);
    /*批量添加话单信息*/
    void importTicket(List<EtTicket> list);
}
