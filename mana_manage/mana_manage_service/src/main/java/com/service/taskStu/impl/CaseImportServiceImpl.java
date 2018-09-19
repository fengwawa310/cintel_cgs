package com.service.taskStu.impl;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.taskStu.EpCaseGang;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.taskStu.EpCaseGangMapper;
import com.service.taskStu.CaseImportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by weipc on 2018/3/15.
 */
@Transactional
@Service
public class CaseImportServiceImpl implements CaseImportService {

    @Resource
    private EpCaseGangMapper epCaseGangMapper;

    @Override
    public void importCase(List<EpCaseGang> list) {
        epCaseGangMapper.insert(list);
    }

    @Override
    public PageHelpVO findImportCase(PageVO pageVO, Map<String,Object> map) {
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<EpCaseGang> list =epCaseGangMapper.findImportCase(map);
        PageInfo<EpCaseGang> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<>(total, list);
        return pageHelpVO;
    }
}
