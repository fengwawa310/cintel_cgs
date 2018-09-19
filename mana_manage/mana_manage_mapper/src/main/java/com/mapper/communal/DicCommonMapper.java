package com.mapper.communal;

import com.entity.DicCommon;

import java.util.List;

public interface DicCommonMapper  {
    /*根据父级字典编码查询出以下的字典*/
    List<DicCommon> selectDicCommon(String parentCode);
    /*查询字典中所有信息*/
    List<DicCommon> selectDicCommons();

    /**
     * 通过字典编码获取字典
     * @param dicCode
     * @return
     */
    DicCommon selectByDicCode(String dicCode);
}