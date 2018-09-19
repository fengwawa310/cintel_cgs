package com.mapper.utils;

import java.util.List;

import com.entity.integral.EtSuspectIntegral;


public interface EtSuspectIntegralMapper {
    /**
     *
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     */
    int insert(EtSuspectIntegral record);

    /**
     *
     */
    int insertSelective(EtSuspectIntegral record);

    /**
     *
     */
    EtSuspectIntegral selectByPrimaryKey(String id);

    /**
     *
     */
    List<EtSuspectIntegral> findSuspectIntegral(EtSuspectIntegral si);

    /**
     *
     */
    int updateByPrimaryKeySelective(EtSuspectIntegral record);

    /**
     *
     */
    int updateByPrimaryKey(EtSuspectIntegral record);
    
    
    /**
    * 人员积分保存（inertOrUpdate）<br/>
    * 更新时依赖于数据表中唯一索引IDCARD_NUM，注意积分部分字段是在原有值上增加。
    * 
    * @param unitIntegral 人员积分实例集合
    */
    void mergeSuspectIntegral(List<EtSuspectIntegral> list);
}