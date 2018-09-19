package com.mapper.communal;

import java.util.List;
import java.util.Map;

import com.entity.message.MessageList;
import com.vo.infor.IntelligenceListRequetParam;
import com.vo.infor.IntelligenceListResponseVO;

public interface MessageListMapper {
	List<IntelligenceListResponseVO> queryByList(Map<String, String> map);

	int add(MessageList messageList);

	Integer count(Map<String, String> map);

	Integer deletebyId(String relationNo);

}
