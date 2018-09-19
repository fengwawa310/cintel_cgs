package com.controller.task;

import com.collect.CollectSrcDataModle;
import com.common.utils.GetPropertiesUtil;
import com.common.utils.LogUtils;
import com.service.task.CollectSrcDataServiceInfc;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 调度测试类
 *
 * @author admin
 * @create 2017-12-06 17:13
 **/
@Service
public class SchedulerTest {

    private static int index = 0;

    @Resource
    private CollectSrcDataServiceInfc collectSrcDataService;

    /***
     * 调度测试方法。
     */
//     @Scheduled(cron = "0 0/2 * * * ? ") // 每2分钟执行一次
     @Scheduled(cron = "${OceanTimeCron}") // 每1分钟执行一次
//    @Scheduled(cron = "0 0 */1 * * ?") // 每1小时执行一次
    public void obtainAlarmData() {
        // 依据task.properties文件 控制触发
        Map<String, String> pros = GetPropertiesUtil.findProperties("resource/task.properties");
        String taskSwitchStr = pros.get("TimedTaskSwitch");
        if ("1".equals(taskSwitchStr)) {
            if (index % 2 == 0) {
                if ("info".equals(DockingController.logLevel)){
                    LogUtils.info("定时触发 原始数据采集任务");
                }
                index++;
                if ("info".equals(DockingController.logLevel)){
                    LogUtils.info("定时触发 原始数据采集任务");
                }
                //触发增量采集流程
                CollectSrcDataModle csdm = new CollectSrcDataModle(collectSrcDataService);
                csdm.collectNewSrcData(false, true);
            } else {
                if ("info".equals(DockingController.logLevel)){
                    LogUtils.info("定时触发 ES同步任务");
                }
                index = 0;
                TimerTask task= new TimerTask();
                task.run();
//                csdm.reviseSrcData(false, true);
            }
        } else {
            if ("info".equals(DockingController.logLevel)){
                LogUtils.info("原始数据采集任务 定时触发被关闭了");
            }
        }
        return;
    }

}
