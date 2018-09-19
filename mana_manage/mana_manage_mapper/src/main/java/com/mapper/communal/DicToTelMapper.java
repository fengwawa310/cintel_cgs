package com.mapper.communal;


import com.entity.DicToTel;

public interface DicToTelMapper {
    int deleteByPrimaryKey(String id);

    int insert(DicToTel record);

    int insertSelective(DicToTel record);

    DicToTel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DicToTel record);

    int updateByPrimaryKeyWithBLOBs(DicToTel record);

    int updateByPrimaryKey(DicToTel record);

    /**
     * @Author: sky
     * @Description:根据配置文件中的区域ID获取电话区号
     * @Date: 上午10:22 2018/4/25
     * @param: code 区域code
     */
    DicToTel getAreacodeByGlobal(String code);
}