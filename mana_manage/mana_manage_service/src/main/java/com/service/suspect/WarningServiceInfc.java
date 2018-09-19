package com.service.suspect;

import com.entity.suspect.EtWarning;
import com.vo.warning.PageVoWarning;
import com.service.BaseService;
import com.vo.suspect.WarningListItemVo;

import java.util.List;

/**
 * Created by Auri on 2018/1/6.
 * Desc:
 */
public interface WarningServiceInfc extends BaseService<EtWarning, String> {

    /**
     * 依据编号删除
     * @param No
     * @return
     */
    int deleteByNo(String No);

    /**
     * 分页查询
     * @param pageVO
     * @return
     */
    List<WarningListItemVo> findByPage(PageVoWarning pageVO);

    long countTotal(PageVoWarning pageVO);

    /**
     * 按编号查询
     * @param no
     * @return
     */
    EtWarning findByNo(String no);


}
