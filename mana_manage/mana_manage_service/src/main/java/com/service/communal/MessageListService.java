package com.service.communal;

import java.util.Map;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.message.MessageList;
import com.vo.infor.IntelligenceListRequetParam;


public interface MessageListService {
	PageHelpVO queryByList(PageVO pageVO, MessageList messageList, String sysUserDicUnit, String level);

	/**
	 * 添加系统通知
	 * @param messageList
	 * @return
	 */
	int add(MessageList messageList);

	Integer queryCount(Map<String, String> map);

	Integer delete(String id);

}
