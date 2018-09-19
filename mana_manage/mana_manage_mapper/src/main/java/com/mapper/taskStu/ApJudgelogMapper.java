package com.mapper.taskStu;


import com.entity.taskStu.ApJudgelog;

import java.util.List;

public interface ApJudgelogMapper {
    int insert(ApJudgelog record);

    int insertSelective(ApJudgelog record);

    List<ApJudgelog> selectJudgeDetailLog(String id);
}