package com.mapper.utils;

import java.util.List;

import com.entity.integral.EtUnitIntegral;

public interface EtUnitIntegralMapper {

    int insert(EtUnitIntegral record);

    int deleteByPrimaryKey(String id);

    int update(EtUnitIntegral record);

    EtUnitIntegral selectByPrimaryKey(String id);
    
    /**
     *单位积分统计
     */
    List<EtUnitIntegral> findUnitIntegralStatistics(EtUnitIntegral si);

    /**
     *查询单位积分
     */
    List<EtUnitIntegral> findUnitIntegral(EtUnitIntegral si);

    /**
     * 单位积分保存（inertOrUpdate）<br/>
     * 更新时依赖于数据表中唯一索引unit_code，注意积分部分字段是在原有值上增加。
     * 
     * @param unitIntegral 单位积分实例
     */
    void mergeUnitIntegral(EtUnitIntegral unitIntegral);
}