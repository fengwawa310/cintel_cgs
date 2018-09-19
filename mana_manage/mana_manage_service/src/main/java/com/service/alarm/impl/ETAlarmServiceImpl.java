package com.service.alarm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.consts.Const;
import com.common.utils.ElasticSearchUtils;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.alarm.EtAlarm;
import com.entity.integral.EtUnitIntegral;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.alarm.EtAlarmMapper;
import com.mapper.utils.EtUnitIntegralMapper;
import com.mapper.utils.PublicEtSuspectMapper;
import com.mapper.utils.RlSuspectAlarmMapper;
import com.service.alarm.ETAlarmService;
import com.service.utils.IntegralCalculationService;


@Service("eTAlarmService")
public class ETAlarmServiceImpl implements ETAlarmService {
	
	 private static final Logger logger = LoggerFactory.getLogger(ETAlarmServiceImpl.class);

	@Resource 
	private EtAlarmMapper etAlarmMapper;
	   
	//操作人员积分
	@Resource
    private IntegralCalculationService integralCalculationService;
  //操作单位积分
    @Resource
    private EtUnitIntegralMapper etUnitIntegralMapper;
    //涉警人员关系信息
    @Resource
    private RlSuspectAlarmMapper rlSuspectAlarmMapper;
    //重点人员公用mapper,查询所有的重点人员
    @Resource
    private PublicEtSuspectMapper publicEtSuspectMapper;

	
	@Override
	public int insertEtAlarm(EtAlarm etAlarm) {
		int result=etAlarmMapper.insertSelective(etAlarm);
		if(result>0){
			try{
				//单位积分
				EtUnitIntegral etUnitIntegral = integralCalculationService.integralCalcOfUnit(Const.INTEGRAL_UNIT_ALARM, etAlarm.getUnitCode(), etAlarm.getUnitName());
				if(etUnitIntegral!=null){
					etUnitIntegralMapper.mergeUnitIntegral(etUnitIntegral);
				}
			 } catch (Exception e) {
		            logger.info("案件录入积分异常："+e);
		            e.printStackTrace();
		        }
			}
		return result;
	}
	@Override
	public int updateEtAlarm(EtAlarm etAlarm) {
		return etAlarmMapper.updateByPrimaryKeySelective(etAlarm);
	}
	@Override
	public int deleteByPrimaryKey(String id) {
		int i = etAlarmMapper.deleteByPrimaryKey(id);
		try {
			ElasticSearchUtils.delete("et_case","cgs",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public int abandonByPrimaryKey(String id) {
		return etAlarmMapper.abandonByPrimaryKey(id);
	}
	@Override
	public int alarmDeleteReplyById(String id) {
		return etAlarmMapper.alarmDeleteReplyById(id);
	}
	@Override
	public EtAlarm selectByPrimaryKey(String id) {
		return etAlarmMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<EtAlarm> selectEtAlarmList(EtAlarm etAlarm) {
		return etAlarmMapper.selectEtAlarmList(etAlarm);
	}
	@Override
	public List<EtAlarm> countEtAlarmList(EtAlarm etAlarm) {
		return etAlarmMapper.countEtAlarmList(etAlarm);
	}
	@Override
	public List<EtAlarm> selectDelEtAlarmList(EtAlarm etAlarm) {
		return etAlarmMapper.selectDelEtAlarmList(etAlarm);
	}
	@Override
	public List<EtAlarm> countDelEtAlarmList(EtAlarm etAlarm) {
		return etAlarmMapper.countDelEtAlarmList(etAlarm);
	}
	//查询已经人工确认的涉警列表
	@Override
	public PageHelpVO suspectAlarmOpen(PageVO pageVO,String suspectId) {
		 Integer start = pageVO.getStart();
	        Integer length = pageVO.getLength();
	        PageHelper.startPage(start, length);
		List<EtAlarm> list=etAlarmMapper.suspectAlarmOpen(suspectId);
		PageInfo<EtAlarm> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<EtAlarm>(total, list);
		return pageHelpVO;
	}
	//查询未经人工确认的涉警列表
	@Override
	public PageHelpVO suspectAlarmClose(PageVO pageVO,String suspectId) {
		//先查询登录用户的所属单位的级别，拿到可获取警情的范围代码（派出所、区、市）
//		String rlEntryUnit = etAlarmMapper.rlEntryUnit(suspectId);
		Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        Map<String , String> map=new HashMap<>();
		map.put("unitCode", "44030");
		map.put("suspectId", suspectId);
	List<EtAlarm> list=etAlarmMapper.suspectAlarmClose(map);
	PageInfo<EtAlarm> pageInfo = new PageInfo<>(list);
    long total = pageInfo.getTotal();
    PageHelpVO pageHelpVO = new PageHelpVO<EtAlarm>(total, list);
	return pageHelpVO;
	}
	

}
