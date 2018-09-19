package com.mapper.suspect;


import com.entity.suspect.EtWarning;
import com.mapper.BaseDao;
import com.vo.warning.PageVoWarning;
import com.vo.suspect.WarningListItemVo;

import java.util.List;
import java.util.Map;

public interface EtWarningMapper extends BaseDao {
    int deleteByPrimaryKey(String id);

    int insert(EtWarning record);

    int insertSelective(EtWarning record);

    EtWarning selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtWarning record);

    int updateByPrimaryKeyWithBLOBs(EtWarning record);

    int updateByPrimaryKey(EtWarning record);

    /**
     * 依照编号 查询唯一结果
     *
     * @param no
     * @return
     */
    EtWarning selectByNo(String no);

    /**
     * 依照编号 删除唯一记录
     *
     * @param no
     * @return
     */
    int deleteByNo(String no);

    /**
     * 依照分页条件 查询记录
     *
     * @param pageVO
     * @return
     */
    List<WarningListItemVo> selectByPage(PageVoWarning pageVO);

    /**
     * 按照条件 统计查询结果总数
     *
     * @param pageVO
     * @return
     */
    long countTotal(PageVoWarning pageVO);

    /**
     * 依照分页条件 查询带有详细内容的记录
     *
     * @param pageVO
     * @return
     */
    List<WarningListItemVo> selectWithDetailByPage(PageVoWarning pageVO);

    /**
     * 检查一身份证是否在案件或警情中预警过
     *
     * @param map relationNo bCtrlIdcardNum
     * @return
     */
    Long selectCountByRelaNoAndIdCard(Map<String, String> map);
}