package com.controller.task;

import com.collect.CollectSrcDataModle;
import com.service.task.CollectSrcDataServiceInfc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Auri on 2018/3/23.
 * Desc:
 */
@RequestMapping("/collect")
@Controller
public class TestCollectController {


    @Resource
    private CollectSrcDataServiceInfc collectSrcDataService;

    /**
     * /oracle/test
     *
     * @return oracle 数据库访问测试
     */
//    @RequestMapping("/test")
//    public @ResponseBody
//    List<NiandudaocaojihuaEntity> test() {
//        List<NiandudaocaojihuaEntity> niandudaocaojihuaEntityList = oracleService.find();
//        return niandudaocaojihuaEntityList;
    //  不再㤇
//    }

    @RequestMapping("/test")
    public String collect() {
        CollectSrcDataModle csdm = new CollectSrcDataModle(collectSrcDataService);
        csdm.collectNewSrcData(true, true);
        return "welcom";
    }

//    @RequestMapping("/revise")
//    public String revise() {
//        CollectSrcDataModle csdm = new CollectSrcDataModle(collectSrcDataService);
//        csdm.reviseSrcData(true, true);
//        return "welcom";
//    }


}
