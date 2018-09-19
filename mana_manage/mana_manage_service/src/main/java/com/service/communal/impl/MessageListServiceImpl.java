package com.service.communal.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.consts.Const;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.message.MessageList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.communal.MessageListMapper;
import com.service.communal.MessageListService;
import com.vo.infor.IntelligenceListRequetParam;
import com.vo.infor.IntelligenceListResponseVO;


@Service
@Transactional
public class MessageListServiceImpl implements MessageListService{

	@Autowired
	private MessageListMapper  messageListMapper;
	
	@Override
	public PageHelpVO queryByList(PageVO pageVO, MessageList messageList,String sysUserDicUnit,String level) {
		Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        Map<String,String> map = new HashMap<>();
        /*
    	 * 分权分域-同级单位间不共享；
    	 * 数据对上级单位默认授权；
    	 */
		map.put("deceSigns",sysUserDicUnit);
        List<IntelligenceListResponseVO> list = messageListMapper.queryByList(map);
        PageInfo<IntelligenceListResponseVO> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<IntelligenceListResponseVO>(total,list);
		return pageHelpVO;
	}

	@Override
	public int add(MessageList messageList) {
		return  messageListMapper.add(messageList);
	}

	@Override
	public Integer queryCount(Map<String, String> map) {
		return messageListMapper.count(map);
	}

	@Override
	public Integer delete(String relationNo) {
		return messageListMapper.deletebyId(relationNo);
	}

	 

}
