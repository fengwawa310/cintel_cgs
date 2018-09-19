package com.mapper.alarm;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.alarm.EtAlarm;
import com.entity.caseInfo.EtCase;
import com.entity.suspect.EtSuspect;
import com.mapper.BaseDao;

public interface EtAlarmMapper {

	/*
	 * 警情录入，插入新的警情
	 */
	int insertSelective(EtAlarm record);
	/*
	 * 实体删除，数据将不会恢复
	 */
    int deleteByPrimaryKey(String id);
    /*
     * 根据Id更新警情数据
     */
    int updateByPrimaryKeySelective(EtAlarm record);
    /*
     * 状态删除，更新数据删除字段状态
     * 更新 is_abandon 字段成 1: '删除标识：0未删除；1已删除；默认0',
     */
    int abandonByPrimaryKey(String id);
    /*
     * 状态删除恢复，更新数据删除字段状态
     * 更新 is_abandon 字段成 0: '删除标识：0未删除；1已删除；默认0',
     */
    int alarmDeleteReplyById(String id);
    /*
     * 根据Id查询相关警情
     */
    EtAlarm selectByPrimaryKey(String id);
    /*
     * 根据条件 查询所有未删除警情数据List
     */
    List<EtAlarm> selectEtAlarmList(EtAlarm etAlarm);
    /*
     * 根据条件 查询所有未删除警情数据List 统计
     */
    List<EtAlarm> countEtAlarmList(EtAlarm etAlarm);
    /*
     * 根据条件 查询所有删除到回收站警情数据List
     */
	List<EtAlarm> selectDelEtAlarmList(EtAlarm etAlarm);
	 /*
     * 根据条件 查询所有删除到回收站警情数据List统计
     */
	List<EtAlarm> countDelEtAlarmList(EtAlarm etAlarm);
	/*
	 *根据重点人员，查询重点人员已经人工确认过关联的警情列表
	 *
	 */
	List<EtAlarm> suspectAlarmOpen(@Param("suspectId") String  suspectId);
	/*
	 *根据重点人员，查询重点人员已经人工确认过关联的警情列表
	 *
	 */
	String rlEntryUnit(@Param("suspectId")String suspectId);
	/*
	 *根据重点人员，查询重点人员已经人工没有确认过的警情列表
	 *
	 */
	List<EtAlarm> suspectAlarmClose(Map<String,String> rlMap);
	/*
	 *综合搜索根据id集合搜索结果集
	 *
	 */
	List<EtAlarm> findAlarmForSearch(List<String> ids);

    Date selectMaxCreateTime();

    Date selectMaxUpdateTime();

    EtAlarm findById(Map<String, Object> param);
}