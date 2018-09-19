package com.service.taskStu;

import com.vo.taskStu.EtAnalyse;

import java.util.List;

/**
 * @Author: sky
 * @Description:
 * @Date: Create in 下午2:17 2018/4/23
 */
public interface EtAnalyseService {

    //新增模块分析结果
    int insertSelective(EtAnalyse record);

    //根据主键更新数据
    int updateByPrimaryKeySelective(EtAnalyse record);

    //根据id删除数据
    int deleteByPrimaryKey(String id);

    //根据场景值以及用户id获取列表，按更新时间降序排列
    List<EtAnalyse> selectByType(EtAnalyse record);

    EtAnalyse selectByPrimaryKey(String id);

}
