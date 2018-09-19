package com.controller.communal;

import com.common.consts.Global;
import com.common.utils.ResponseVO;
import com.entity.DicArea;
import com.entity.DicCommon;
import com.entity.DicToTel;
import com.entity.DicUnit;
import com.entity.caseInfo.EtCase;
import com.entity.sys.SysUser;
import com.service.communal.DicUtilsService;
import com.util.SysUserHelp;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 查询页面所需要的下拉框选项或其他
 * Created by weipc on 2018/1/6.
 */
@Controller
@RequestMapping("/dic")
public class DicUtilsController {

    @Resource
    private DicUtilsService dicUtilsService;
    //时间
    private String[] date = new String[5];
    //内容
    private JSONObject[] json = new JSONObject[5];

    /**
     * 查询字典中所有信息
     */
    @RequestMapping("/findDicCommon")
    @ResponseBody
    public JSONObject findDicCommonList(HttpSession httpSession, HttpServletRequest request, String falg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String newdate = sdf.format(new Date());
        if(date[1]!=null&&date[1].equals(newdate)){
            return json[1];
        }
        date[1]=newdate;
        json[1] = new JSONObject();
        List<DicCommon> dicCommonList = dicUtilsService.findDicCommonList();
        for (DicCommon dicCommon1:dicCommonList) {
            if("0".equals(dicCommon1.getParentCode())||Integer.parseInt(dicCommon1.getParentCode())==0)
            {//父级字典编码=0 即 为泛型
                String dicCode = dicCommon1.getDicCode();
                JSONObject value = new JSONObject();
                for (DicCommon dicCommon2:dicCommonList) {
                    if(dicCode.equals(dicCommon2.getParentCode())||Integer.parseInt(dicCode)==Integer.parseInt(dicCommon2.getParentCode()))
                    {//改泛型下的选项
                        value.put(dicCommon2.getDicCode(),dicCommon2.getDicDes());
                    }
                }
                json[1].put(dicCode,value);

            }
        }
        return json[1];
    }

    /**
     * 查询所有单位机构
     */
    @RequestMapping("/findDicUnit")
    @ResponseBody
    public JSONObject findDicUnitList(HttpSession httpSession, HttpServletRequest request, String falg){
        Map<String,String>  map = new HashMap<>();
        map.put("falg",falg);
        if(falg==null||!falg.equals("true")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String newdate = sdf.format(new Date());
//            if(date[2]!=null&&date[2].equals(newdate)){
//                return json[2];
//            }
            date[2]=newdate;
            json[2] = new JSONObject();
            List<DicUnit> dicUnits = new ArrayList<>();
            SysUser user = (SysUser)request.getAttribute("user");
            String isInputUnit = request.getParameter("isInputUnit");
            if("1".equals(isInputUnit)){
                DicUnit dicUnit= dicUtilsService.findDicUnitByID(SysUserHelp.getSysUserDicUnit(user));
                getDicUnitByPId(dicUnits, Arrays.asList(dicUnit));

                if(!"4".equals(user.getLevel())){//
//                    dicUnits.addAll(dicUtilsService.findDicUnitListByPId("440300190499"));
                    //数据库pid变化了
                    dicUnits.addAll(dicUtilsService.findDicUnitListByPId("T30000000001"));
                }

            }else{
                dicUnits = dicUtilsService.findDicUnitList(map);
            }
            json[2].put("list",dicUnits);
            StringBuffer html = new StringBuffer("<option value =\"\">--请选择--</option>");
            for (DicUnit one:dicUnits) {
                html.append("\n<option value =\""+one.getId()+"\">"+one.getName()+"</option>");
            }
            json[2].put("html",html.toString());
            return json[2];
        }else{
            //不需要查出Level=4的信息
            JSONObject jsonObject = new JSONObject();
            List<DicUnit> dicUnits= dicUtilsService.findDicUnitList(map);
            jsonObject.put("list",dicUnits);
            StringBuffer html = new StringBuffer("<option value =\"\">--请选择--</option>");
            for (DicUnit one:dicUnits) {
                html.append("\n<option value =\""+one.getId()+"\">"+one.getName()+"</option>");
            }
            jsonObject.put("html",html.toString());
            return jsonObject;
        }

    }
    
    /*根据父id查询字典数据*/
    private void getDicUnitByPId( List<DicUnit> dicUnits,List<DicUnit> dicUnitsTem) {
        if (dicUnitsTem.size()>0){
            dicUnits.addAll(dicUnitsTem);
            for (DicUnit unit : dicUnitsTem) {
                List<DicUnit> dicUnitListByPid = dicUtilsService.findDicUnitListByPId(unit.getId());
                getDicUnitByPId(dicUnits,dicUnitListByPid);
            }
        }
    }

    /**
     * 查询所有地区单位
     */
    @RequestMapping("/findDicArea")
    @ResponseBody
    public JSONObject findDicAreaList(HttpSession httpSession, HttpServletRequest request, EtCase etCase){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String newdate = sdf.format(new Date());
        if(date[3]!=null&&date[3].equals(newdate)){
            return json[3];
        }
        date[3]=newdate;
        json[3]=new JSONObject();
        String arearoot=Global.getConfig("sys.areaRoot");
        List<DicArea> dicAreaList = dicUtilsService.findDicAreaList(arearoot);
        json[3].put("list",dicAreaList);
        StringBuffer html = new StringBuffer("<option value =\"\">--请选择--</option>");
        for (DicArea dicArea:dicAreaList) {
            html.append("\n<option value =\""+dicArea.getId()+"\">"+dicArea.getName()+"</option>");
        }
        json[3].put("html",html.toString());
        return json[3];
    }

    /*
        /dic/findCurUserSysUnit

  *   根据查询当前用户的机构编码
  * */
    @RequestMapping("/findCurUserSysUnit")
    public @ResponseBody
    ResponseVO findCurUserSysUnit(HttpServletRequest request){
        SysUser user = (SysUser) request.getAttribute("user");
        //获取用户地区编码
        String userDicUnit = SysUserHelp.getSysUserDicUnit(user);
        List<DicUnit> cmsMenuAll=new ArrayList<>();
        DicUnit dicUnitByCode = dicUtilsService.findDicUnitByCode(userDicUnit);
        List<DicUnit> cmsMenuList=dicUtilsService.findDicUnitListByPId(dicUnitByCode.getId());
        if (cmsMenuList.size()>0) {
            cmsMenuAll.addAll(cmsMenuList);
            cmsMenuList = recursionFunMenu(cmsMenuAll,cmsMenuList);
        }
        cmsMenuList.add(dicUnitByCode);
        ResponseVO responseVO = new ResponseVO(ResponseVO.SUCCESS, "", cmsMenuList);
        return  responseVO;
    }

    /*递归算法*/
    public List<DicUnit> recursionFunMenu(List<DicUnit> cmsMenuAll,List<DicUnit> contentList){
        for (DicUnit content : contentList) {
            String id = content.getId();
            List<DicUnit> cmsMenuList=dicUtilsService.findDicUnitListByPId(id);
            if(cmsMenuList.size()>0){
                cmsMenuAll.addAll(cmsMenuList);
                recursionFunMenu(cmsMenuAll,cmsMenuList);
            }
        }
        return cmsMenuAll;
    }

//    /**
//     * 查询所有单位机构
//     */
//    @RequestMapping("/fastFileUrl")
//    @ResponseBody
//    public JSONObject fastFileUrl(HttpSession httpSession, HttpServletRequest request){
//        JSONObject json = new JSONObject();
//        String fastFileUrl= Global.getConfig("fastFileUrl");
//        json.put("fastFileUrl",fastFileUrl);
//        return json;
//    }

    /**
     * @Author: sky
     * @Description:根据配置文件中的区域ID获取电话区号
     * @Date: 上午10:22 2018/4/25
     * @param: request
     */
    @RequestMapping("/getAreacodeByGlobal")
    @ResponseBody
    public DicToTel getAreacodeByGlobal(HttpServletRequest request){
        DicToTel dicToTel = new DicToTel();
        //获取配置文件中的区域编码
        String code = Global.getConfig("sys.areaRoot");
        dicToTel = dicUtilsService.getAreacodeByGlobal(code);
        return dicToTel;
    }

}
