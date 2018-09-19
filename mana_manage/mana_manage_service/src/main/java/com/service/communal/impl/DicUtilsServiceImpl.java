package com.service.communal.impl;

import com.entity.DicArea;
import com.entity.DicCommon;
import com.entity.DicToTel;
import com.entity.DicUnit;
import com.mapper.communal.DicAreaMapper;
import com.mapper.communal.DicCommonMapper;
import com.mapper.communal.DicToTelMapper;
import com.mapper.communal.DicUnitMapper;
import com.service.communal.DicUtilsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class DicUtilsServiceImpl implements DicUtilsService {

	/**
	 * 查询字典信息
	 */
	@Resource
	private DicCommonMapper dicCommonMapper;
	@Resource
	private DicToTelMapper dicToTelMapper;


	/**
	 * 根据父级字典编码查询出以下的字典
	 * @param parentCode
	 * @return
	 */
	@Override
	public List<DicCommon> findDicCommonList(String parentCode) {
		return dicCommonMapper.selectDicCommon(parentCode);
	}
	/**
	 * 查询字典中所有信息
	 * @return
	 */
	@Override
	public List<DicCommon> findDicCommonList() {
		return dicCommonMapper.selectDicCommons();
	}

	/**
	 * 查询单位机构
	 */
	@Resource
	private DicUnitMapper dicUnitMapper;

	/**
	 * 查询所有单位机构
	 * @return
	 * @param map
	 */
	@Override
	public List<DicUnit> findDicUnitList(Map<String,String> map) {
		return dicUnitMapper.selectDicUnitList(map);
	}
	/**
	 * 查询单位机构
	 */
	@Resource
	private DicAreaMapper dicAreaMapper;

	/**
	 * 查询所有地区单位
	 * @return
	 * @param arearoot
	 */
	private List<DicArea> list = new ArrayList<>();
	@Override
	public List<DicArea> findDicAreaList(String arearoot) {
		list=new ArrayList<>();
		List<DicArea> dicAreas = dicAreaMapper.selectDicAreaList(arearoot);
		if(dicAreas!=null&&dicAreas.size()>1){
			for (DicArea dicArea:dicAreas) {
				list.add(dicArea);
				if(!arearoot.equals(dicArea.getId())){
					selectDicAreaList(dicArea);
				}
			}
		}else{
			list.addAll(dicAreas);
		}
		return list;
	}

	public void selectDicAreaList(DicArea dicArea) {
		List<DicArea> dicAreas = dicAreaMapper.selectDicAreaList(dicArea.getId());
		if(dicAreas!=null&&dicAreas.size()>1){
			for (DicArea area:dicAreas) {
				if(!(area.getId()).equals(dicArea.getId())){
					list.add(area);
					selectDicAreaList(area);
				}
			}
		}
	}

	@Override
	public List<DicUnit> findDicUnitListByPId(String id) {
		return dicUnitMapper.findDicUnitListByPId(id);
	}

	@Override
	public List<DicUnit> findDicUnitListByGrade(String grade) {
		return dicUnitMapper.selectDicUnitByGrade(grade);
	}

	/**
	 * @Author: sky
	 * @Description:根据配置文件中的区域ID获取电话区号
	 * @Date: 上午10:22 2018/4/25
	 * @param: code 区域code
	 */
	@Override
	public DicToTel getAreacodeByGlobal(String code) {
		return dicToTelMapper.getAreacodeByGlobal(code);
	}

	/**
	 * 根据code 查询所属单位
	 */
	@Override
	public DicUnit findDicUnitByCode(String code) {
		DicUnit dicUnit=dicUnitMapper.selectDicUnitByCode(code);
		return dicUnit;
	}

	/**
	 * 根据id 查询单位
	 */
	@Override
	public DicUnit findDicUnitByID(String id) {
		return dicUnitMapper.selectDicUnitByID(id);
	}


}
