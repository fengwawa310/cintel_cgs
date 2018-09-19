package com.mapper.sourceData;


//import com.entity.task.sourceData.SrcCaseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SrcCaseEntityMapper {
    /**
     * 依据案件编号 查询案件
     * @return
     */
    Map<String,Object> selectByAjbh(Map<String,Object> sqlMap);
    //添加数据
    int insert(Map<String,Object> sqlMap);
    //修改数据
    int updateByPrimaryKey(Map<String,Object> sqlMap);

    /**
     * 查询最新的一条数据
     * @return
     */
//    SrcCaseEntity findLatestUpdateTime();

    /**
     * 查询 创建时间 最大值
     * @return
     */
    Date selectMaxCreateTime();

    /**
     * 查询 最后修改时间 最大值
     * @return
     */
    Date selectMaxUpdateTime(Map<String,Object> cnfObj);

    /**
     * 查询本地库中 与指定ajbhs有关联的数据的 ajbh 集合
     * @param ajbhs
     * @return
     */
//    List<String> selectAjbhsByAjbhs(List<String> ajbhs);
}