package com.mapper.infor;


import com.entity.infor.BInforFlow;
import com.vo.infor.IntelligenceListRequetParam;
import com.vo.infor.IntelligenceListResponseVO;

import java.util.List;

public interface BInforFlowMapper {
    int deleteByPrimaryKey(String id);

    int insert(BInforFlow record);

    int insertSelective(BInforFlow record);

    BInforFlow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BInforFlow record);

    int updateByPrimaryKey(BInforFlow record);

//    List<IntelligenceListResponseVO> queryByIntelligenceListRequetParam(IntelligenceListRequetParam intelligenceListRequetParam);
}