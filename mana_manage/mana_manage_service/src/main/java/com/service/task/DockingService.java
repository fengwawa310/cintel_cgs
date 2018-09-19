package com.service.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weipc on 2018/3/5.
 */
public interface DockingService {
    /**
     * 案件清洗存储
     * @param paramMap
     */
    Map<String,Object> caseClean(HashMap<String, Object> paramMap);

    /**
     * @Author: sky
     * @Description: 向数据库中插入案件分析的案件分类信息  本公司分析得到
     * @Date: 下午5:41 2018/5/17
     * @param: 案件分类数据集合
     */
    void caseClassifyInsert(List<Map<String, Object>> CS_SET);

    /**
     * @Author: sky
     * @Description: 向数据库中插入案件分析的身份证信息  本公司分析得到
     * @Date: 上午9:29 2018/5/18
     * @param: IDNUM_SET  案件身份证号码数据集合
     */
    void caseIdNumInsert(List<Map<String, Object>> IDNUM_SET);

}
