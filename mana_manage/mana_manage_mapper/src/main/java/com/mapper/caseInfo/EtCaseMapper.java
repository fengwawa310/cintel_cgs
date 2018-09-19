package com.mapper.caseInfo;


import com.entity.caseInfo.ApScarela;
import com.entity.caseInfo.CaseSeries;
import com.entity.caseInfo.EtCase;
import com.mapper.BaseDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EtCaseMapper  /*extends BaseDao<EtCase, Long>*/ {
    int insert(EtCase etCase) throws Exception;
    /*案件编辑*/
    int updateCase(EtCase etCase);
    /*案件状态修改*/
    int updateByPrimaryKey(EtCase etCase);

    int deleteByPrimaryKey(String id);
    /*查询案件列表*/
    List<EtCase> findCase(EtCase etCase);
    /*通过人员查询案件列表*/
    List<EtCase> findCaseAndApStaffOpen(EtCase etCase);
    List<EtCase> findCaseAndApStaffClose(EtCase etCase);
    /*
     *综合搜索根据id集合搜索结果集
     *
     */
    List<EtCase> findCaseForSearch(List<String> ids);

    EtCase findCaseById(Map<String,Object> map);
    /*案件串并*/
    List<CaseSeries> caseSeries(CaseSeries caseSeries);

    /**
     * 查询创建时间字段的最大值
     * @return
     */
    Date selectMaxCreateTime();

    /**
     * 查询修改时间字段的最大值
     * @return
     */
    Date selectMaxUpdateTime();

    int insertToApScarela(ApScarela apscarela);

    /*删除案件插入案件重点人员关系表*/
    void deleteApScarela(String id);

    void deleteApAcarelaByCaseId(String id);

    /*根据id获取详情信息*/
    EtCase getCaseById(String o);

    //    获取案件总量
    int getCaseNum();
}