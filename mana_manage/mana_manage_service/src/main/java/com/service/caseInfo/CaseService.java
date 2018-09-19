package com.service.caseInfo;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.caseInfo.ApStaff;
import com.entity.caseInfo.CaseSeries;
import com.entity.caseInfo.EtCase;
import com.entity.suspect.EtWarning;

import java.util.List;
import java.util.Map;

/**
 * Created by weipc on 2018/1/2.
 */
public interface CaseService /*extends BaseService<EtCase, Long>*/{
    void save(EtCase etCase, String apStaffs) throws Exception;

    void update(EtCase etCase) throws Exception;

    void update(EtCase etCase, String apStaffs,String apStaffIds) throws Exception;

    void delete(String id) throws Exception;

    PageHelpVO findCase(PageVO pageVO, EtCase etCase);

    Map<String,Object> findCaseById(Map<String,Object> map) throws Exception;

    PageHelpVO caseSeries(PageVO pageVO, CaseSeries caseSeries);

    EtCase getCaseById(String o);

//    根据人员身份证号查询相关涉案人员
    List<ApStaff> findInvolveByIdcard(ApStaff apStaff);

//    根据staffId查找涉案涉警编号列表
    List<String> findCaseNoByStaffId(String staffId);

//    获取案件总量
    int getCaseNum();
}
