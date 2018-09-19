package com.service.taskStu.impl;

import com.common.enums.DossierEnumType;
import com.common.enums.EnumTypeVO;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.xmlparse.EpClue;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.taskStu.EpClueMapper;
import com.service.taskStu.DossierImportService;
import com.vo.taskStu.EpClueVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @create 2018-03-15 18:06
 **/
@Transactional
@Service
public class DossierImportServiceImpl implements DossierImportService {

    @Resource
    private EpClueMapper EpClueMapper;

    @Override
    public void insertEpClueList(List<EpClue> paramList) {
        EpClueMapper.insertEpClueList(paramList);
    }

    @Override
    public PageHelpVO findEpClueList(PageVO pageVO) {
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<EpClue> list = EpClueMapper.findEpClueList();
        List<EpClueVO> lists = new ArrayList<>();
        for (EpClue epClue : list) {
            EpClueVO epClueVO1 = new EpClueVO();
            BeanUtils.copyProperties(epClue, epClueVO1);
            DossierEnumType valueof = DossierEnumType.valueof(Integer.parseInt(epClueVO1.getType()));
            epClueVO1.setTypeEnum(new EnumTypeVO(valueof.getName(),String.valueOf(valueof.getValue())));
            lists.add(epClueVO1);
        }
        PageInfo<EpClue> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<EpClueVO>(total, lists);
        return pageHelpVO;
    }
}
