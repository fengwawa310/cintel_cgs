package com.mapper.sourceData;


import java.util.List;
import java.util.Map;

/**
 * Created by weipc on 2018/3/5.
 */
public interface DockingMapper {
    /*添加案件清洗分类数据*/
    void insertCS(List<Map<String, Object>> list) throws Exception;
    /*添加案件关键字数据*/
    void insertKW(List<Map<String, Object>> list) throws Exception;
    /*添加案件中涉案身份证号数据*/
    void insertIDNUM(List<Map<String, Object>> list) throws Exception;
    /*查询案件编号对应案件的标题*/
    String selectCaseTitle(String ajbh);
    /*查询案件编号对应警情的标题*/
    String selectAlarmTitle(String ajbh);
    /*添加案警研判任务流数据*/
    void insertJudgeflow(List<Map<String, Object>> list) throws Exception;

    /**
     * @Author: sky
     * @Description: 向数据库中插入案件分析的案件分类信息  本公司分析得到
     * @Date: 下午5:41 2018/5/17
     * @param: 案件分类数据集合
     */
    void caseClassifyInsert(List<Map<String, Object>> list) throws Exception;

    /**
     * @Author: sky
     * @Description: 向数据库中插入案件分析的身份证信息  本公司分析得到
     * @Date: 上午9:29 2018/5/18
     * @param: IDNUM_SET  案件身份证号码数据集合
     */
    void caseIdNumInsert(List<Map<String, Object>> list) throws Exception;
}
