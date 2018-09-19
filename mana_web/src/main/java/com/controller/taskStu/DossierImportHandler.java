package com.controller.taskStu;

import com.common.consts.Const;
import com.common.enums.DossierEnumType;
import com.common.services.api.APIClientRequest;
import com.common.utils.*;
import com.common.utils.httpclient.ObjectToMapGen;
import com.entity.taskStu.EpCaseGang;
import com.entity.xmlparse.EpClue;
import com.service.taskStu.DossierImportService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 线索和赌档线索导入
 * Created by weipc on 2018/3/15.
 */
@Controller
@RequestMapping("/dossierimportCase")
public class DossierImportHandler {


    @Resource
    private DossierImportService dossierImportService;

    /**
     *  /dossierimportCase/importCase
     * @param response
     * @param file
     * @param type
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importCase", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject importExcel(HttpServletResponse response, MultipartFile file,String type) throws IOException {
        try {
            //错误提示
            JSONObject map= new JSONObject();
            JSONObject map1= new JSONObject();
            map1.put("flag",false);
            //文件名
            String filename = file.getOriginalFilename();
            if("".equals(filename)||filename==null){
                map1.put("msg","请选择Excel文件！");
                map.put("data",map1);
                return map;
            }
            //后缀名
            String subfix = filename.lastIndexOf(".") == -1 ? "" : filename.substring(filename.lastIndexOf(".") + 1);
            List result;
            if (subfix.equals("xls")){
                result = ExceUtil.readExcel2003(file,7);
            } else if (subfix.equals("xlsx")){
                result = ExceUtil.readExcel2007(file,7);
            }else{
                map1.put("msg","只能上传Excel文件！");
                map.put("data",map1);
                return map;
            }
            if(result.size()==0){
                map1.put("msg","Excel中没有数据！");
                map.put("data",map1);
                return map;
            }
            List<EpClue> paramList = new ArrayList<>();
            for (int i = 0; i < result.size(); i ++) {
                if (i==0){  //  略过第一行标题

                }else {
                    List<String> list = (List) result.get(i);
                    EpClue ecg = new EpClue();
                    ecg.setId(IDGenerator.getorderNo());
                    ecg.setGangId(""); //团伙id
                    if (Integer.parseInt(type)==DossierEnumType.DUDANG_CLUE.getValue()){
                        ecg.setType(String.valueOf(DossierEnumType.DUDANG_CLUE.getValue()));//线索类型--自己赋值
                    }else {
                        ecg.setType(String.valueOf(DossierEnumType.QITA_CLUE.getValue()));//线索类型--自己赋值
                    }

                    if (this.isChinese(list.get(0))) {//是中文
                        ecg.setCaseName(list.get(0));//案件名称
                        ecg.setCaseNo(list.get(1));//案件编号

                    } else if (this.isChinese(list.get(1))) {//是中文
                        ecg.setCaseName(list.get(1));//案件名称
                        ecg.setCaseNo(list.get(0));//案件编号
                    } else {
                        if (StringUtils.isNotBlank(list.get(0))) {
                            ecg.setCaseNo(list.get(0));//案件编号
                        } else {
                            ecg.setCaseNo(list.get(1));//案件编号
                        }
                    }
    //              ecg.setCaseNo(list.get(0));//案件编号
    //               ecg.setCaseName(list.get(1));//案件名称
                    ecg.setCaseDetail(list.get(2));//简要案情
                    ecg.setRemark(list.get(3) + list.get(5) + list.get(6));
                    ecg.setSuspect(list.get(4));
                    paramList.add(ecg);
                }
            }
            dossierImportService.insertEpClueList(paramList);
            JSONObject data = new JSONObject();
            JSONObject paramJson = new JSONObject();
            paramJson.put("flag",true);
            paramJson.put("msg","导入成功！");
            data.put("data",paramJson);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject data = new JSONObject();
            JSONObject paramJson = new JSONObject();
            paramJson.put("flag",false);
            paramJson.put("msg","导入失败！");
            data.put("data",paramJson);
            return data;
        }
    }
    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    /**     /dossierimportCase/findEpClueList
     *
     查询系统公告列表
     */
    @RequestMapping(value="/findEpClueList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DatatablesResponse findEpClueList(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start,
                                       @RequestParam(required=false,defaultValue="10")Integer length){
//        SysUser user = (SysUser) request.getAttribute("user");
//        String userVOStr = GsonUtils.getGson().toJson(user);
//        JSONObject userVOObj = JSONObject.fromObject(userVOStr);
        //查询条件
//        SysNotice sysNotice = GsonUtils.toBean(search,SysNotice.class);
        //分页条件
//        if (sysNotice == null){
//            sysNotice = new SysNotice();
//        }
        /*查询条件*/
        Map map = new HashMap<>();
        map.put("start",""+start);
        map.put("length",""+length);
        PageHelpVO pageHelpVO = null;
        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "/dossier/findEpClueList", map);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
        DatatablesResponse<EpClue> resp = DatatablesResponse.build(dataSet);
        return resp;
    }
}
