package com.service.taskStu;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.xmlparse.EpClue;

import java.util.List;

public interface DossierImportService {
    void insertEpClueList(List<EpClue> paramList);

    PageHelpVO findEpClueList(PageVO pageVO);
}
