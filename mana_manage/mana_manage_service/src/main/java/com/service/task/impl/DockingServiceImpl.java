package com.service.task.impl;

import com.alibaba.fastjson.JSONArray;
import com.common.utils.IDGenerator;
import com.mapper.sourceData.DockingMapper;
import com.service.task.DockingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 与BYS对接 接口
 * Created by weipc on 2018/3/5.
 */
@Service
@Transactional
public class DockingServiceImpl implements DockingService {
    @Resource
    private DockingMapper dockingMapper;

    private static final Logger logger = LoggerFactory.getLogger(DockingServiceImpl.class);
    /**
     * 案件清洗存储
     * @param paramMap
     */
    @Override
    public Map<String,Object>  caseClean(HashMap<String, Object> paramMap) {
        Map<String,Object> result= new HashMap<>();
        List<Map<String, Object>> REQ_SET = (List<Map<String, Object>>) JSONArray.parse(paramMap.get("REQ_SET").toString());
        //案件清洗分类数据
        List<Map<String, Object>> CS_SET = new ArrayList<>();
        //案件关键字数据
        List<Map<String, Object>> KW_SET = new ArrayList<>();
        //案件中涉案身份证号数据
        List<Map<String, Object>> IDNUM_SET = new ArrayList<>();
        //案警研判任务流数据
        List<Map<String, Object>> JUDGEFLOW = new ArrayList<>();
        String ajbhs="";
        for (Map<String, Object> map:REQ_SET) {
            String ajbh = map.get("AJBH").toString();
            ajbhs+=ajbh+",";
            List<Map<String, Object>> CS = (List<Map<String, Object>>) JSONArray.parse(map.get("CS_SET").toString());
            for (Map<String, Object> map1:CS) {
                map1.put("ID", IDGenerator.getorderNo());
                map1.put("CASE_NO",ajbh);
            }

            CS_SET.addAll(CS);
            List<Map<String, Object>> KW = (List<Map<String, Object>>) JSONArray.parse(map.get("KW_SET").toString());
            KW_SET.addAll(KW);
            for (Map<String, Object> map1:KW) {
                map1.put("ID", IDGenerator.getorderNo());
                map1.put("CASE_NO",ajbh);
            }

            String idnum_str = map.get("IDNUM_SET").toString();
            idnum_str = idnum_str.replace("[", "");
            idnum_str=idnum_str.replace("]","");
            idnum_str=idnum_str.replace("\"","");
            if(!"".equals(idnum_str)){
                String[] idnumSplit = idnum_str.split(",");
                for (String idnum:idnumSplit) {
                    Map<String, Object> IDNUM_MAP= new HashMap<>();
                    IDNUM_MAP.put("ID", IDGenerator.getorderNo());
                    IDNUM_MAP.put("IDNUM",idnum.trim());
                    IDNUM_MAP.put("CASE_NO",ajbh);
                    IDNUM_SET.add(IDNUM_MAP);
                }
            }
            Map<String, Object> judgeflowMap= new HashMap<>();
            judgeflowMap.put("ID",IDGenerator.getorderNo());
            judgeflowMap.put("CASE_NO",ajbh);
            judgeflowMap.put("JUDGE_TYPE","0");
            if(0==0){
                String caseTitle = dockingMapper.selectCaseTitle(ajbh);
                judgeflowMap.put("TITLE","关于 "+caseTitle+" 案件研判任务。");
            }else{
                String alarmTitle = dockingMapper.selectAlarmTitle(ajbh);
                judgeflowMap.put("TITLE","关于 "+alarmTitle+" 警情研判任务。");
            }
            JUDGEFLOW.add(judgeflowMap);
        }
        result.put("AJBH",ajbhs.replace("," ," "));

        try {
            //添加案件清洗分类数据
            if(CS_SET.size()>0){
                dockingMapper.insertCS(CS_SET);
            }
            System.out.println("CS_SET大小："+CS_SET.size());

            //添加案件关键字数据
            if(KW_SET.size()>0){
                dockingMapper.insertKW(KW_SET);
            }
            System.out.println("KW_SET大小："+KW_SET.size());

            //添加案件中涉案身份证号数据
            if(IDNUM_SET.size()>0){
                dockingMapper.insertIDNUM(IDNUM_SET);
            }
            System.out.println("IDNUM_SET大小："+IDNUM_SET.size());

            if(JUDGEFLOW.size()>0){
                //添加案警研判任务流数据
                dockingMapper.insertJudgeflow(JUDGEFLOW);
            }
            result.put("RET_CODE",0);
            result.put("ERR_TEXT","");
        } catch (Exception e) {
            result.put("RET_CODE",1);
            result.put("ERR_TEXT",e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Author: sky
     * @Description: 向数据库中插入案件分析的案件分类信息  本公司分析得到
     * @Date: 下午5:41 2018/5/17
     * @param: 案件分类数据集合
     */
    @Override
    public void caseClassifyInsert(List<Map<String, Object>> CS_SET) {
        try {
            System.out.println("--------");
            dockingMapper.caseClassifyInsert(CS_SET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author: sky
     * @Description: 向数据库中插入案件分析的身份证信息  本公司分析得到
     * @Date: 上午9:29 2018/5/18
     * @param: IDNUM_SET  案件身份证号码数据集合
     */
    @Override
    public void caseIdNumInsert(List<Map<String, Object>> IDNUM_SET) {
        try {
            System.out.println("----02----");
            dockingMapper.caseIdNumInsert(IDNUM_SET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
