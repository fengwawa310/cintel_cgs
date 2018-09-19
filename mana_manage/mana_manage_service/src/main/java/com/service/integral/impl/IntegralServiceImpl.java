package com.service.integral.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.caseInfo.EtCase;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.entity.integral.StatisticsEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.integral.IntegralMapper;
import com.mapper.utils.EtSuspectIntegralMapper;
import com.mapper.utils.EtUnitIntegralMapper;
import com.service.integral.IntegralService;

@Service
@Transactional
public class IntegralServiceImpl implements IntegralService {

	@Resource
	private IntegralMapper integralMapper;
	
	@Resource
	private EtSuspectIntegralMapper siMapper;

	@Resource
	private EtUnitIntegralMapper uiMapper;
	
	@Override
	public List<StatisticsEntity> caseClassStatistics(String creatTime) {
		// TODO Auto-generated method stub
		return integralMapper.caseClassStatistics(creatTime);
	}
	
	@Override
	public List<StatisticsEntity> caseZoneStatistics(String creatTime) {
		// TODO Auto-generated method stub
		return integralMapper.caseZoneStatistics(creatTime);
	}

	@Override
	public List<StatisticsEntity> dailyStatistics(String createTime) {
		// TODO Auto-generated method stub
		return integralMapper.dailyStatistics(createTime);
	}
	
	@Override
    public void updateUnitIntegral(EtUnitIntegral etUnitIntegral) {
		EtUnitIntegral entity = this.uiMapper.selectByPrimaryKey(etUnitIntegral.getId());
		//
		int basePoints_chang = etUnitIntegral.getBasePoints() - entity.getBasePoints();
		//激励扣除积分变化
		//int kcPoints_chang = etUnitIntegral.getAwardsDeducPoints() - entity.getAwardsDeducPoints();
		int kcPoints_chang = etUnitIntegral.getAwardsDeducPoints();
		
		entity.setTotalPoints(entity.getTotalPoints()+ basePoints_chang - kcPoints_chang);

		entity.setBasePoints(etUnitIntegral.getBasePoints());
		entity.setAwardsDeducPoints(etUnitIntegral.getAwardsDeducPoints());
		
		uiMapper.update(entity);
    }

	@Override
	public PageHelpVO<EtSuspectIntegral> findSuspectIntegral(PageVO pageVO,
			EtSuspectIntegral si) {
		Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        
		List<EtSuspectIntegral> siList = siMapper.findSuspectIntegral(si);
		PageInfo<EtSuspectIntegral> pageInfo = new PageInfo<>(siList);
		PageHelpVO<EtSuspectIntegral> pageHelpVO = new PageHelpVO<EtSuspectIntegral>(pageInfo.getTotal(),siList);
		return pageHelpVO;
	}

	@Override
	public PageHelpVO<EtUnitIntegral> findUnitIntegralStatistics(PageVO pageVO,
			EtUnitIntegral si) {
		PageHelper.startPage(pageVO.getStart(), pageVO.getLength());
		
		List<EtUnitIntegral> siList = uiMapper.findUnitIntegralStatistics(si);
		PageInfo<EtUnitIntegral> pageInfo = new PageInfo<>(siList);
		PageHelpVO<EtUnitIntegral> pageHelpVO = new PageHelpVO<EtUnitIntegral>(pageInfo.getTotal(),siList);
		return pageHelpVO;
	}
	
	@Override
	public PageHelpVO<EtUnitIntegral> findUnitIntegral(PageVO pageVO,
			EtUnitIntegral si) {
		PageHelper.startPage(pageVO.getStart(), pageVO.getLength());
		
		List<EtUnitIntegral> siList = uiMapper.findUnitIntegral(si);
		PageInfo<EtUnitIntegral> pageInfo = new PageInfo<>(siList);
		PageHelpVO<EtUnitIntegral> pageHelpVO = new PageHelpVO<EtUnitIntegral>(pageInfo.getTotal(),siList);
		return pageHelpVO;
	}

}
