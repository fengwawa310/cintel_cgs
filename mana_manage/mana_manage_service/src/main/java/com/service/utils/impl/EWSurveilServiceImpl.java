package com.service.utils.impl;

import java.util.*;

import javax.annotation.Resource;

import com.common.utils.StringHelp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.IDGenerator;
import com.entity.suspect.EtCtrl;
import com.entity.suspect.EtWarning;
import com.mapper.suspect.EtCtrlMapper;
import com.mapper.suspect.EtWarningMapper;
import com.service.utils.EWSurveilService;


@Service("ewSurveilService")
@Transactional
public class EWSurveilServiceImpl implements EWSurveilService {

//	@Resource
//	private EtCaseMapper etCaseMapper;

    @Resource
    private EtCtrlMapper etCtrlMapper;

    @Resource
    private EtWarningMapper etWarningMapper;

    @Override
    public EtCtrl selectCtrlIdByICN(String bCtrlIdcardNum) {
        return etCtrlMapper.selectCtrlIdByICN(bCtrlIdcardNum);
    }

    @Override
    public List<String> insertIntoWarning(List<EtWarning> etWList) {
        List<String> warningNos = new ArrayList<>();
        for (EtWarning etW : etWList) {
            String bCtrlIdcardNum = etW.getbCtrlIdcardNum();
            String relationNo = etW.getRelationNo();
            int warningClass = etW.getWarningClass();
            if (StringHelp.isEmpty(bCtrlIdcardNum) || StringHelp.isEmpty(relationNo)) {
                continue;
            }
            if (warningClass == 0) {
                continue;
            }
            Map<String,String> query = new HashMap<>();
            query.put("relationNo",relationNo);
            query.put("bCtrlIdcardNum",bCtrlIdcardNum);
            long count = etWarningMapper.selectCountByRelaNoAndIdCard(query);
            if(count > 0)
            {
                continue; // 该预警已经写入过了
                // 同一个关注人员 在一个案件或警情中 只产生一条预警信息
            }
            EtCtrl etCtrl = null;
            etCtrl = selectCtrlIdByICN(bCtrlIdcardNum);// 依据身份证，在人员关注池中查询匹配数据 并关联出关注任务数据
            if (etCtrl == null || StringHelp.isEmpty(etCtrl.getCtrlId()) || StringHelp.isEmpty(etCtrl.getbCtrlPCode())) {
                // 关注任务ID 和 被关注人员身份证不能为空
                continue;
            }
            etW.setId(IDGenerator.getorderNo());
            etW.setWarningId(IDGenerator.getorderNo());
            etW.setCtrlTaskId(etCtrl.getCtrlId());
            etW.setbCtrlPCode(etCtrl.getbCtrlPCode());
            etW.setWarningTime(new Date());
            etW.setCreatTime(new Date());
            etW.setModifyTime(new Date());
            int insertResult = etWarningMapper.insert(etW);
            if (insertResult > 0) {
                warningNos.add(etW.getWarningId());
            }
        }
        return warningNos;
    }
}
