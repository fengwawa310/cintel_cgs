package com.mapper.integral;

import java.util.List;

import com.entity.integral.StatisticsEntity;

public interface IntegralMapper {
	
	List<StatisticsEntity> caseZoneStatistics(String createTime);
	
	List<StatisticsEntity> caseClassStatistics(String createTime);
	
	List<StatisticsEntity> dailyStatistics(String createTime);
}
