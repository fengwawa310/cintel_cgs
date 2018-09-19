package com.controller.taskStu;

import com.common.utils.IDGenerator;
import com.service.taskStu.EtAnalyseService;
import com.vo.taskStu.EtAnalyse;
import com.vo.taskStu.ResultData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: sky
 * @Description:
 * @Date: Create in 下午2:13 2018/4/23
 */
@Controller
@RequestMapping("/analyse")
public class AnalyseController {

    @Resource
    EtAnalyseService etAnalyseService;


    /**
     * @Author: sky
     * @Description: 保存模块分析结果
     * @Date: 下午4:13 2018/4/23
     * @param: request
    etAnalyse
     */
    @RequestMapping(value="/saveAnalyse",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultData saveAnalyse(HttpServletRequest request, EtAnalyse etAnalyse){

        //创建返回结果实体类
        ResultData resultData = new ResultData();

        Date date = new Date();
        //判断是新增操作还是更新操作
        if (StringUtils.isBlank(etAnalyse.getId())){
            //新增
            etAnalyse.setId(IDGenerator.getorderNo());
            etAnalyse.setCreateTime(date);
            etAnalyse.setUpdateTime(date);
            if (etAnalyseService.insertSelective(etAnalyse) == 1){
                resultData.setCode("200");
                resultData.setMsg("保存成功!");
                return resultData;
            }
        }else {
            //更新操作
            etAnalyse.setUpdateTime(date);
            if(etAnalyseService.updateByPrimaryKeySelective(etAnalyse) == 1){
                resultData.setCode("200");
                resultData.setMsg("更新成功!");
                return resultData;
            }
        }

        resultData.setCode("500");
        resultData.setMsg("操作失败!");
        return resultData;
    }


    /**
     * @Author: sky
     * @Description: 根据id删除历史保存数据
     * @Date: 下午4:13 2018/4/23
     * @param: request
    id
     */
    @RequestMapping(value="/deleteAnalyse",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultData deleteAnalyse(HttpServletRequest request, String id){

        //创建返回结果实体类
        ResultData resultData = new ResultData();

        if(etAnalyseService.deleteByPrimaryKey(id) == 1){
            resultData.setCode("200");
            resultData.setMsg("删除成功!");
            return resultData;
        }

        resultData.setCode("500");
        resultData.setMsg("删除失败!");
        return resultData;
    }


    /**
     * @Author: sky
     * @Description: 根据模块场景、用户id获取列表
     * @Date: 下午4:16 2018/4/23
     * @param: request
    etAnalyse
     */
    @RequestMapping(value="/analyseList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<EtAnalyse> analyseList(HttpServletRequest request, EtAnalyse etAnalyse){
        List<EtAnalyse> analyses = new ArrayList<>();
        analyses = etAnalyseService.selectByType(etAnalyse);
        return analyses;
    }

    /**
     * @Author: sky
     * @Description: 根据id获取结果数据
     * @Date: 下午4:16 2018/4/23
     * @param: request
    etAnalyse
     */
    @RequestMapping(value="/findAnalyseById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public EtAnalyse findAnalyseById(HttpServletRequest request, String id){
        EtAnalyse etAnalyse = new EtAnalyse();
        etAnalyse = etAnalyseService.selectByPrimaryKey(id);
        return etAnalyse;
    }


}
