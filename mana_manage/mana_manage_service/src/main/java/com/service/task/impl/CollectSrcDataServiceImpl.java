package com.service.task.impl;

import com.common.utils.*;
import com.entity.alarm.EtAlarm;
import com.entity.caseInfo.ApScarela;
import com.entity.caseInfo.ApStaff;
import com.entity.caseInfo.EtCase;
import com.entity.task.CollectRecord;
import com.entity.task.SysTaskLog;
import com.mapper.alarm.EtAlarmMapper;
import com.mapper.caseInfo.ApScarelaMapper;
import com.mapper.caseInfo.ApStaffMapper;
import com.mapper.caseInfo.EtCaseMapper;
import com.mapper.sourceData.SrcCaseEntityMapper;
import com.mapper.task.log.SysTaskLogMapper;
import com.mapper.task.sourceData.*;
import com.service.task.CollectSrcDataServiceInfc;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author QiuCheng
 * @create 2017/12/8.
 */
@Service
@Transactional
public class CollectSrcDataServiceImpl implements CollectSrcDataServiceInfc {

    @Resource
    private EtCaseMapper etCaseMapper;
    @Resource
    private EtAlarmMapper etAlarmMapper;

    @Resource
    private JZCaseEntityMapper jzCaseEntityMapper;
    @Resource
    private ApScarelaMapper apScarelaMapper;
    @Resource
    private ApStaffMapper apStaffMapper;
    @Resource
    private SysTaskLogMapper sysTaskLogMapper;

    @Override
    public List<EtCase> pageCollectJZAJData(Map<String, Object> map) {
        return jzCaseEntityMapper.findPageAJData(map);
    }

    @Override
    public CollectRecord writeOrUpdateEtCase(List<EtCase> srcAjs,List<EtCase> needToAdd,List<EtCase> needToUpdate) {
        CollectRecord collectRecord = new CollectRecord();
        if (srcAjs == null || srcAjs.isEmpty()) {
            return collectRecord;
        }
//        List<EtCase> needToAdd = new ArrayList<>();
//        List<EtCase> needToUpdate = new ArrayList<>();
        // 判断数据是否已经写入过了
        for (int i = 0; i < srcAjs.size(); i++) {
            EtCase srcData = srcAjs.get(i);
            String ajbh = srcData.getCaseNo();
            if (ajbh == null || ajbh.length() == 0) {
                LogUtils.error("CT 该" + EtCase.class.getName() + "数据 没有AJBH ! 系统编号：" + srcData.getId());
                continue;
            }
            Map<String, Object> param = new HashMap<>();
            param.put("caseNo", ajbh);
            EtCase tempData = etCaseMapper.findCaseById(param);
            if (tempData == null) {
                needToAdd.add(srcData);
            } else {
                srcData.setId(tempData.getId());//一定要有主键
                needToUpdate.add(srcData);
            }
        }
        // 将数据写入到本地库中
        if (!needToAdd.isEmpty()) {
            for (int i = 0; i < needToAdd.size(); i++) {
                try {
                    EtCase etCase = needToAdd.get(i);
                    int effectRowNum = etCaseMapper.insert(etCase);
                    toDbAddOperLog(effectRowNum, collectRecord, EtCase.class.getName(), needToAdd.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.error(e.toString());
                }
            }
        }
        // 将数据更新至本地库中
        if (!needToUpdate.isEmpty()) {
            for (int i = 0; i < needToUpdate.size(); i++) {
                try {
                    int effectRowNum = etCaseMapper.updateByPrimaryKey(needToUpdate.get(i));
                    toDbUpdateOperLog(effectRowNum, collectRecord, EtCase.class.getName(), needToUpdate.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.error(e.toString());
                }
            }
        }
        return collectRecord;
    }

    @Override
    public Date getEtCaseLatestCreateTime() {
        return etCaseMapper.selectMaxCreateTime();
    }

    @Override
    public Date getEtCaseLatestUpdateTime() {
        return etCaseMapper.selectMaxUpdateTime();
    }

    @Override
    public List<EtAlarm> pageCollectJZJQData(Map<String, Object> map) {
        return jzCaseEntityMapper.findPageJQData(map);
    }

    @Override
    public CollectRecord writeOrUpdateEtAlarm(List<EtAlarm> srcDataArr,List<EtAlarm> needToAdd,List<EtAlarm> needToUpdate) {
        CollectRecord collectRecord = new CollectRecord();
        if (srcDataArr == null || srcDataArr.isEmpty()) {
            return collectRecord;
        }
//        List<EtAlarm> needToAdd = new ArrayList<>();
//        List<EtAlarm> needToUpdate = new ArrayList<>();
        // 判断数据是否已经写入过了
        for (int i = 0; i < srcDataArr.size(); i++) {
            EtAlarm srcData = srcDataArr.get(i);
            String jqbh = srcData.getAlarmNo();
            if (StringHelp.isEmpty(jqbh)) {
                LogUtils.error("CT 该" + EtAlarm.class.getName() + "数据 没有AJBH ! 系统编号：" + srcData.getId());
                continue;
            }
            Map<String, Object> param = new HashMap<>();
            param.put("alarmNo", jqbh);
            EtAlarm tempData = etAlarmMapper.findById(param);
            if (tempData == null) {
                needToAdd.add(srcData);
            } else {
                srcData.setId(tempData.getId());//一定要有主键
                needToUpdate.add(srcData);
            }
        }
        // 将数据写入到本地库中
        if (!needToAdd.isEmpty()) {
            for (int i = 0; i < needToAdd.size(); i++) {
                try {
                    EtAlarm etAlarm = needToAdd.get(i);
                    int effectRowNum = etAlarmMapper.insertSelective(etAlarm);
                    toDbAddOperLog(effectRowNum, collectRecord, EtCase.class.getName(), needToAdd.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.error(e.toString());
                }
            }
        }
        // 将数据更新至本地库中
        if (!needToUpdate.isEmpty()) {
            for (int i = 0; i < needToUpdate.size(); i++) {
                try {
                    int effectRowNum = etAlarmMapper.updateByPrimaryKeySelective(needToUpdate.get(i));
                    toDbUpdateOperLog(effectRowNum, collectRecord, EtCase.class.getName(), needToUpdate.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.error(e.toString());
                }
            }
        }
        return collectRecord;
    }

    @Override
    public Date getEtAlarmLatestCreateTime() {
        return etAlarmMapper.selectMaxCreateTime();
    }

    @Override
    public Date getEtAlarmLatestUpdateTime() {
        return etAlarmMapper.selectMaxUpdateTime();
    }

    @Override
    public List<ApScarela> pageCollectJZRYQKData(Map<String, Object> queryParam) {
        return jzCaseEntityMapper.findPageRYQKData(queryParam);
    }

    @Override
    public CollectRecord writeOrUpdateApScarela(List<ApScarela> srcDataArr,List<ApScarela> needToAdd,List<ApScarela> needToUpdate) {
        CollectRecord collectRecord = new CollectRecord();
        if (srcDataArr == null || srcDataArr.isEmpty()) {
            return collectRecord;
        }
//        List<ApScarela> needToAdd = new ArrayList<>();
//        List<ApScarela> needToUpdate = new ArrayList<>();
        // 判断数据是否已经写入过了
        for (int i = 0; i < srcDataArr.size(); i++) {
            ApScarela srcData = srcDataArr.get(i);
            String staffId = srcData.getStaffId();
            String id = srcData.getId();
            if (StringHelp.isEmpty(staffId)) {
                LogUtils.error("CT 该" + ApScarela.class.getName() + "数据 没有RYBH编号。 id：" + srcData.getId());
                continue;
            }
            Map<String, Object> param = new HashMap<>();
            param.put("id", id);
            ApScarela tempData = apScarelaMapper.findById(param);
            if (tempData == null) {
                needToAdd.add(srcData);
            } else {
                srcData.setId(tempData.getId());//一定要有主键
                needToUpdate.add(srcData);
            }
        }
        // 将数据写入到本地库中
        if (!needToAdd.isEmpty()) {
            for (int i = 0; i < needToAdd.size(); i++) {
                try {
                    ApScarela apScarela = needToAdd.get(i);
                    int effectRowNum = apScarelaMapper.insertSelective(apScarela);
                    toDbAddOperLog(effectRowNum, collectRecord, ApScarela.class.getName(), needToAdd.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.error(e.toString());
                }
            }
        }
        // 将数据更新至本地库中
        if (!needToUpdate.isEmpty()) {
            for (int i = 0; i < needToUpdate.size(); i++) {
                try {
                    int effectRowNum = apScarelaMapper.updateByPrimaryKeySelective(needToUpdate.get(i));
                    toDbUpdateOperLog(effectRowNum, collectRecord, ApScarela.class.getName(), needToUpdate.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.error(e.toString());
                }
            }
        }
        return collectRecord;
    }

    @Override
    public Date getApScarelaLatestCreateTime() {
        return apScarelaMapper.selectMaxCreateTime();
    }

    @Override
    public Date getApScarelaLatestUpdateTime() {
        return apScarelaMapper.selectMaxUpdateTime();
    }

    @Override
    public List<ApStaff> pageCollectJZXYRData(Map<String, Object> queryParam) {
        return jzCaseEntityMapper.findPageXYRData(queryParam);
    }

    @Override
    public CollectRecord writeOrUpdateApStaff(List<ApStaff> srcDataArr,List<ApStaff> needToAdd,List<ApStaff> needToUpdate) {
        CollectRecord collectRecord = new CollectRecord();
        if (srcDataArr == null || srcDataArr.isEmpty()) {
            return collectRecord;
        }
//        List<ApStaff> needToAdd = new ArrayList<>();
//        List<ApStaff> needToUpdate = new ArrayList<>();
        // 判断数据是否已经写入过了
        for (int i = 0; i < srcDataArr.size(); i++) {
            ApStaff srcData = srcDataArr.get(i);
            String staffId = srcData.getStaffId();
            if (StringHelp.isEmpty(staffId)) {
                LogUtils.error("CT 该" + ApStaff.class.getName() + "数据 没有AJBH ! 系统编号：" + srcData.getId());
                continue;
            }
            Map<String, Object> param = new HashMap<>();
            param.put("staffId", staffId);
            ApStaff tempData = apStaffMapper.findById(param);
            if (tempData == null) {
                needToAdd.add(srcData);
            } else {
                srcData.setId(tempData.getId());//一定要有主键
                needToUpdate.add(srcData);
            }
        }
        // 将数据写入到本地库中
        if (!needToAdd.isEmpty()) {
            for (int i = 0; i < needToAdd.size(); i++) {
                try {
                    ApStaff apStaff = needToAdd.get(i);
                    int effectRowNum = apStaffMapper.insert(apStaff);
                    toDbAddOperLog(effectRowNum, collectRecord, ApScarela.class.getName(), needToAdd.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.error(e.toString());
                }
            }
        }
        // 将数据更新至本地库中
        if (!needToUpdate.isEmpty()) {
            for (int i = 0; i < needToUpdate.size(); i++) {
                try {
                    int effectRowNum = apStaffMapper.update(needToUpdate.get(i));
                    toDbUpdateOperLog(effectRowNum, collectRecord, ApScarela.class.getName(), needToUpdate.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.error(e.toString());
                }
            }
        }
        return collectRecord;
    }

    @Override
    public Date getApStaffLatestCreateTime() {
        return apStaffMapper.selectMaxCreateTime();
    }

    @Override
    public Date getApStaffLatestUpdateTime() {
        return apStaffMapper.selectMaxUpdateTime();
    }

    @Override
    public void recordTaslLog(CollectRecord collectRecord) {
        if (collectRecord == null) {
            return;
        }
        SysTaskLog log = new SysTaskLog();
        log.setId(IDGenerator.getorderNo());
        log.setCreatTime(new Date());
        log.setReadTableName(collectRecord.getReadTableName());
        log.setWriteTableName(collectRecord.getWriteTableName());
        log.setLastexcuBegintime(collectRecord.getExcuBeginTime());
        log.setLastexcuEndtime(collectRecord.getExcuEndTime());
        log.setRemark(collectRecord.toString());
        sysTaskLogMapper.insert(log);// 通过数据库 持久化操作记录
    }

    @Override
    public SysTaskLog getLastTaskLog(String readTableName, String writeTableName) {
        SysTaskLog log = null;
        log = sysTaskLogMapper.selectLastLogByTableName(readTableName);
        return log;
    }

    private void toDbAddOperLog(int effectRowNum, CollectRecord collectRecord, String className, String mainStr) {
        if (effectRowNum <= 0) {
            LogUtils.error("CT 写入 " + className + " 原始数据失败 关键标识：" + mainStr);
        } else {
            collectRecord.addInsertNum(effectRowNum);
        }
    }

    private void toDbUpdateOperLog(int effectRowNum, CollectRecord collectRecord, String className, String mainStr) {
        if (effectRowNum <= 0) {
            LogUtils.error("CT 更新 " + className + " 原始数据失败 关键标识：" + mainStr);
        } else {
            collectRecord.addUpdateNum(effectRowNum);
        }
    }
}
