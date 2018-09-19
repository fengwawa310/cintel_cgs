package com.service.taskStu.impl;

import com.entity.taskStu.ApJudgelog;
import com.mapper.taskStu.ApJudgelogMapper;
import com.service.taskStu.ApJudgelogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author admin
 * @create 2018-03-07 16:12
 **/
@Transactional
@Service
public class ApJudgelogServiceImpl implements ApJudgelogService {

    @Resource
    private ApJudgelogMapper apJudgelogMapper;

    @Override
    public void insertApJudgelog(ApJudgelog apJudgelog) {
        apJudgelogMapper.insert(apJudgelog);
    }
}
