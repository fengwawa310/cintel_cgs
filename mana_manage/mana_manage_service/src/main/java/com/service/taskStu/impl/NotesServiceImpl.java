package com.service.taskStu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.task.EpAsjBl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.taskJingzong.EpAsjBlMapper;
import com.service.taskStu.NotesService;

/**
 * @author admin
 * @create 2018-03-21 16:06
 **/
@Service
@Transactional
public class NotesServiceImpl implements NotesService {

    @Resource
    private EpAsjBlMapper epAsjBlMapper;

    @Override
    public EpAsjBl selectByPrimaryKey(String id) {
        return epAsjBlMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageHelpVO findNotesList(PageVO pageVO,Map map) {
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<EpAsjBl> list = epAsjBlMapper.findNotesList(map);
//        List<EpClueVO> lists = new ArrayList<>();
//        for (EpClue epClue : list) {
//            EpClueVO epClueVO1 = new EpClueVO();
//            BeanUtils.copyProperties(epClue, epClueVO1);
//            DossierEnumType valueof = DossierEnumType.valueof(Integer.parseInt(epClueVO1.getType()));
//            epClueVO1.setTypeEnum(new EnumTypeVO(valueof.getName(),String.valueOf(valueof.getValue())));
//            lists.add(epClueVO1);
//        }
        PageInfo<EpAsjBl> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<EpAsjBl>(total, list);
        return pageHelpVO;
    }
}
