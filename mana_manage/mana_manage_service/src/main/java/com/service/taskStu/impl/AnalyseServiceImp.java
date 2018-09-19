package com.service.taskStu.impl;

import com.mapper.taskStu.EtAnalyseMapper;
import com.service.taskStu.EtAnalyseService;
import com.vo.taskStu.EtAnalyse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: sky
 * @Description:
 * @Date: Create in 下午2:17 2018/4/23
 */
@Transactional
@Service
public class AnalyseServiceImp implements EtAnalyseService {

    @Resource
    EtAnalyseMapper etAnalyseMapper;


    @Override
    public int insertSelective(EtAnalyse record) {
        return etAnalyseMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(EtAnalyse record) {
        return etAnalyseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return etAnalyseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<EtAnalyse> selectByType(EtAnalyse record) {
        return etAnalyseMapper.selectByType(record);
    }

    @Override
    public EtAnalyse selectByPrimaryKey(String id) {
        return etAnalyseMapper.selectByPrimaryKey(id);
    }
}
