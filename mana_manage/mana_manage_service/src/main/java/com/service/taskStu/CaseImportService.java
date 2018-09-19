package com.service.taskStu;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.taskStu.EpCaseGang;

import java.util.List;
import java.util.Map;

/**
 * Created by weipc on 2018/3/15.
 */
public interface CaseImportService {
    void importCase(List<EpCaseGang> list);

    PageHelpVO findImportCase(PageVO pageVO, Map<String,Object> map);
}
