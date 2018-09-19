package com.mapper.task.sourceData;

import com.entity.alarm.EtAlarm;
import com.entity.caseInfo.ApScarela;
import com.entity.caseInfo.ApStaff;
import com.entity.caseInfo.EtCase;
import com.entity.task.CTPageQueryBean;
//import com.entity.task.sourceData.JZCaseEntity;

import java.util.List;
import java.util.Map;

public interface JZCaseEntityMapper {
    List<EtCase> findPageAJData(Map<String,Object> map);

    List<EtAlarm> findPageJQData(Map<String,Object> map);

    List<ApScarela> findPageRYQKData(Map<String, Object> queryParam);

    List<ApStaff> findPageXYRData(Map<String, Object> queryParam);
}