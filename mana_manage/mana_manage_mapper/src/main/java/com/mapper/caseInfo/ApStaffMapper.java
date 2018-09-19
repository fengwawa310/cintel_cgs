package com.mapper.caseInfo;

import com.entity.caseInfo.ApStaff;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ApStaffMapper {
    /*添加嫌疑人和受害人*/
    int insert(ApStaff record);
    /*修改嫌疑人和受害人*/
    int update(ApStaff record);
    /*通过案件编号查询嫌疑人和受害人*/
    List<ApStaff> selectApStaffList(String caseNo);
    /*删除嫌疑人和受害人*/
    int delete(String id);

    ApStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApStaff record);


    Date selectMaxCreateTime();

    Date selectMaxUpdateTime();

    ApStaff findById(Map<String, Object> param);
    /*综合搜索通过id集合搜索嫌疑人和受害人*/
    List<ApStaff> findApStaffForSearch(List<String> ids);

//    根据人员身份证号查询相关涉案人员
    List<ApStaff> findInvolveByIdcard(ApStaff apStaff);

//    根据staffId查找涉案涉警编号列表
    List<String> findCaseNoByStaffId(String staffId);

}