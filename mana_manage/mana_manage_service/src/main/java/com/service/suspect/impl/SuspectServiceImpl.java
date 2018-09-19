package com.service.suspect.impl;

import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.common.enums.EnumTypeVO;
import com.common.enums.PermissionCodeEnumType;
import com.common.utils.*;
import com.entity.alarm.EtAlarm;
import com.entity.caseInfo.EtCase;
import com.entity.suspect.*;
import com.entity.sys.SysUser;
import com.mapper.suspect.EtVehicleMapper;
import com.mapper.suspect.OperPermissionMapper;
import com.mapper.sys.SysUserMapper;
import com.mapper.utils.RlSuspectAlarmMapper;
import com.param.suspect.EtSuspectDTO;
import com.param.sys.SysUserParam;
import com.vo.suspect.OperPermissionVO;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.integral.EtUnitIntegral;
import com.entity.ticket.EtTicket;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.suspect.EtSuspectMapper;
import com.mapper.utils.EtTicketMapper;
import com.mapper.utils.EtUnitIntegralMapper;
import com.mapper.utils.RlSuspectCaseMapper;
import com.service.suspect.SuspectService;


@Service
@Transactional
public class SuspectServiceImpl implements SuspectService {

    @Resource
    protected EtSuspectMapper EtSuspectMapper;
    @Resource
    protected EtUnitIntegralMapper etUnitIntegralMapper;
    @Resource
    protected EtTicketMapper etTicketMapper;
    //车辆信息
    @Resource
    private EtVehicleMapper etVehicleMapper;

    @Resource
    private OperPermissionMapper operPermissionMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    //人-案对应关系建立
    @Resource
    private RlSuspectCaseMapper rlSuspectCaseMapper;

    //人-警情对应关系建立
    @Resource
    private RlSuspectAlarmMapper rlSuspectAlarmMapper;

    @Override
    public EtSuspect selectIDCardNum(EtSuspect etSuspect) {
        return EtSuspectMapper.selectIDCardNum(etSuspect);
    }

    @Override
    public String save(EtSuspect entity, EtUnitIntegral etUnitIntegral, String vehicles, String userId) {
        //添加重点人员基本信息
        entity.setBirthdate(new Date());
        //添加重点人员车辆信息
        List<Object> jsonArray = (List<Object>) JSONArray.parse(vehicles);
        Date date = new Date();
        String dataId;
        if (StringUtils.isNotBlank(entity.getId())) {//编辑
            dataId = entity.getId();
            EtSuspectMapper.updateByPrimaryKeySelective(entity);
            
            
            //保存车辆信息功能完善 on 2018/03/26
            List<EtVehicle> list = etVehicleMapper.selectvehicleList(entity.getSuspectId());
            String vehicle_old_ids = "";
            for(EtVehicle one : list){
            	vehicle_old_ids += one.getId()+",";
            }
            
            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, Object> paramMap = (Map<String, Object>) jsonArray.get(i);
                if (paramMap.size() > 0) {
                    EtVehicle vehicle = GsonUtils.toBean(paramMap.toString(), EtVehicle.class);
                    //主键
//                vehicle.setId(IDGenerator.getorderNo());
                    vehicle.setSuspectNo(entity.getSuspectId());
                    if (StringUtils.isBlank(vehicle.getId())) {
                        //主键
                        vehicle.setId(IDGenerator.getorderNo());
                        etVehicleMapper.insert(vehicle);
                    } else {
                    	vehicle_old_ids = vehicle_old_ids.replaceAll(vehicle.getId()+",", "");
                        etVehicleMapper.updateByPrimaryKeySelective(vehicle);
                    }
                }
            }
            
            for(String one : vehicle_old_ids.split(",")){
            	if(StringUtils.isNotBlank(one))
            		etVehicleMapper.deleteByPrimaryKey(one);
            }
        } else {//添加
            entity.setId(IDGenerator.getorderNo());
            dataId = entity.getId();
            EtSuspectMapper.insertSelective(entity);
            if (jsonArray != null && !(jsonArray.isEmpty())) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    Map<String, Object> paramMap = (Map<String, Object>) jsonArray.get(i);
                    if (paramMap.size() > 0) {
                        EtVehicle vehicle = GsonUtils.toBean(paramMap.toString(), EtVehicle.class);
                        //主键
                        vehicle.setId(IDGenerator.getorderNo());
                        //重点人员编号
                        vehicle.setSuspectNo(entity.getSuspectId());
                        etVehicleMapper.insert(vehicle);
                    }
                }
            }
            //单位积分保存
            if (etUnitIntegral != null) {
                etUnitIntegralMapper.mergeUnitIntegral(etUnitIntegral);
            }
            // 创建建档人权限
            OperPermission permission = new OperPermission();
            permission.setId(IDGenerator.getorderNo());
            permission.setSuspectNo(entity.getSuspectId());
            permission.setUserNo(userId);
            permission.setPermissionCode(OperPermission.PERMISSION_CREATOR);
            permission.setCreatTime(new Date());
            permission.setModifyTime(new Date());
            operPermissionMapper.insert(permission);

            //人-案对应关系建立
            List<EtCase> etCases = rlSuspectCaseMapper.findCaseByIDCardNum(entity);
            if (etCases != null && !etCases.isEmpty()) {
                for (EtCase etcase : etCases) {
                    RlSuspectCase rlSuspectCase = new RlSuspectCase();
                    rlSuspectCase.setCaseId(etcase.getId());
                    rlSuspectCase.setId(IDGenerator.getorderNo());
                    rlSuspectCase.setSuspectId(entity.getSuspectId());
                    rlSuspectCase.setRelation(0);
                    rlSuspectCase.setOptPCode(entity.getEntry());
                    rlSuspectCase.setOptPName(entity.getEntryName());
                    rlSuspectCaseMapper.insert(rlSuspectCase);
                }
            }
            //人-警情对应关系建立
            List<EtAlarm> etAlarms = rlSuspectAlarmMapper.findAlarmByIDCardNum(entity);
            if (etAlarms != null && !etAlarms.isEmpty()) {
                for (EtAlarm etAlarm : etAlarms) {
                    RlSuspectAlarm rlSuspectAlarm = new RlSuspectAlarm();
                    rlSuspectAlarm.setAlarmId(etAlarm.getId());
                    rlSuspectAlarm.setId(IDGenerator.getorderNo());
                    rlSuspectAlarm.setSuspectId(entity.getSuspectId());
                    rlSuspectAlarm.setRelation(0);
                    rlSuspectAlarm.setOptPCode(entity.getEntry());
                    rlSuspectAlarm.setOptPName(entity.getEntryName());
                    rlSuspectAlarmMapper.insert(rlSuspectAlarm);
                }
            }
        }
        return dataId;
    }

    @Override
    public int delete(String id) {
        int deleteByPrimaryKey = EtSuspectMapper.deleteByPrimaryKey(id);
        //删除es
        ElasticSearchUtils.delete("et_suspect", "cgs", id);
        return deleteByPrimaryKey;
    }

    @Override
    public int edit(EtSuspect entity) {
        int updateByPrimaryKeySelective = EtSuspectMapper.updateByPrimaryKeySelective(entity);
        return updateByPrimaryKeySelective;
    }

    @Override
    public EtSuspect find(String id) {
        EtSuspect selectByPrimaryKey = EtSuspectMapper.selectByPrimaryKey(id);
        return selectByPrimaryKey;
    }

    @Override
    public PageHelpVO findAll(PageVO pageVO, EtSuspect etSuspect) {
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<EtSuspect> list = new ArrayList<>();
        if (StringUtils.isNotBlank(etSuspect.getEntry()))
            list = EtSuspectMapper.findAll(etSuspect);
        PageInfo<EtSuspect> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<EtSuspect>(total, list);
        return pageHelpVO;
    }

    @Override
    public int editStatus(EtSuspect qe) {
        int updateByPrimaryKeySelective = EtSuspectMapper.updateByPrimaryKeySelective(qe);
        return updateByPrimaryKeySelective;
    }

    @Override
    public int searchIdCard(EtSuspect etSuspect) {
        int idcard = EtSuspectMapper.searchIdCard(etSuspect.getIdcardNum());
        return idcard;
    }

    @Override
    public int changeCtrlState(String suspectNo, Integer ctrlStatCode) {
        EtSuspect suspect = EtSuspectMapper.selectByNo(suspectNo);
        if (suspect == null) {
            return 0;
        }
        suspect.setIsIntl(ctrlStatCode);
        int num = EtSuspectMapper.updateByPrimaryKeySelective(suspect);
        return num;
    }

    @Override
    public PageHelpVO findticket(PageVO pageVO, EtTicket etSuspect) {

        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<EtTicket> selectByCallingNumber = etTicketMapper.selectByCallingNumber(etSuspect);
        PageInfo<EtTicket> pageInfo = new PageInfo<>(selectByCallingNumber);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<EtTicket>(total, selectByCallingNumber);
        return pageHelpVO;

    }

    @Override
    public List<EtSuspect> listAllForOperator(Map<String, Object> map) {
        return EtSuspectMapper.listAllForOperator(map);
    }

    @Override
    public PageHelpVO suspectAndpoliceList(PageVO pageVO, EtSuspectDTO etSuspectDTO) {
        SysUser sysUserP = new SysUser();
        sysUserP.setName(etSuspectDTO.getNameOrIdcard());//用户名搜索
        sysUserP.setIdcard(etSuspectDTO.getNameOrIdcard());//身份证号搜索
        sysUserP.setId(etSuspectDTO.getUserId());//重点人员选择时，过滤掉录入人。
        SysUserParam sysUserParam = new SysUserParam(sysUserP);
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
//        业务更改，由原来拉取关联表信息转移到拉取所有的警员信息
//        List<OperPermission> list = operPermissionMapper.suspectAndpoliceList(etSuspect);
        List<SysUser> sysUserList = sysUserMapper.findSysUserListByParamBlue(sysUserParam);
        ArrayList<OperPermissionVO> arrayList = new ArrayList<>();
        for (SysUser sysUser : sysUserList) {
            OperPermission operPermission = operPermissionMapper.selectOperPermissionBySuspectNoAndUserNo(etSuspectDTO.getSuspectId(), sysUser.getId());
            OperPermissionVO operPermissionVO = new OperPermissionVO(sysUser, operPermission);
            if (operPermission == null) {
                EnumTypeVO permission_code = new EnumTypeVO(PermissionCodeEnumType.WQX.getName(), String.valueOf(PermissionCodeEnumType.WQX.getValue()));
                operPermissionVO.setPermission_code(permission_code);
            } else {
                PermissionCodeEnumType valueof = PermissionCodeEnumType.valueof(operPermission.getPermissionCode());
                EnumTypeVO permission_code = new EnumTypeVO(valueof.getName(), String.valueOf(valueof.getValue()));
                operPermissionVO.setPermission_code(permission_code);
            }
            arrayList.add(operPermissionVO);
        }
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<OperPermissionVO>(total, arrayList);
        return pageHelpVO;
    }

    @Override
    public PageHelpVO findSuspectList(PageVO pageVO, EtSuspectDTO etSuspectDTO) {
        etSuspectDTO.setName(etSuspectDTO.getNameOrbyNameOrIdcardNum());//姓名
        etSuspectDTO.setByname(etSuspectDTO.getNameOrbyNameOrIdcardNum());//绰号
        etSuspectDTO.setIdcardNum(etSuspectDTO.getNameOrbyNameOrIdcardNum());//身份证号
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<EtSuspect> etSuspects = EtSuspectMapper.findSuspectList(etSuspectDTO);
        PageInfo<EtSuspect> pageInfo = new PageInfo<>(etSuspects);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<EtSuspect>(total, etSuspects);
        return pageHelpVO;
    }

    // <!-- 根据重点人员编号查询该重点人员的授权信息列表 -->
    @Override
    public PageHelpVO findUserListForSuspect(PageVO pageVO, EtSuspect etSuspect) {
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<OperPermission> operPermission = operPermissionMapper.selectBySuspectId(etSuspect);
        PageInfo<OperPermission> pageInfo = new PageInfo<>(operPermission);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<OperPermission>(total, operPermission);
        return pageHelpVO;
    }

    @Override
    public PageHelpVO<EtVehicle> findVehicleForSuspect(PageVO pageVO, EtVehicle EtVehicle) {
        Integer start = pageVO.getStart();
        Integer length = pageVO.getLength();
        PageHelper.startPage(start, length);
        List<EtVehicle> list = etVehicleMapper.selectvehicleList(EtVehicle.getSuspectNo());
        PageInfo<EtVehicle> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageHelpVO<EtVehicle> pageHelpVO = new PageHelpVO<EtVehicle>(total, list);
        return pageHelpVO;
    }

    @Override
    public Map<String, Object> findPersonById(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
//        String id=(String)map.get("id");
        EtSuspect etSuspect = EtSuspectMapper.findSuspectById(map);
        if (etSuspect.getHeadPhotoUrl() != null && !"".equals(etSuspect.getHeadPhotoUrl())) {
            String url = etSuspect.getHeadPhotoUrl();
//            String fastFileUrl=Global.getConfig("fastFileUrl");
//            url=fastFileUrl+url;
            etSuspect.setHeadPhotoUrl(url);
        }
        if (etSuspect != null) {
            List<EtVehicle> etVehicles = etVehicleMapper.selectvehicleList(etSuspect.getSuspectId());
            resultMap.put("etSuspect", etSuspect);
            resultMap.put("etVehicles", etVehicles);
        }
        return resultMap;
    }

    /**
     * @Author: sky
     * @Description: 根据姓名身份证获取重点人员
     * @Date: 下午3:02 2018/4/3
     * @param: etSuspect
     */
    @Override
    public EtSuspect findSuspectByBaseinfo(EtSuspect etSuspect) {
        return EtSuspectMapper.findSuspectByBaseinfo(etSuspect);
    }

    /**
     * @Author: sky
     * @Description:根据团伙id获取团伙人员
     * @Date: 下午3:44 2018/4/3
     * @param: gangId
     */
    @Override
    public List<EtSuspect> selectByGangId(String gangId) {
        return EtSuspectMapper.selectByGangId(gangId);
    }

    /**
     * 查询重点人员查看权限
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> suspectRole(Map<String, Object> map) {
        map.put("permissionCode",false);
        Map<String, Object> result= EtSuspectMapper.suspectRole(map);
        Integer countNum =Integer.parseInt(result.get("countNum").toString());
        if(countNum>0){
            map.put("permissionCode",true);
        }
        return map;
    }

    @Override
    public int getSuspectNum() {
        return EtSuspectMapper.getSuspectNum();
    }

}
