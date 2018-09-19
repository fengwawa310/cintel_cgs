package com.mapper.suspect;

import java.util.List;
import java.util.Map;

import com.entity.suspect.EtSuspect;
import com.entity.suspect.OperSuspectVo;
import com.param.suspect.EtSuspectDTO;
import com.vo.suspect.SuspectTreePo;

public interface EtSuspectMapper {
    int deleteByPrimaryKey(String id);

    int insert(EtSuspect record);

    int insertSelective(EtSuspect record);

    EtSuspect selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtSuspect record);

    int updateByPrimaryKey(EtSuspect record);

    List<EtSuspect> findAll(EtSuspect etSuspect);

    List<SuspectTreePo> fuzzeyQuery(EtSuspect etSuspect);

    int searchIdCard(String idcardnum);

    EtSuspect selectByNo(String suspectNo);


	List<EtSuspect> listAllForOperator(Map<String, Object> map);
	List<EtSuspect> listAllForOperatorForALL(Map<String, String> map);
    /*根据条件查询嫌疑人分页信息*/
    List<EtSuspect> findSuspectList(EtSuspectDTO etSuspectDTO);

    List<OperSuspectVo> fuzzeyQueryWithPermission(Map<String, String> map);

    EtSuspect findSuspectById(Map<String, Object> map);
    /*查询改人员是否已录入*/
    EtSuspect selectIDCardNum(EtSuspect etSuspect);
    //综合查询根据人员id集合查询   
    List<EtSuspect> findSuspectForSearch(List<String> ids);

    //根据姓名和身份证判断查询重点人员
    EtSuspect findSuspectByBaseinfo(EtSuspect etSuspect);
    //根据团伙id获取团伙人员
    List<EtSuspect> selectByGangId(String gangId);
    //查询重点人员查看权限
    Map<String, Object> suspectRole(Map<String, Object> map);
//    获取重点人员总数
    int getSuspectNum();
}