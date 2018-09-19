package com.service.integral;

import java.util.List;


import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.entity.integral.StatisticsEntity;

public interface IntegralService {

	/**
	 * 每日通报-案件按类型统计
	 * @param date
	 * @return
	 */
	List<StatisticsEntity> caseClassStatistics(String date);
	
	/**
	 * 每日通报-区域案件统计
	 * @param date
	 * @return
	 */
	List<StatisticsEntity> caseZoneStatistics(String date);
	
	/**
	 * 每日统计
	 * @param date
	 * @return
	 */
	List<StatisticsEntity> dailyStatistics(String date);

	/**
	 * 人员积分信息
	 * @param date
	 * @return
	 */
	PageHelpVO<EtSuspectIntegral> findSuspectIntegral(PageVO pageVO,EtSuspectIntegral si);

	/**
	 * 单位积分统计信息
	 * @param date
	 * @return
	 */
	PageHelpVO<EtUnitIntegral> findUnitIntegralStatistics(PageVO pageVO,EtUnitIntegral si);
	
	/**
	 * 单位积分信息
	 * @param date
	 * @return
	 */
	PageHelpVO<EtUnitIntegral> findUnitIntegral(PageVO pageVO,EtUnitIntegral si);
	
	
	void updateUnitIntegral(EtUnitIntegral etUnitIntegral);
}
