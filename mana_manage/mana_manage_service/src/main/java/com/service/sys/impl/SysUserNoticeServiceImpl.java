package com.service.sys.impl;

import com.entity.sys.SysUserNotice;
import com.mapper.sys.SysUserNoticeMapper;
import com.service.sys.SysUserNoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户消息实现类
 *
 * @author admin
 * @create 2018-01-22 15:43
 **/

@Service
@Transactional
public class SysUserNoticeServiceImpl implements SysUserNoticeService {

    @Resource
    private SysUserNoticeMapper sysUserNoticeMapper;

    @Override
    public void addSysUserNotice(SysUserNotice sysUserNotice) {
        sysUserNoticeMapper.insert(sysUserNotice);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUserNotice record) {
        return sysUserNoticeMapper.updateByPrimaryKeySelective(record);
    }
}
