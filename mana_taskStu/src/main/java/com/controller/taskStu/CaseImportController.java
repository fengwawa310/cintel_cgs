package com.controller.taskStu;

import com.alibaba.fastjson.JSONArray;
import com.entity.taskStu.EpCaseGang;
import com.service.taskStu.CaseImportService;
import com.service.taskStu.EtJudgeflowService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by weipc on 2018/3/15.
 */
@Controller
@RequestMapping("/importCase")
public class CaseImportController {

    @Resource
    private CaseImportService caseImportService;

    @RequestMapping(value="/importCase",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject importCase(HttpSession httpSession, HttpServletRequest request, @RequestParam HashMap<String,Object> paramMap ){
        JSONObject result =new JSONObject();
        try {
            List<EpCaseGang> jsonArray=(List<EpCaseGang>) JSONArray.parse(paramMap.get("list").toString());
            caseImportService.importCase(jsonArray);
            result.put("flag",true);
            result.put("msg","导入成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","导入失败！原因："+e);
            e.printStackTrace();
        }
        return result;
    }
}
