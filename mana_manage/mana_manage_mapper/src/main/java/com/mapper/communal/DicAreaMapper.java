package com.mapper.communal;

import com.entity.DicArea;
import com.entity.DicUnit;

import java.util.List;

public interface DicAreaMapper {
    /*查询所有地区单位*/
    List<DicArea> selectDicAreaList(String arearoot);

    int deleteByPrimaryKey(String id);

    int insert(DicArea record);

    int insertSelective(DicArea record);

    int updateByPrimaryKeySelective(DicArea record);

    int updateByPrimaryKeyWithBLOBs(DicArea record);

    int updateByPrimaryKey(DicArea record);
}