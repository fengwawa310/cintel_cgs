package com.service.utils.impl;

import com.common.consts.Const;
import com.common.utils.IDGenerator;
import com.entity.DicCommon;
import com.entity.caseInfo.ApStaff;
import com.entity.infor.ApInforStaff;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.entity.suspect.EtSuspect;
import com.mapper.communal.DicCommonMapper;
import com.mapper.suspect.EtSuspectMapper;
import com.service.utils.IntegralCalculationService;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class IntegralCalculationServiceImpl implements IntegralCalculationService {

	@Resource
	private EtSuspectMapper etSuspectMapper;
    @Resource
    private DicCommonMapper dicCommonMapper;

    private List<DicCommon> jfList;

    private void initJFList(){
    	jfList = dicCommonMapper.selectDicCommon(Const.INTEGRAL_FZ);
    }

    /**
     *查询重点人员
     */
    private EtSuspect findEcSuspect(String idCardNum){
		EtSuspect tj = new EtSuspect();
		tj.setIdcardNum(idCardNum);
		tj.setIsAbandon(Integer.valueOf(0));//未回收
		tj.setIsArchive(Integer.valueOf(0));//未归档
		List<EtSuspect> etSuspectList = this.etSuspectMapper.findAll(tj);
		if(etSuspectList != null && etSuspectList.size() == 1){//唯一性
			return etSuspectList.get(0);
		}
		return null;
    }

    private DicCommon getDicCommon(String dicCode){
    	DicCommon result = null;
    	for(DicCommon one : jfList){
    		if(one.getDicCode().equals(dicCode)){
    			result = one;
    			break;
    		}
    	}
    	return result;
    }

    private String getSuspectBlCode(String suspectClass){
    	if(Const.SUSPECT_TYPE_QT.equals(suspectClass)){
    		return Const.INTEGRAL_SUSPECT_BL_ZD;
    	}else if(Const.SUSPECT_TYPE_XM.equals(suspectClass) 
    			||Const.SUSPECT_TYPE_BGK.equals(suspectClass) 
    			|| Const.SUSPECT_TYPE_ZY.equals(suspectClass) 
    			|| Const.SUSPECT_TYPE_QB.equals(suspectClass)){
    		return Const.INTEGRAL_SUSPECT_BL_BGK;
    	}else if(Const.SUSPECT_TYPE_ZT.equals(suspectClass)){
    		return Const.INTEGRAL_SUSPECT_BL_ZT;
    	}
    	return null;
    }
    /**
     * 计算人员积分
     * @param suspectClass
     * @param type
     * @return
     */
    private Integer calcSuspectIntegral(String suspectClass,String type){
    	Integer result = Integer.valueOf(0);
    	String suspectBlCode = this.getSuspectBlCode(suspectClass);
    	if(suspectBlCode != null){
    		//倍率
    		DicCommon blDic = getDicCommon(suspectBlCode);
    		//案件默认积分
    		DicCommon integralDic = getDicCommon(type);
    		if(blDic != null && integralDic != null){
    			int bl = Integer.valueOf(blDic.getDicValue()).intValue();
    			int jf = Integer.valueOf(integralDic.getDicValue()).intValue();
    			result = Integer.valueOf(jf*bl);
    		}
    	}
    	return result;
    }


    @Override
    public EtSuspectIntegral integralCalcOfSuspect(Object suspect) {
    	List tmpList = new ArrayList();
    	tmpList.add(suspect);
    	List<EtSuspectIntegral> result = integralCalcOfSuspectList(tmpList);
    	if(result.size()>0)
    		return result.get(0);
    	return null;
    }

	@Override
	public List<EtSuspectIntegral> integralCalcOfSuspectList(List suspectList) {
		List<EtSuspectIntegral> result = new ArrayList<>();
		if(suspectList == null || suspectList.isEmpty())
			return result;

		initJFList();

		for(Object object : suspectList){
			if(object instanceof ApStaff){//案件录入
				//
				ApStaff one = (ApStaff)object;
				String idCardNum = one.getIdcardNum();
				EtSuspect etSuspect = this.findEcSuspect(idCardNum);
				if(etSuspect != null){
					EtSuspectIntegral integral = generateEtSuspectIntegral(idCardNum,etSuspect);
					//计算应得积分
					integral.setIntegral(calcSuspectIntegral(String.valueOf(etSuspect.getSuspectClass()),Const.INTEGRAL_SUSPECT_CASE));
					result.add(integral);
				}
			}else if(object instanceof ApInforStaff){//情报录入
				//
				ApInforStaff one = (ApInforStaff)object;
				String idCardNum = one.getIdcardNum();
				EtSuspect etSuspect = this.findEcSuspect(idCardNum);
				if(etSuspect != null){
					EtSuspectIntegral integral = generateEtSuspectIntegral(idCardNum,etSuspect);
					//计算应得积分
					integral.setIntegral(calcSuspectIntegral(String.valueOf(etSuspect.getSuspectClass()),Const.INTEGRAL_SUSPECT_QB));
					result.add(integral);
				}
			}
		}

		return result;
	}

	private EtSuspectIntegral generateEtSuspectIntegral(String idCardNum,EtSuspect etSuspect){
		EtSuspectIntegral integral = new EtSuspectIntegral();

		integral.setId(IDGenerator.getorderNo());
		integral.setSuspectId(etSuspect.getSuspectId());
		integral.setSuspectName(etSuspect.getName());
		integral.setIdcardNum(idCardNum);
		integral.setSuspectClass(etSuspect.getSuspectClass());

		return integral;
	}

	private EtUnitIntegral initEtUnitIntegral(){
		EtUnitIntegral unitIntegral = new EtUnitIntegral();

		unitIntegral.setAwardCount(Integer.valueOf(0));
		unitIntegral.setAwardsDeducPoints(Integer.valueOf(0));
		unitIntegral.setBasePoints(Integer.valueOf(0));
		unitIntegral.setCasePoints(Integer.valueOf(0));
		unitIntegral.setAlarmPoints(Integer.valueOf(0));
		unitIntegral.setClueReportPoints(Integer.valueOf(0));
		unitIntegral.setHonourCount(Integer.valueOf(0));
		unitIntegral.setInforReportPoints(Integer.valueOf(0));
		unitIntegral.setJudgePoints(Integer.valueOf(0));
		unitIntegral.setSuspectReportPoints(Integer.valueOf(0));
		unitIntegral.setTotalPoints(Integer.valueOf(0));

		return unitIntegral;
	}

	@Override
	public EtUnitIntegral integralCalcOfUnit(String unitIntegralType,String unitCode,String unitName, Integer count) {
		if(StringUtils.isBlank(unitIntegralType) 
				|| StringUtils.isBlank(unitCode) 
				|| StringUtils.isBlank(unitName)
				|| count == null || count <= 0)
			return null;

		initJFList();
		DicCommon unitJFTypeDic = getDicCommon(unitIntegralType);
		if(unitJFTypeDic != null){

			EtUnitIntegral unitIntegral = initEtUnitIntegral();
			unitIntegral.setId(IDGenerator.getorderNo());
			unitIntegral.setUnitCode(unitCode);
			unitIntegral.setUnitName(unitName);

			Integer jf = Integer.valueOf(Integer.valueOf(unitJFTypeDic.getDicValue()).intValue()*count.intValue());
			unitIntegral.setTotalPoints(jf);
			if(Const.INTEGRAL_UNIT_ALARM.equals(unitIntegralType)){
				unitIntegral.setAlarmPoints(jf);
			}else if(Const.INTEGRAL_UNIT_CASE.equals(unitIntegralType)){
	    		unitIntegral.setCasePoints(jf);
	    	}else if(Const.INTEGRAL_UNIT_QB.equals(unitIntegralType)){
	    		unitIntegral.setInforReportPoints(jf);
	    	}else if(Const.INTEGRAL_UNIT_SUSPECT.equals(unitIntegralType)){
	    		unitIntegral.setSuspectReportPoints(jf);
	    	}

			//
			//etUnitIntegrallMapper.mergeUnitIntegral(unitIntegral);

			return unitIntegral;
		}
		return null;
	}

	@Override
	public EtUnitIntegral integralCalcOfUnit(String unitIntegralType,
			String unitCode, String unitName) {
		//业务数据记录条数默认为1
		return integralCalcOfUnit(unitIntegralType,unitCode,unitName,Integer.valueOf(1));
	}

}
