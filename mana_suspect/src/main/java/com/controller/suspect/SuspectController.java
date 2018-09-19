package com.controller.suspect;

import com.common.consts.Const;
import com.common.enums.PermissionCodeEnumType;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.common.utils.StringHelp;
import com.entity.DicCommon;
import com.entity.DicUnit;
import com.entity.integral.EtUnitIntegral;
import com.entity.suspect.*;
import com.entity.ticket.EtTicket;
import com.param.suspect.EtSuspectDTO;
import com.service.communal.DicUtilsService;
import com.service.suspect.*;
import com.service.utils.HostUtils;
import com.service.utils.IntegralCalculationService;
import com.vo.suspect.SuspectTreeNodeVo;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/suspect")
public class SuspectController extends BaseControlller {

    @Resource
    private SuspectService suspectService;
    @Resource
    private TreeServiceInfc treeServiceInfc;
    @Resource
    private IntegralCalculationService integralCalculationService;
    @Resource
    private DicUtilsService dicUtilsService;
    @Resource
    private HostUtils hostUtils;

    @Resource
    private OperPermissionService operPermissionService;
    @Resource
    private RlSuspectCaseService rlSuspectCaseService;
    @Resource
    private RlSuspectAlarmService rlSuspectAlarmService;
    @Resource
    private RlSuspectGangService rlSuspectGangService;


    /**
     * @Author: sky
     * @Description:如果有数据，根据人员姓名以及身份证号查询人员是否为重点人员并查询起团伙成员
     * @Date: 下午2:59 2018/4/3
     * @param: request
    etSuspect
     */
    @RequestMapping(value = "/findGang", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> findGang(HttpServletRequest request,EtSuspect etSuspect){

        Map<String,Object> resultMap=new HashMap<>();;
        List<EtSuspect> suspects = new ArrayList<>();

        //根据姓名和身份证判断是否为重点人员
        EtSuspect et = new EtSuspect();
        et.setName(etSuspect.getName());
        et.setIdcardNum(etSuspect.getIdcardNum());
        EtSuspect suspect = suspectService.findSuspectByBaseinfo(et);
        if (suspect == null){
            resultMap.put("code","400");
            resultMap.put("message","不是重点人员");
        }else {
            resultMap.put("code","200");
            resultMap.put("message","是重点人员");
            //获取重点人员id
            String suspectId = suspect.getSuspectId();
            //根据重点人员id查询所有团伙id集合
            List<String> gangIdList = rlSuspectGangService.findGangsBySuspectId(suspectId);
            if(!gangIdList.isEmpty()){
                for (String gangId :gangIdList){
                    //根据团伙id获取团伙人员
                    List<EtSuspect> etSuspects = suspectService.selectByGangId(gangId);
                    suspects.addAll(etSuspects);
                }
            }
        }
        resultMap.put("gangList",suspects);
        return resultMap;
    }

    /*
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> save(HttpServletRequest request, EtSuspect etSuspect, String vehicles, String userId) {
        Map<String, Object> map = new HashMap<>();
//        //获取User 的对象
//        SysUser user = (SysUser) request.getAttribute("user");
        EtSuspect ets = suspectService.selectIDCardNum(etSuspect);
        if (!StringUtils.isNotBlank(etSuspect.getId()))
        {
            if (ets != null) {
                map.put("flag", false);
                map.put("msg", "操作失败,该身份证号已录入！");
                return map;
            }
        }
        
        DicUnit findDicUnitByCode = dicUtilsService.findDicUnitByCode(etSuspect.getEntryUnit());
        if (etSuspect == null) {
            System.out.println("et为null------------------------------------------------------------------");
        }
        if (findDicUnitByCode != null) {

            etSuspect.setEntryUnitName(findDicUnitByCode.getName());
        } else {
            System.out.println("find为null------------------------------------------------------------------");
        }
        EtUnitIntegral integralCalcOfUnit = integralCalculationService.integralCalcOfUnit(Const.INTEGRAL_UNIT_SUSPECT, etSuspect.getEntryUnit(), etSuspect.getEntryUnitName());
        try {
            String id = suspectService.save(etSuspect, integralCalcOfUnit, vehicles, userId);
            map.put("flag", true);
            map.put("msg", "操作成功!");
            map.put("id",id);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "操作失败!");
            map.put("id","");
        }
        return map;
    }

    /*
     * 删除
     */
    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int delete(HttpServletRequest request, String id) {
        int delete = suspectService.delete(id);
        return delete;
    }

    /*
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int edit(HttpServletRequest request, EtSuspect etSuspect) {
        System.out.println(etSuspect);
        int edit = suspectService.edit(etSuspect);
        return edit;
    }

    //	/*
    //	 * 查询
    //	 */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public EtSuspect list(HttpSession httpSession, HttpServletRequest request, String id) {
        EtSuspect find = suspectService.find(id);
        return find;
    }

    /**
     * 依照条件获取可选择的重点人员树形结构数据
     *
     * @param paramStr 搜索条件字符
     */
    @RequestMapping(value = "/listSelectableTreeNodes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<SuspectTreeNodeVo> listSelectableTreeNodes(HttpSession httpSession, HttpServletRequest request, String userId, String gangId, String paramStr) {
        List<SuspectTreeNodeVo> treeNodes = new ArrayList<>();
        if (StringHelp.isEmpty(userId)) {
            return treeNodes;
        }
        gangId = StringHelp.isEmpty(gangId) ? "" : gangId;
        try {
            List<RlSuspectGang> rls = rlSuspectGangService.selectRlsByGangId(gangId);
            Set<String> suspectIds = new HashSet<>();
            if (rls != null && !(rls.isEmpty())) {
                for (int i = 0; i < rls.size(); i++) {
                    RlSuspectGang rl = rls.get(i);
                    String suspectId = rl.getSuspectId();
                    suspectIds.add(suspectId);
                }
            }
            List<SuspectTreeNodeVo> nodes = treeServiceInfc.listTreeNodesByPermission(userId, paramStr, suspectIds);
            treeNodes.addAll(nodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeNodes;
    }

    /**
     * 依照条件获取重点人员树形结构数据
     *
     * @param paramStr 搜索条件字符
     * @param treeType 1=按团伙；2=按所属单位；3=按重点人员类型SUSPECT_TYPE
     */
    @RequestMapping(value = "/listTreeNodes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object>
    listTreeNodes(HttpSession httpSession, HttpServletRequest request, String paramStr, Integer treeType, String userId, String level,String entryUnit) {
        Map<String, Object> map = new HashMap<>();
//        Set<String> suspectIds = new HashSet<>();
        Map<String,SuspectTreeNodeVo> tempTreeNodeMap = new HashMap<>();
        List<SuspectTreeNodeVo> treeNodes = null;
        List<SuspectTreeNodeVo> finalTreeNodes = new ArrayList<>();
        treeType = treeType == null ? SuspectTreeNodeVo.LIST_BY_ENTRY_UNIT : treeType;
        try {
            if (treeType == SuspectTreeNodeVo.LIST_BY_SUSPECTTYPE) {
                List<DicCommon> dics = dicUtilsService.findDicCommonList("43");
                treeNodes = treeServiceInfc.listTreeNodesByType(dics, paramStr,userId,entryUnit);
            } else if (treeType == SuspectTreeNodeVo.LIST_BY_GANG) {
                treeNodes = treeServiceInfc.listTreeNodesByGang(null, paramStr,userId);
            } else {
                List<DicUnit> units = dicUtilsService.findDicUnitListByGrade("3");
                treeNodes = treeServiceInfc.listTreeNodesByUnit(units, paramStr,userId,entryUnit);
            }
            if (treeNodes != null) {
                for (SuspectTreeNodeVo vo : treeNodes) {
                    if (userId.equals(vo.getUserId())) {
                        vo.setPermissionCode(1);
                    }
                    if(vo.getType() == 1){//人员
                    	String suspectId = vo.getSuspectNo();
                    	if("".equals(suspectId)){
                            suspectId="未知";
                        }
                    	tempTreeNodeMap.put(vo.getpId()+suspectId,vo);
                    }
                    if(vo.getType() == 0){//团伙
                    	tempTreeNodeMap.put(vo.getId(),vo);
                    }
                }
                finalTreeNodes.addAll(tempTreeNodeMap.values());
            }
        } catch (Exception e) {
            e.printStackTrace();
            treeNodes = new ArrayList<>();
        }
        map.put("tree", finalTreeNodes);
        map.put("level", level);
        return map;
    }

    /*
     * 带条件查询
     */
    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO findAll(HttpSession httpSession, HttpServletRequest request, PageVO pageVO, EtSuspect etSuspect) {
        PageHelpVO pageHelpVO = null;
        try {
            pageHelpVO = suspectService.findAll(pageVO, etSuspect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }

    /*
     * 状态修改
     */
    @RequestMapping(value = "/editstatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int editStatus(HttpServletRequest request, EtSuspect etSuspect) {
        int editStatus = suspectService.editStatus(etSuspect);
        return editStatus;
    }

    /*
     *调取话单数据
     */
    @RequestMapping(value = "/findticket", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO findTicket(HttpSession httpSession, HttpServletRequest request, PageVO pageVO, EtTicket etTicket) {
        PageHelpVO findticket = null;
        try {
            findticket = suspectService.findticket(pageVO, etTicket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findticket;
    }

    //	列出授权列表所有重点人员
    @RequestMapping(value = "/listAllForOperator", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<EtSuspect> listAllForOperator(@RequestParam Map<String, Object> map) {
        List<EtSuspect> etSuspectList = suspectService.listAllForOperator(map);
        return etSuspectList;
    }

    //	修改重点人员与案件关系表
    @RequestMapping(value = "/editForCase", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int editForCase(HttpServletRequest request, RlSuspectCase rlSuspectCase) {
        if (rlSuspectCase == null) {
            rlSuspectCase.setCaseId(request.getParameter("id"));
            rlSuspectCase.setCaseId(request.getParameter("caseId"));
            rlSuspectCase.setSuspectId(request.getParameter("suspectId"));
        }
        int update = rlSuspectCaseService.update(rlSuspectCase);
        return update;
    }

    //	修改重点人员与警情关系表
    @RequestMapping(value = "/editForAlarm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int editForAlarm(HttpServletRequest request, RlSuspectAlarm rlSuspectAlarm) {
        int update = rlSuspectAlarmService.update(rlSuspectAlarm);
        return update;
    }

    /*
     * 多条件查询,重点人员警官权限授予
     *
     *      /suspect/suspectAndpoliceList?suspectId=111&start=1&length=10
     */
    @RequestMapping(value = "/suspectAndpoliceList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO suspectAndpoliceList(HttpSession httpSession, HttpServletRequest request, PageVO pageVO, EtSuspectDTO etSuspect) {
        PageHelpVO pageHelpVO = null;
        try {
            pageHelpVO = suspectService.suspectAndpoliceList(pageVO, etSuspect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }

    /*
     * 状态修改
     */
    @RequestMapping(value = "/editSuspectAndpolice", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int editSuspectAndpolice(String suspectNo, String userNo,
                                    String type) {
        int i = 1;
        try {
            if (type.equals(String.valueOf(PermissionCodeEnumType.WQX.getValue()))) {//无权限需要把原来表删除
                operPermissionService.deleteBySuspectNoAndUserNo(suspectNo, userNo);
            } else {
                OperPermission operPermission = operPermissionService.selectOperPermissionBySuspectNoAndUserNo(suspectNo, userNo);
                if (operPermission == null) {//新添加
                    OperPermission operPermission1 = new OperPermission();
                    operPermission1.setId(IDGenerator.getorderNo());
                    operPermission1.setSuspectNo(suspectNo);
                    operPermission1.setUserNo(userNo);
                    operPermission1.setPermissionCode(Integer.parseInt(type));
                    operPermission1.setCreatTime(new Date());
                    operPermission1.setModifyTime(new Date());
                    operPermissionService.insert(operPermission1);
                } else {//更新
                    OperPermission operPermission2 = new OperPermission();
                    operPermission2.setSuspectNo(suspectNo);
                    operPermission2.setUserNo(userNo);
                    operPermission2.setPermissionCode(Integer.parseInt(type));
                    operPermissionService.updateBySuspectNoAndUserNoSelective(operPermission2);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            i = 2;
        }
        return i;
    }

    /*
  * 多条件查询,重点人员警官权限授予
  *
  *      /suspect/findSuspectList?start=1&length=10
  */
    @RequestMapping(value = "/findSuspectList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO findSuspectList(HttpSession httpSession, HttpServletRequest request, PageVO pageVO, EtSuspectDTO etSuspect) {
        PageHelpVO pageHelpVO = null;
        try {
            pageHelpVO = suspectService.findSuspectList(pageVO, etSuspect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }

    /*
     *  <!-- 根据重点人员编号查询该重点人员的授权信息列表 -->
     */
    @RequestMapping(value = "/findUserListForSuspect", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO findUserListForSuspect(HttpSession httpSession, HttpServletRequest request, PageVO pageVO, EtSuspect etSuspect) {
        PageHelpVO pageHelpVO = null;
        try {
            pageHelpVO = suspectService.findUserListForSuspect(pageVO, etSuspect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }

    /*
     * 警情标记方法接口
     */
    @RequestMapping(value = "/etSuspectAlarm", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int etSuspectAlarm(HttpSession httpSession, HttpServletRequest request, RlSuspectAlarm rlSuspectAlarm) {
        if (rlSuspectAlarm == null) {
            return 0;
        }
        int update = rlSuspectAlarmService.update(rlSuspectAlarm);
        return update;
    }

    /*
     * 警情关系确认方法
     */
    @RequestMapping(value = "/etSuspectAlarmQR", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int etSuspectAlarmQR(HttpSession httpSession, HttpServletRequest request, RlSuspectAlarm rlSuspectAlarm) {
        if (rlSuspectAlarm == null) {
            return 0;
        }
        int update = rlSuspectAlarmService.insert(rlSuspectAlarm);
        return update;
    }

    /*
     * 案件关系标记方法接口
     */
    @RequestMapping(value = "/etSuspectCase", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int etSuspectCase(HttpSession httpSession, HttpServletRequest request, RlSuspectCase rlSuspectCase) {
        if (rlSuspectCase == null) {
            return 0;
        }
        int update = rlSuspectCaseService.update(rlSuspectCase);
        return update;
    }

    /*
     * 案件关系确认方法接口
     */
    @RequestMapping(value = "/etSuspectCaseQR", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int etSuspectCaseQR(HttpSession httpSession, HttpServletRequest request, RlSuspectCase rlSuspectCase) {
        if (rlSuspectCase == null) {
            return 0;
        }
        int update = rlSuspectCaseService.insert(rlSuspectCase);
        return update;
    }

    @RequestMapping(value = "/findVehicleForSuspect", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageHelpVO<EtVehicle> findVehicleForSuspect(HttpSession httpSession, HttpServletRequest request, PageVO pageVO, EtVehicle etVehicle) {
        PageHelpVO<EtVehicle> pageHelpVO = null;
        try {
            pageHelpVO = suspectService.findVehicleForSuspect(pageVO, etVehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
    }


    /**
     * 查询单个重点人员信息
     * <p>
     * /suspect/findPersonById?id='16169FC6C33F02'
     *
     * @return
     */
    @RequestMapping(value = "/findPersonById", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> findPersonById(HttpSession httpSession, HttpServletRequest request, String id, String suspectId) {
        Map<String, Object> map = new HashMap<>();
        if (id != null && !"".equals(id)) {
            map.put("id", id);
        }
        if (suspectId != null && !"".equals(suspectId)) {
            map.put("suspectId", suspectId);
        }
        Map<String, Object> resultMap = null;
        try {
            resultMap = suspectService.findPersonById(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 查询重点人员查看权限
     * @return
     */
    @RequestMapping(value = "/suspectRole", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> suspectRole(HttpSession httpSession, HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
        Map<String, Object> resultMap = suspectService.suspectRole(map);
        return resultMap;
    }

    /**
     * @Author: sky
     * @Description:获取重点人员数量  人员数
     * @Date: 10:01 2018/5/24
     * @param: httpSession
    request
     */
    @RequestMapping(value="/getSuspectNum",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> getSuspectNum(HttpSession httpSession){
        Map<String,Object> resultMap = new HashMap<>();
        int suspectNum = suspectService.getSuspectNum();
        resultMap.put("suspectNum",suspectNum);
        return resultMap;
    }

}