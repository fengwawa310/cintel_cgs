package com.service.ticket.impl;

import com.entity.ticket.EtCommunication;
import com.entity.ticket.EtTicket;
import com.mapper.ticket.EtCommunicationMapper;
import com.mapper.utils.EtTicketMapper;
import com.service.ticket.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 话单分析
 * Created by weipc on 2018/1/31.
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    //通讯录
    @Resource
    private EtCommunicationMapper etCommunicationMapper;
   //话单
    @Resource
    private EtTicketMapper etTicketMapper;

    /**
     * 批量添加通讯录信息
     * @param list
     */
    @Override
    public void insertCommunication(List<EtCommunication> list) {
        etCommunicationMapper.insert(list);
    }

    /**
     * 批量添加话单信息
     * @param list
     */
    @Override
    public void importTicket(List<EtTicket> list) {
        etTicketMapper.insert(list);
    }
}
