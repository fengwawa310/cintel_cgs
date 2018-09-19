package com.controller.task;


import com.common.utils.GetPropertiesUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by weipc on 2017/12/26.
 */
public class TimerListener implements ServletContextListener {
    private Timer timer = null;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void contextInitialized(ServletContextEvent event) {
        timer = new Timer(true);
        event.getServletContext().log("定时器已启动");
        TimerTask task = new TimerTask();
//        task.run();
//        Thread thread = new Thread(task);
//        thread.start();
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        Map<String, String> pros = GetPropertiesUtil.findProperties("resource/task.properties");
        // 首次执行的延时时间
        long initDelay = Long.parseLong(pros.get("initDelay"));
        // 定时执行的间隔时间
        long period = Long.parseLong(pros.get("period"));
        service.scheduleAtFixedRate(task,initDelay, period, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (timer != null) {
            timer.cancel();
            event.getServletContext().log("定时器销毁");
        }
    }

    /**
     * 获取指定时间对应的毫秒数
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
