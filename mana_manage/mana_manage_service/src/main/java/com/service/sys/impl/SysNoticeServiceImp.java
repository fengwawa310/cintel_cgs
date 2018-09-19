package com.service.sys.impl;

import com.common.enums.EnumTypeVO;
import com.common.enums.IsReadEnumType;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.sys.SysNotice;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.sys.SysNoticeMapper;
import com.param.sys.SysNoticeParam;
import com.service.sys.SysNoticeService;
import com.vo.sys.SysNoticeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SysNoticeServiceImp implements SysNoticeService {


    @Resource
    SysNoticeMapper sysNoticeMapper;

    @Override
    public PageHelpVO findNoticeList(PageVO pageVO, SysNoticeParam sysNoticeParam) {
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<SysNoticeVO> list = sysNoticeMapper.findNoticeList(sysNoticeParam);
        List<SysNoticeVO> lists = new ArrayList<>();
        for (SysNoticeVO sysNotice : list) {
            IsReadEnumType valueof = IsReadEnumType.valueof(Integer.parseInt(sysNotice.getIsRead()));
            sysNotice.setIsSendEnum(new EnumTypeVO(valueof.getName(),String.valueOf(valueof.getValue())));
            lists.add(sysNotice);
        }
        PageInfo<SysNoticeVO> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<SysNoticeVO>(total, lists);
        return pageHelpVO;
    }

    @Override
    public SysNotice findNoticeById(String id) {
        return sysNoticeMapper.findNoticeById(id);
    }

    @Override
    public void addSysNoticeSysUnit(SysNotice sysNotice) {
        sysNoticeMapper.insert(sysNotice);
    }

    @Override
    public Integer countSysUserNoticeNoRead(String userId) {
        return sysNoticeMapper.countSysUserNoticeNoRead(userId);
    }

    @Override
    public List<String> getNoticeList() {
        List<String> notices = new ArrayList<>();
        for (String notice : sysNoticeMapper.getNoticeList()){
            if (!(StringUtils.isBlank(notice) || "null".equals(notice))){
                notices.add(notice);
            }
        }
        return notices;
    }

}
