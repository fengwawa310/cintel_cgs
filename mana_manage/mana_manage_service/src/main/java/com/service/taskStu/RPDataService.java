package com.service.taskStu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.taskStu.RelationalPeople;

public interface RPDataService {
	
	List<RelationalPeople> selectByGangId(HashMap<String, Object> map);

	int insertExcelRPData(List<Map<String, String>> listmap);

	long countSelectByGangId(HashMap<String, Object> map);

	List<RelationalPeople> selectBySuspectId(HashMap<String, Object> map);

	long countSelectBySuspectId(HashMap<String, Object> map);

}
