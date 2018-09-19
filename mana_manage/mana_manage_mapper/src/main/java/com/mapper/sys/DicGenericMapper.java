package com.mapper.sys;

import com.entity.DicGeneric;
import com.mapper.BaseDao;

public interface DicGenericMapper extends BaseDao  {
    int deleteByPrimaryKey(String typeCode);

    int insert(DicGeneric record);

    int insertSelective(DicGeneric record);

    DicGeneric selectByPrimaryKey(String typeCode);

    int updateByPrimaryKeySelective(DicGeneric record);

    int updateByPrimaryKey(DicGeneric record);
}