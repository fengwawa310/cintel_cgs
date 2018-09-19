package com.service.sys;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.sys.SysNotice;
import com.param.sys.SysNoticeParam;

import java.util.List;

public interface SysNoticeService {

    PageHelpVO findNoticeList(PageVO pageVO, SysNoticeParam sysNotice);

    SysNotice findNoticeById(String id);

    void addSysNoticeSysUnit(SysNotice sysNotice);

    Integer countSysUserNoticeNoRead(String userId);

    List<String> getNoticeList();
}
