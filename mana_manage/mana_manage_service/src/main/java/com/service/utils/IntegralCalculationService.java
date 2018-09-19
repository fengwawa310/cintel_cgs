package com.service.utils;

import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;

import java.util.List;


public interface IntegralCalculationService {

	/**
	 * 计算人员积分
	 *
	 * @param suspectList 涉案/情报/警情人员对象集合（只存入嫌疑人）<br/>
	 * 案件模块传入ApStaff对象实例集合<br/>
	 * 情报模块传入ApInforStaff对象实例集合
	 *
	 * @return 人员积分计算结果集合
	 */
	List<EtSuspectIntegral> integralCalcOfSuspectList(List suspectList);

	/**
	 * 计算单位积分,业务数据记录条数默认为1。
	 *
	 * @param unitIntegralType<br/>
	 * --警情模块传入 Const.INTEGRAL_UNIT_ALARM<br/>
	 * --案件模块传入 Const.INTEGRAL_UNIT_CASE<br/>
	 * --情报模块传入 Const.INTEGRAL_UNIT_QB<br/>
	 * --重点人员模块传入 Const.INTEGRAL_UNIT_SUSPECT
	 * @param unitCode 单位编码
	 * @param unitName 单位名称
	 *
	 * @return 单位积分计算结果
	 */
	EtUnitIntegral integralCalcOfUnit(String unitIntegralType,String unitCode,String unitName);

	/**
	 * 计算单位积分
	 *
	 * @param unitIntegralType <br/>
	 * --警情模块传入 Const.INTEGRAL_UNIT_ALARM<br/>
	 * --案件模块传入 Const.INTEGRAL_UNIT_CASE<br/>
	 * --情报模块传入 Const.INTEGRAL_UNIT_QB<br/>
	 * --重点人员模块传入 Const.INTEGRAL_UNIT_SUSPECT
	 * @param count 业务数据记录条数
	 * @param unitCode 单位编码
	 * @param unitName 单位名称
	 *
	 * @return 单位积分计算结果
	 */
	EtUnitIntegral integralCalcOfUnit(String unitIntegralType,String unitCode,String unitName,Integer count);


	/**
	 * 计算人员积分
	 *
	 * @param suspect 涉案/情报/警情人员对象（只存入嫌疑人）。<br/>
	 * --案件模块传入ApStaff对象实例<br/>
	 * --情报模块传入ApInforStaff对象实例
	 *
	 * @return 人员积分计算结果实例
	 */
	EtSuspectIntegral integralCalcOfSuspect(Object suspect);

}
