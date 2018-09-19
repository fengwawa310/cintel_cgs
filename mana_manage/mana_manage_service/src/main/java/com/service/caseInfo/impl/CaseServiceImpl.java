package com.service.caseInfo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.common.consts.Const;
import com.common.utils.ElasticSearchUtils;
import com.common.utils.GsonUtils;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.common.utils.TimeUtil;
import com.entity.caseInfo.ApScarela;
import com.entity.caseInfo.ApStaff;
import com.entity.caseInfo.CaseSeries;
import com.entity.caseInfo.EtCase;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.entity.message.MessageList;
import com.entity.suspect.EtSuspect;
import com.entity.suspect.EtWarning;
import com.entity.suspect.RlSuspectCase;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.caseInfo.ApStaffMapper;
import com.mapper.caseInfo.EtCaseMapper;
import com.mapper.suspect.EtSuspectMapper;
import com.mapper.utils.EtSuspectIntegralMapper;
import com.mapper.utils.EtUnitIntegralMapper;
import com.mapper.utils.PublicEtSuspectMapper;
import com.mapper.utils.RlSuspectCaseMapper;
import com.service.caseInfo.CaseService;
import com.service.communal.MessageListService;
import com.service.utils.EWSurveilService;
import com.service.utils.IntegralCalculationService;

/**
 * Created by weipc on 2018/1/2.
 */
@Service
@Transactional
public class CaseServiceImpl implements CaseService {

    private static final Logger logger = LoggerFactory.getLogger(CaseServiceImpl.class);
    //操作案件
    @Resource
    private EtCaseMapper etCaseMapper;

    //操作嫌疑人和受害人
    @Resource
    private ApStaffMapper apStaffMapper;

    //    //操作人员积分
    @Resource
    private IntegralCalculationService integralCalculationService;

    //操作人员积分
    @Resource
    private EtSuspectIntegralMapper etSuspectIntegralMapper;

    //操作单位积分
    @Resource
    private EtUnitIntegralMapper etUnitIntegralMapper;

    @Resource
    private EWSurveilService eWSurveilService;
    //公共重点人员与嫌疑人身份证号匹配
    @Resource
    private PublicEtSuspectMapper publicEtSuspectMapper;
    //公共重点人员与嫌疑人身份证号关系表
    @Resource
    private RlSuspectCaseMapper rlSuspectCaseMapper;
    @Resource
    private EtSuspectMapper etSuspectMapper;
    @Autowired
    private MessageListService messageListService;

    @Override
    public void save(EtCase etCase, String apStaffs) throws Exception {
        List<ApStaff> apStaffList = new ArrayList<>();
        List<EtWarning> etWList = new ArrayList<>();
        ApScarela apscarela = new ApScarela();
        try {
            int insert = etCaseMapper.insert(etCase);
            //2、使用JSONArray
            int length = apStaffs.length();
            if (length > 49) {//传来数据不是默认数据时进行添加
                List<Object> jsonArray = (List<Object>) JSONArray.parse(apStaffs);
                Date date = new Date();
                for (int i = 0; i < jsonArray.size(); i++) {
                    Map<String, Object> paramMap = (Map<String, Object>) jsonArray.get(i);
                    ApStaff apStaff = GsonUtils.toBean(paramMap.toString(), ApStaff.class);
                    if (apStaff.getIdcardNum() != null && !"".equals(apStaff.getIdcardNum())) {
                        //主键
                        apStaff.setId(IDGenerator.getorderNo());
                        //人员编号
                        apStaff.setStaffId(IDGenerator.getorderNo());
                        //关联案件ID
//                        apStaff.setRelationNo(etCase.getId());
                        //录入人编码
                        apStaff.setEntry(etCase.getInputerCode());
                        //录入单位编码
                        apStaff.setEntryUnit(etCase.getInputUnitCode());
                        //数据来源2102:手动录入
                        apStaff.setSourceType(2102);
                        //系统创建时间
                        apStaff.setCreatTime(date);
                        //系统修改时间
                        apStaff.setModifyTime(date);
                        if (apStaff.getSuspectClass() == 1000) {
                            EtWarning etW = new EtWarning();
                            etW.setRelationNo(etCase.getId());
                            etW.setbCtrlIdcardNum(apStaff.getIdcardNum());
                            etW.setWarningClass(1700);// 表示该预警信息来源于案件
                            // 预警详情格式 ： 来源数据编号+触发身份证号
                            etW.setWarningDetal("案件编号为：" + etCase.getCaseNo() + "案件录入嫌疑人身份证号" + apStaff.getIdcardNum());
                            apStaffList.add(apStaff);
                            etWList.add(etW);
                        }
                        int xianyiren = apStaffMapper.insert(apStaff);
                        try { //嫌疑人录入后  不影响其他流程  加try catch finally
                            if(xianyiren == 1){
                                apscarela.setId(IDGenerator.getorderNo());// 关联表主键id
                                apscarela.setCaseNo(etCase.getId());//关联表中 案件id
                                apscarela.setStaffId(apStaff.getStaffId()); //嫌疑人的StaffId
                                int jkl = etCaseMapper.insertToApScarela(apscarela);
                                if(jkl == 1){
                                    System.out.println("关联关系表入库成功");
                                }else{
                                    System.out.println("关联关系表入库失败");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("关联关系入库失败");
                        }
                        if (apStaff.getSuspectClass() == 1000)
                        {
                            //把新录入的案件插入案件重点人员关系表
                            RlSuspectCase rlSuspectCase=new RlSuspectCase();
                            rlSuspectCase.setCaseId(etCase.getId());
                            Map<String,String> map=new HashMap<String, String>();
                            map.put("idCardNum", apStaff.getIdcardNum());
                            List<EtSuspect> listAllForOperatorForALL = etSuspectMapper.listAllForOperatorForALL(map);
                            for (int j = 0; j < listAllForOperatorForALL.size(); j++)
                            {
                                rlSuspectCase.setId(IDGenerator.getorderNo());
                                rlSuspectCase.setSuspectId(listAllForOperatorForALL.get(j).getSuspectId());
                                rlSuspectCase.setRelation(0);
                                rlSuspectCase.setOptPCode(etCase.getInputerCode());
                                rlSuspectCase.setOptPName(etCase.getInputerName());
                                rlSuspectCaseMapper.insert(rlSuspectCase);
                            }
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //把新录入的案件中嫌疑人信息有关的重点人员关系插入到关系表中
            Date date =null;
            EtSuspect etSuspect = new EtSuspect();
            RlSuspectCase rlSuspectCase = new RlSuspectCase();
            for (int i = 0; i < apStaffList.size(); i++) {
                String idcardNum = apStaffList.get(i).getIdcardNum();
                if (idcardNum!=null&&"".equals(idcardNum)) {
                    etSuspect.setIdcardNum(idcardNum);
                    List<EtSuspect> findAll = publicEtSuspectMapper.findAll(etSuspect);
                    if (findAll.size()>0) {
                        for (int j = 0; j < findAll.size(); j++) {
                            date = new Date();
                            rlSuspectCase.setId(IDGenerator.getorderNo());
                            rlSuspectCase.setSuspectId(findAll.get(i).getSuspectId());
                            rlSuspectCase.setCaseId(etCase.getCaseNo());
                            //系统创建时间
                            rlSuspectCase.setCreateTime(date);
                            //系统修改时间
                            rlSuspectCase.setModifyTime(date);
                            rlSuspectCaseMapper.insert(rlSuspectCase);
                        }
                    }
                }
            }

            //单位积分
            EtUnitIntegral etUnitIntegral = integralCalculationService.integralCalcOfUnit(Const.INTEGRAL_UNIT_CASE, etCase.getInputUnitCode(), etCase.getInputUnitName());
            if (etUnitIntegral != null) {
                etUnitIntegralMapper.mergeUnitIntegral(etUnitIntegral);
            }
            //录入人员积分
            List<EtSuspectIntegral> etSuspectIntegrals = integralCalculationService.integralCalcOfSuspectList(apStaffList);
            if (etSuspectIntegrals != null && etSuspectIntegrals.size() > 0) {
                etSuspectIntegralMapper.mergeSuspectIntegral(etSuspectIntegrals);
            }
        } catch (Exception e) {
            logger.info("案件录入积分异常：" + e);
            e.printStackTrace();
        }
        try {
            //案件关注重点人员  录入新的案件嫌疑人为重点人员  ？？？？
            anJianBukong(etWList,etCase.getUserId());
        } catch (Exception e) {
            logger.info("案件布控预警异常：" + e);
            e.printStackTrace();
        }

    }

    /**
     * 将情报生成过程中产生的预警信息入库，同时对每一条预警匹配创建一条系统通知消息信息并入库。
     *
     * @param etWList
     */
    private void anJianBukong(List<EtWarning> etWList,String userId) {
        List<String> warningNos = eWSurveilService.insertIntoWarning(etWList);
        logger.info("案件布控预警接口返回的，已成功创建的预警信息编号：" + warningNos.toString());
        if (warningNos == null || warningNos.isEmpty()) {
            return;
        }
        int num = 0;
        for (int i = 0; i < etWList.size(); i++) {
            String createTimeStr = TimeUtil.formatDateToStr(new Date(), null);
            EtWarning etW = etWList.get(i);
            MessageList messageList = new MessageList();
            messageList.setId(IDGenerator.getorderNo());
            messageList.setTitle("有新预警产生，编号<" + etW.getWarningId() + ">，请进行处理");
            messageList.setReceiveUnitCode(etW.getManaUnitCode());
            messageList.setReceiveUnitName(etW.getManaPCode());
            messageList.setReceiverCode(userId);
            messageList.setRelationNo(etW.getWarningId());
            messageList.setRelationClass(Const.XIAOXI_QINGBAO_YUJING);
            messageList.setIsSend(0);
            messageList.setIsRead(0);
            messageList.setCreatTime(createTimeStr);
            num += messageListService.add(messageList);
        }
        logger.info("系统通知消息接口返回的，已成功创建的消息数目：" + num);
    }

    @Override
    public void update(EtCase etCase) throws Exception {
        etCaseMapper.updateByPrimaryKey(etCase);
    }

    @Override
    public void update(EtCase etCase, String apStaffs, String apStaffIds) throws Exception {
        List<EtWarning> etWList = new ArrayList<>();
        etCaseMapper.updateCase(etCase);
        //2、使用JSONArray
        ApScarela apscarela = new ApScarela();
        int length = apStaffs.length();
        if (length > 49) {//传来数据不是默认数据时进行添加
            List<Object> jsonArray = (List<Object>) JSONArray.parse(apStaffs);
            Date date = new Date();
            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, Object> paramMap = (Map<String, Object>) jsonArray.get(i);
                if (paramMap.size() > 0) {
                    ApStaff apStaff = GsonUtils.toBean(paramMap.toString(), ApStaff.class);
                    if (apStaff.getId() == null || "".equals(apStaff.getId())) {
                        //主键
                        apStaff.setId(IDGenerator.getorderNo());
                        //人员编号
                        apStaff.setStaffId(IDGenerator.getorderNo());
                        //关联案件ID
//                        apStaff.setRelationNo(etCase.getId());
                        //录入人编码
                        apStaff.setEntry(etCase.getInputerCode());
                        //录入单位编码
                        apStaff.setEntryUnit(etCase.getInputUnitCode());
                        //数据来源2102:手动录入
                        apStaff.setSourceType(2102);
                        //系统创建时间
                        apStaff.setCreatTime(date);
                        //系统修改时间
                        apStaff.setModifyTime(date);
                        int xianyiren = apStaffMapper.insert(apStaff);
                        if (apStaff.getSuspectClass() == 1000)
                        {
                            //把新录入的案件插入案件重点人员关系表
                            RlSuspectCase rlSuspectCase=new RlSuspectCase();
                            rlSuspectCase.setCaseId(etCase.getId());
                            Map<String,String> map=new HashMap<String, String>();
                            map.put("idCardNum", apStaff.getIdcardNum());
                            List<EtSuspect> listAllForOperatorForALL = etSuspectMapper.listAllForOperatorForALL(map);
                            for (int j = 0; j < listAllForOperatorForALL.size(); j++)
                            {
                                rlSuspectCase.setId(IDGenerator.getorderNo());
                                rlSuspectCase.setSuspectId(listAllForOperatorForALL.get(j).getSuspectId());
                                rlSuspectCase.setRelation(0);
                                rlSuspectCase.setOptPCode(etCase.getInputerCode());
                                rlSuspectCase.setOptPName(etCase.getInputerName());
                                rlSuspectCaseMapper.insert(rlSuspectCase);
                            }
                        }

                        try { //嫌疑人录入后  不影响其他流程  加try catch finally
                            if(xianyiren == 1){
                                apscarela.setId(IDGenerator.getorderNo());// 关联表主键id
                                apscarela.setCaseNo(etCase.getId());//关联表中 案件id
                                apscarela.setStaffId(apStaff.getStaffId()); //嫌疑人的StaffId
                                int jkl = etCaseMapper.insertToApScarela(apscarela);
                                if(jkl == 1){
                                    System.out.println("关联关系表入库成功");
                                }else{
                                    System.out.println("关联关系表入库失败");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("关联关系入库失败");
                        }

                        //受害人要去布控预警
                        if (apStaff.getSuspectClass() == 1000) {
                            EtWarning etW = new EtWarning();
                            etW.setRelationNo(etCase.getId());
                            etW.setbCtrlIdcardNum(apStaff.getIdcardNum());
                            etW.setWarningClass(1700);
                            etW.setWarningClass(1700);
                            // 预警详情格式 ： 来源数据编号+触发身份证号
                            etW.setWarningDetal("案件编号为：" + etCase.getCaseNo() + "案件录入嫌疑人身份证号" + apStaff.getIdcardNum());
                            etWList.add(etW);
                        }
                    } else {
                        //关联案件ID
                        apStaff.setRelationNo(etCase.getId());
                        //系统修改时间
                        apStaff.setModifyTime(date);
                        int xianyiren = apStaffMapper.update(apStaff);

                    }
                }
            }
        }
        if(!"".equals(apStaffIds)){
            String[] split = apStaffIds.split(",");
            for (String id : split) {
                apStaffMapper.delete(id);
                etCaseMapper.deleteApScarela(id);
                //删除es
                ElasticSearchUtils.delete("ap_staff","cgs",id);
            }
        }
        anJianBukong(etWList,etCase.getUserId());
    }

    @Override
    public void delete(String id)  {
        etCaseMapper.deleteByPrimaryKey(id);
        //删除人员涉案涉警情况表中相关案件信息by caseID
        etCaseMapper.deleteApAcarelaByCaseId(id);
        //删除重点人员与案件关系表中相关案件信息by caseID
        rlSuspectCaseMapper.deleteByCaseId(id);
        //删除es
        try {
            ElasticSearchUtils.delete("et_case","cgs",id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageHelpVO findCase(PageVO pageVO, EtCase etCase) {
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<EtCase> list;
//        List<String> ids =new ArrayList<>();
//        ids.add("161E042161AF00");
//        ids.add("161E0605791F04");
//        ids.add("161E0659D9FF06");
//        list = etCaseMapper.findCaseForSearch(ids);
//        System.out.println(list);
        if ((etCase.getSuspectName() != null && !"".equals(etCase.getSuspectName()))
                ||(etCase.getSuspectIDCardNo() != null && !"".equals(etCase.getSuspectIDCardNo()))
                ||(etCase.getSuspectPhoneNum() != null && !"".equals(etCase.getSuspectPhoneNum()))
                ||(etCase.getManaUnitCode() != null && !"".equals(etCase.getManaUnitCode()))
                ||(etCase.getRelation()!=null&&!"".equals(etCase.getRelation()))
                ) {//通过人员查询案件列表
            if (etCase.getRelation()==1)
            {
                list = etCaseMapper.findCaseAndApStaffOpen(etCase);
                System.out.println(list);
            }else {
                list = etCaseMapper.findCaseAndApStaffClose(etCase);
                System.out.println(list);
            }

        } else {//查询案件列表
            list = etCaseMapper.findCase(etCase);
        }
        PageInfo<EtCase> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<EtCase>(total, list);
        return pageHelpVO;
    }

    @Override
    public Map<String, Object> findCaseById(Map<String, Object> map) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        EtCase caseById = etCaseMapper.findCaseById(map);
        if(caseById != null){
            List<ApStaff> apStaffs = apStaffMapper.selectApStaffList(caseById.getId());
            resultMap.put("etCase", caseById);
            resultMap.put("apStaffs", apStaffs);
        }
        return resultMap;
    }

    /**
     * 案件串并
     *
     * @param pageVO
     * @param caseSeries
     * @return
     */
    @Override
    public PageHelpVO caseSeries(PageVO pageVO, CaseSeries caseSeries) {
        //一级级串并
        if (Const.CASE_SERIES_CATEGORY.equals(caseSeries.getOneLevel())) {//案件类别
            caseSeries.setOneLevel("CASE_CLASS");
            caseSeries.setMode(Const.CASE_SERIES_CATEGORY);
            if (!"".equals(caseSeries.getOneLevelValue())) {
                caseSeries.setOneLevelValue("AND c.CASE_CLASS='" + caseSeries.getOneLevelValue() + "'");
            }
        } else if (Const.CASE_SERIES_SUSPICION.equals(caseSeries.getOneLevel())) {//嫌疑人
            caseSeries.setOneLevel("IDCARD_NUM");
            caseSeries.setMode(Const.CASE_SERIES_SUSPICION);
            if (!"".equals(caseSeries.getOneLevelValue())) {
                caseSeries.setOneLevelValue("AND s.SUSPECT_CLASS='1000' AND s.IDCARD_NUM like'" + caseSeries.getOneLevelValue() + "%'");
            } else {
                caseSeries.setOneLevelValue("AND s.SUSPECT_CLASS='1000'");
            }
        } else if (Const.CASE_SERIES_VICTIM.equals(caseSeries.getOneLevel())) {//受害人
            caseSeries.setOneLevel("IDCARD_NUM");
            caseSeries.setMode(Const.CASE_SERIES_VICTIM);
            if (!"".equals(caseSeries.getOneLevelValue())) {
                caseSeries.setOneLevelValue("AND s.SUSPECT_CLASS='1001' AND s.IDCARD_NUM like'" + caseSeries.getOneLevelValue() + "%'");
            } else {
                caseSeries.setOneLevelValue("AND s.SUSPECT_CLASS='1001'");
            }
        }
        //二级串并
        if (Const.CASE_SERIES_CATEGORY.equals(caseSeries.getTwoLevel())) {//案件类别
            caseSeries.setTwoLevel("CASE_CLASS");
            caseSeries.setMode(Const.CASE_SERIES_CATEGORY);
            if (!"".equals(caseSeries.getTwoLevelValue())) {
                caseSeries.setTwoLevelValue("AND c.CASE_CLASS='" + caseSeries.getTwoLevelValue() + "'");
            }
        } else if (Const.CASE_SERIES_SUSPICION.equals(caseSeries.getTwoLevel())) {//嫌疑人
            caseSeries.setTwoLevel("IDCARD_NUM");
            caseSeries.setMode(Const.CASE_SERIES_SUSPICION);
            if (!"".equals(caseSeries.getTwoLevelValue())) {
                caseSeries.setTwoLevelValue("AND s.SUSPECT_CLASS='1000' AND s.IDCARD_NUM like'" + caseSeries.getTwoLevelValue() + "%'");
            } else {
                caseSeries.setTwoLevelValue("AND s.SUSPECT_CLASS='1000'");
            }
        } else if (Const.CASE_SERIES_VICTIM.equals(caseSeries.getTwoLevel())) {//受害人
            caseSeries.setTwoLevel("IDCARD_NUM");
            caseSeries.setMode(Const.CASE_SERIES_VICTIM);
            if (!"".equals(caseSeries.getTwoLevelValue())) {
                caseSeries.setTwoLevelValue("AND s.SUSPECT_CLASS='1001' AND s.IDCARD_NUM like'" + caseSeries.getTwoLevelValue() + "%'");
            } else {
                caseSeries.setTwoLevelValue("AND s.SUSPECT_CLASS='1001'");
            }
        }

        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<CaseSeries> list = etCaseMapper.caseSeries(caseSeries);
        PageInfo<CaseSeries> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<CaseSeries>(total, list);
        return pageHelpVO;
    }

    @Override
    public EtCase getCaseById(String o) {
        return etCaseMapper.getCaseById(o);
    }


    /**
     * @Author: sky
     * @Description: 根据人员身份证号查询相关涉案人员
     * @Date: 上午11:29 2018/4/20
     * @param: apStaff
     */
    @Override
    public List<ApStaff> findInvolveByIdcard(ApStaff apStaff) {
        return apStaffMapper.findInvolveByIdcard(apStaff);
    }

    /**
     * @Author: sky
     * @Description: 根据staffId查找涉案涉警编号列表
     * @Date: 上午11:48 2018/4/20
     * @param: staffId
     */
    @Override
    public List<String> findCaseNoByStaffId(String staffId) {
        return apStaffMapper.findCaseNoByStaffId(staffId);
    }

    @Override
    public int getCaseNum() {
        return etCaseMapper.getCaseNum();
    }


}
