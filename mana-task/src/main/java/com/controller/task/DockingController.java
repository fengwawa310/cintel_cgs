package com.controller.task;

import com.common.consts.Global;
import com.common.utils.GetPropertiesUtil;
import com.common.utils.LogUtils;
import com.service.task.DockingService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 与BYS对接 接口
 * Created by weipc on 2018/3/5.
 */
@Controller
@RequestMapping("/docking")
public class DockingController {

    private static final Logger logger = LoggerFactory.getLogger(DockingController.class);

    private static String flag= Global.getTask("caseCleanSwitch");
    public static String logLevel = Global.getTask("logLevel");

    @Resource
    private DockingService dockingService;

    @RequestMapping("/test")
    public void tets(){
        String haha = logLevel;
        System.out.println(haha);
        if ("info".equals(DockingController.logLevel)){
            LogUtils.error("hfalsjfljkadsf");
        }
    }

    /**
     * BYS案件清洗存储接口
     */
    @RequestMapping(value="/caseClean ",produces = "application/json;charset=UTF-8" )
    public JSONObject save(HttpSession httpSession, HttpServletRequest request, @RequestParam HashMap<String,Object> paramMap){
        JSONObject result =new JSONObject();
        String OP_ID=paramMap.get("OP_ID").toString();
        result.put("OP_ID",OP_ID);
        if("1".equals(flag)){
            System.out.println("BYS案件清洗存储开始");
            System.out.println("BYS传来map大小："+paramMap.size());
            System.out.println("BYS传来参数："+paramMap.toString());
            System.out.println("BYS传来OP_ID参数："+OP_ID);
            Map<String,Object> map=dockingService.caseClean(paramMap);
            result.put("RET_SET",map);
            System.out.println("BYS案件清洗存储结束");
        }else{
            result.put("RET_SET","");
        }
        return result;
    }

    /**
     * BYS案件清洗存储接口
     */
    @RequestMapping(value="/timerListener",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject timerListener(HttpSession httpSession, HttpServletRequest request){

        JSONObject result =new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        TimerTask task = new TimerTask();
        try {
            task.NEWDATE = "0000-00-00 00:00:00";
            System.out.println("\n\n定时任务开始。。。\n\n");
            //写入案件
            task.insertOrUpdate("et_case","cgs_et_case","cgs");
            //写入警情
            task.insertOrUpdate("et_alarm","cgs_et_alarm","cgs");
            //写入重点人员
            task.insertOrUpdate("et_suspect","cgs_et_suspect","cgs");
            //写入嫌疑人
            task.insertOrUpdate("ap_staff","cgs_ap_staff","cgs");
            //把最近更新时间写入配置文件中

            Map<String, String> pros=new HashMap<>();
            pros.put("updateDate",date);
            GetPropertiesUtil.updateProperties("resource/task.properties",pros);
            System.out.println("\n\n定时任务结束。。。\n\n");
            result.put("flag",true);
            result.put("msg","es同步成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("flag",false);
            result.put("msg","es同步异常："+e);
        }

        return result;
    }

}
