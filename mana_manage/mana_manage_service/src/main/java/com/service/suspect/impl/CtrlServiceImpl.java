package com.service.suspect.impl;

import com.common.utils.StringHelp;
import com.entity.DicCommon;
import com.entity.suspect.ApCtrlKey;
import com.entity.suspect.EtCtrl;
import com.mapper.communal.DicCommonMapper;
import com.mapper.suspect.EtCtrlMapper;
import com.mapper.utils.ApCtrlKeyMapper;
import com.vo.ctrl.PageVoCtrl;
import com.service.suspect.CtrlService;
import com.vo.suspect.CtrlDetailVo;
import com.vo.suspect.CtrlListItemVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2018/1/5.
 */
@Service
@Transactional
public class CtrlServiceImpl implements CtrlService {

    @Resource
    private EtCtrlMapper etCtrlMapper;

    @Resource
    private ApCtrlKeyMapper apCtrlKeyMapper;

    @Resource
    private DicCommonMapper dicCommonMapper;

    @Override
    public EtCtrl find(String id) {
        if (StringHelp.isEmpty(id)) {
            return null;
        }
        return etCtrlMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EtCtrl> findAll() {
//        return etCtrlMapper.selectAll();
        return null;
    }

    @Override
    public List<EtCtrl> findList(String... strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    @Deprecated
    public int save(EtCtrl entity) {
//        if (entity == null || StringHelp.isEmpty(entity.getbCtrlIdcardNum())) {
//            return 0;// 如果没有任务实体 或 任务中没有身份证
//        }
//
//        return etCtrlMapper.insert(entity);
        return 0;
    }

    @Override
    public int update(EtCtrl entity) {
        if (entity == null || StringHelp.isEmpty(entity.getCtrlId())) {
            return 0;
        }
        EtCtrl tempEt = etCtrlMapper.selectByNo(entity.getCtrlId());
        if (tempEt == null) {
            return 0;
        }
        String primaryKey = tempEt.getId();
        entity.setId(primaryKey);
        return etCtrlMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int update(EtCtrl entity, String... ignoreProperties) {
        return 0;
    }

    @Override
    public int delete(String id) {
        if (StringHelp.isEmpty(id)) {
            return 0;
        }
        return etCtrlMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(String... strings) {
        return 0;
    }

    @Override
    public int delete(EtCtrl entity) {
        return 0;
    }

    @Override
    public int addCtrlAndCtrlKey(EtCtrl entity) {
        // 生成关键词对象
        ApCtrlKey apCtrlKey = new ApCtrlKey();
        apCtrlKey.setCtrlId(entity.getCtrlId());
        apCtrlKey.setIdcardNum(entity.getbCtrlIdcardNum());
        apCtrlKey.setCreatTime(new Date());
        entity.setCreatTime(new Date());
        entity.setModifyTime(new Date());
        int num = 0;
        try {
            apCtrlKeyMapper.insert(apCtrlKey);
            num = etCtrlMapper.insert(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    @Override
    public int cancelCtrlAndCtrlKeyByIdCardNum(String idCardNum) {
        // 先清除关键词对象
        int num = 0;
        try {
        	if(StringUtils.isNotBlank(idCardNum)){
        		num = etCtrlMapper.deleteByIDCardNum(idCardNum);
        		apCtrlKeyMapper.deleteByIDCardNum(idCardNum);
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }
    
    @Override
    public int cancelCtrlAndCtrlKey(String ctrlNo) {

        // 先清除关键词对象
        int num = 0;
        try {
            apCtrlKeyMapper.deleteByPrimaryKey(ctrlNo);
            num = etCtrlMapper.deleteByNo(ctrlNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<CtrlListItemVo> findByPage(PageVoCtrl pageVoCtrl) {
        List<EtCtrl> etCtrls = etCtrlMapper.selectByPage(pageVoCtrl);
        if (etCtrls == null || etCtrls.isEmpty()) {
            return new ArrayList<CtrlListItemVo>();
        }
        List<CtrlListItemVo> items = new ArrayList<>();
        for (EtCtrl etCtrl : etCtrls) {
            CtrlListItemVo ctrlListItemVo = new CtrlListItemVo();
            BeanUtils.copyProperties(etCtrl, ctrlListItemVo);
            Integer gender = etCtrl.getbCtrlGender();
            if (gender != null) {
                DicCommon dic = dicCommonMapper.selectByDicCode("" + gender);
                String genderStr = dic == null ? "" : dic.getDicValue();
                ctrlListItemVo.setbCtrlGenderStr(genderStr);
            }
            items.add(ctrlListItemVo);
        }
        return items;
    }

    @Override
    public long countTotal(PageVoCtrl pageVoCtrl) {
        return etCtrlMapper.countTotal(pageVoCtrl);
    }


    @Override
    public EtCtrl findByNo(String no) {
        if (StringHelp.isEmpty(no)) {
            return null;
        }
        return etCtrlMapper.selectByNo(no);
    }

    @Override
    public CtrlDetailVo findDetailById(String id) {
        EtCtrl etCtrl = etCtrlMapper.findCtrlById(id);
        CtrlDetailVo ctrlDetailVo = new CtrlDetailVo();
        BeanUtils.copyProperties(etCtrl, ctrlDetailVo);
        Integer gender = etCtrl.getbCtrlGender();
        if (gender != null) {
            DicCommon dic = dicCommonMapper.selectByDicCode("" + gender);
            String genderStr = dic == null ? "" : dic.getDicValue();
            ctrlDetailVo.setbCtrlGenderStr(genderStr);
        }
        return ctrlDetailVo;
    }

}
