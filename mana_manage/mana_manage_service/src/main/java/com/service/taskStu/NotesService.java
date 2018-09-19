package com.service.taskStu;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.task.EpAsjBl;

import java.util.Map;

public interface NotesService {
    EpAsjBl selectByPrimaryKey(String id);


    PageHelpVO findNotesList(PageVO pageVO,Map map);
}
