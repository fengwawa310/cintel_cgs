package com.service.suspect.impl;

import com.common.utils.StringHelp;
import com.common.utils.TimeUtil;
import com.entity.DicCommon;
import com.entity.suspect.EtWarning;
import com.mapper.communal.DicCommonMapper;
import com.mapper.suspect.EtWarningMapper;
import com.vo.warning.PageVoWarning;
import com.service.suspect.WarningServiceInfc;
import com.vo.suspect.WarningListItemVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Auri on 2018/1/6.
 * Desc:
 */
@Service
@Transactional
public class WarningServiceImpl implements WarningServiceInfc {

    @Resource
    private EtWarningMapper etWarningMapper;

    @Resource
    private DicCommonMapper dicCommonMapper;

    @Override
    public EtWarning find(String id) {
        if (StringHelp.isEmpty(id)) {
            return null;
        }
        return etWarningMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EtWarning> findAll() {
        return null;
    }

    @Override
    public List<EtWarning> findList(String... strings) {
        return null;
    }

    @Override
    public long count() {
//        return etWarningMapper.countTotal();
        return 0;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public int save(EtWarning entity) {
        if (entity == null) {
            return 0;
        }
        entity.setWarningTime(new Date());
        entity.setCreatTime(new Date());
        entity.setModifyTime(new Date());
        return etWarningMapper.insert(entity);
    }

    @Override
    public int update(EtWarning entity) {
        if (entity == null || StringHelp.isEmpty(entity.getWarningId())) {
            return 0;
        }
        EtWarning et = etWarningMapper.selectByNo(entity.getWarningId());
        String ID = et.getId();
        entity.setId(ID);
        return etWarningMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int update(EtWarning entity, String... ignoreProperties) {
        return 0;
    }

    @Override
    public int delete(String s) {
        return 0;
    }

    @Override
    public int delete(String... strings) {
        return 0;
    }

    @Override
    public int delete(EtWarning entity) {
        return 0;
    }

    @Override
    public int deleteByNo(String no) {
        if (StringHelp.isEmpty(no)) {
            return 0;
        }
        return etWarningMapper.deleteByNo(no);
    }

    @Override
    public List<WarningListItemVo> findByPage(PageVoWarning pageVO) {
        List<WarningListItemVo> items = null;
        if (pageVO.getNeedDetail()) {
            items = etWarningMapper.selectWithDetailByPage(pageVO);
        } else {
            items = etWarningMapper.selectByPage(pageVO);
        }
        if(items == null || items.isEmpty())
        {
            return new ArrayList<WarningListItemVo>();
        }
        for (WarningListItemVo item : items) {
            Date ctrlTime = item.getCtrlTime();
            Date warningTime = item.getWarningTime();
            Integer warningClass = item.getWarningClass();
            Integer gender = item.getbCtrlGender();
            if (ctrlTime != null) {
                String timeStr = TimeUtil.formatDateToStr(ctrlTime, null);
                item.setCtrlTimeStr(timeStr);
            }
            if (warningTime != null) {
                String timeStr = TimeUtil.formatDateToStr(warningTime, null);
                item.setWarningTimeStr(timeStr);
            }
            if (warningClass != null) {
                DicCommon dic = dicCommonMapper.selectByDicCode("" + warningClass);
                String warningClassStr = dic == null ? "" : dic.getDicValue();
                item.setWarningClassStr(warningClassStr);
            }
            if (gender != null) {
                DicCommon dic = dicCommonMapper.selectByDicCode("" + gender);
                String genderStr = dic == null ? "" : dic.getDicValue();
                item.setbCtrlGenderStr(genderStr);
            }
        }
        return items;
    }

    @Override
    public long countTotal(PageVoWarning pageVO) {
        return etWarningMapper.countTotal(pageVO);
    }

    @Override
    public EtWarning findByNo(String No) {
        if (StringHelp.isEmpty(No)) {
            return null;
        }
        return etWarningMapper.selectByNo(No);
    }
}
