package com.mapper.suspect;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.suspect.EtCtrl;
import com.vo.ctrl.PageVoCtrl;

public interface EtCtrlMapper {
    int deleteByPrimaryKey(String id);
    
    int deleteByIDCardNum(String idCardNum);

    int insert(EtCtrl record);

    int insertSelective(EtCtrl record);

    EtCtrl selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtCtrl record);

    int updateByPrimaryKey(EtCtrl record);

    /**
     * 按照条件 统计查询结果总数
     *
     * @param pageVO
     * @return
     */
    long countTotal(PageVoCtrl pageVO);

    /**
     * 依据布控任务编号 查询
     *
     * @param No
     * @return
     */
    EtCtrl selectByNo(String No);

    /**
     * 依据布控任务编号删除
     *
     * @param No
     * @return
     */
    int deleteByNo(String No);
    /**
     * 布控预警前期查询是否需要插入 预警表条件
     */
//	EtCtrl selectCtrlIdByCaseId(@Param("id")String id);
	EtCtrl selectCtrlIdByICN(@Param("bCtrlIdcardNum")String bCtrlIdcardNum);

    /**
     * 依照分页条件 查询记录
     *
     * @param pageVO
     * @return
     */
    List<EtCtrl> selectByPage(PageVoCtrl pageVO);

    /**
     * 通过布控任务ID查询
     * @param ctrlId
     * @return
     */
    EtCtrl findCtrlById(String ctrlId);
}