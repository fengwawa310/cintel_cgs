package com.service.communal;

import com.entity.DicArea;
import com.entity.DicCommon;
import com.entity.DicToTel;
import com.entity.DicUnit;

import java.util.List;
import java.util.Map;

public interface DicUtilsService {
	/**
	 * 根据父级字典编码查询出以下的字典
	 * @param parentCode
	 * @return
	 */
	public List<DicCommon> findDicCommonList(String parentCode);

	/**
	 * 查询字典中所有信息
	 * @return
	 */
	public List<DicCommon> findDicCommonList();

	/**
	 * 查询所有单位机构
	 * @return
	 * @param map
	 */
	public List<DicUnit> findDicUnitList(Map<String,String> map);
	/**
	 * 根据CODE查询所属单位信息
	 * @return
	 */
	public DicUnit findDicUnitByCode(String code);

	public DicUnit findDicUnitByID(String id);
	/**
	 * 查询所有地区单位
	 * @return
	 * @param arearoot
	 */
	public List<DicArea> findDicAreaList(String arearoot);


	List<DicUnit> findDicUnitListByPId(String id);

	/**
	 * 依据Grade数值获取对应级别的单位数据
	 * @param grade
	 * @return
	 */
	public List<DicUnit> findDicUnitListByGrade(String grade);

	/**
	 * @Author: sky
	 * @Description:根据配置文件中的区域ID获取电话区号
	 * @Date: 上午10:22 2018/4/25
	 * @param: code 区域code
	 */
	public DicToTel getAreacodeByGlobal(String code);

}
