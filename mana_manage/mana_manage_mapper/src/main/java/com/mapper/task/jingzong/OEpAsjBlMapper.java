package com.mapper.task.jingzong;

import java.util.List;
import java.util.Map;

import com.entity.task.EpAsjBl;

public interface OEpAsjBlMapper {
	EpAsjBl selectByPrimaryKey(String systemid);

	public List<EpAsjBl> findBlData(Map<String, String> map);

	long findBlDataCount(Map<String, String> map);

}