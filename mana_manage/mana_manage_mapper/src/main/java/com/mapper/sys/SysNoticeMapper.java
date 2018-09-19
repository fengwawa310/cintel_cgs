package com.mapper.sys;


import com.entity.sys.SysNotice;
import com.param.sys.SysNoticeParam;
import com.vo.sys.SysNoticeVO;

import java.util.List;

public interface SysNoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysNotice record);

    int insertSelective(SysNotice record);

    SysNotice selectByPrimaryKey(String id);

    SysNotice findNoticeById(String id);

    int updateByPrimaryKeySelective(SysNotice record);

    int updateByPrimaryKeyWithBLOBs(SysNotice record);

    int updateByPrimaryKey(SysNotice record);
    //根据用户查询公告列表
    List<SysNoticeVO> findNoticeList(SysNoticeParam sysNotice);

    List<SysNotice> selectNoticListForProposal(SysNotice sysNotice);

    long countFindProposalList(SysNotice sysNotice);
    /*计算当前用户未读去的消息的数量*/
    Integer countSysUserNoticeNoRead(String userId);

    List<String> getNoticeList();
}