package com.mapper.communal;

import java.util.List;
import java.util.Map;

import com.entity.DicUnit;

public interface DicUnitMapper {
    /*查询所有单位机构*/
    List<DicUnit> selectDicUnitList(Map<String,String> map);
    /*根据code  查询单位机构信息*/
    DicUnit selectDicUnitByCode(String code);
 
    DicUnit selectDicUnitByID(String id);

    int deleteByPrimaryKey(String id);

    int insert(DicUnit record);

    int insertSelective(DicUnit record);

    int updateByPrimaryKeySelective(DicUnit record);

    int updateByPrimaryKey(DicUnit record);

    List<DicUnit> findDicUnitListByPId(String id);

    List<DicUnit> selectDicUnitByGrade(String grade);
}