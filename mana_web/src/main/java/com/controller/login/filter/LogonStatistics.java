package com.controller.login.filter;

import com.entity.sys.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 登录统计类
 * Created by weipc on 2018/1/15.
 */
public class LogonStatistics {

    private static final Logger logger = LoggerFactory.getLogger(LogonStatistics.class);

    private static int count = 0;
    //登录用户集合，设置为线程安全
    private static Map<String, SysUser> logonMap = Collections.synchronizedMap(new HashMap<String, SysUser>());
    //线程锁
    private static Lock lock = new ReentrantLock();

    private static boolean isIntercept =true;

    private static LogonStatistics instance = null;

    private LogonStatistics() { }

    public static LogonStatistics getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new LogonStatistics();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 刷新用户信息
     * @param now
     */
    public static boolean refresh(SysUser now) {
        lock.lock();
        try {
            if (logonMap.containsKey(now.getUserName()))
            {//如果在登录用户容器 logonMap 中指定的Key已存在
                //已登录用户信息
                SysUser old = logonMap.get(now.getUserName());
                //用户不用系统的时间
                long difference=now.getLogonTime().getTime()-old.getLogonTime().getTime();
                if(difference>=1*10*1000)
                {//用户不用系统的时间 大于5分钟时
                    if(old.getLogonIp().equals(now.getLogonIp()))
                    {//没有人登录
                        logonMap.remove(old.getUserName());
                        logonMap.put(now.getUserName(), now);
                        logger.info("=======================================================\n用户不用系统的时间 大于5分钟时 没有人登录 继续使用");
                        isIntercept=true;
                        return true;
                    }else{//已登录
                        logger.info("=======================================================\n用户不用系统的时间 大于5分钟时 已登录 进入登录页面");
                        isIntercept=false;
                        return false;
                    }
                }else{//用户不用系统的时间 小于5分钟时 直接刷新登录时间
                    if(old.getLogonIp().equals(now.getLogonIp()))
                    {
                        logonMap.remove(old.getUserName());
                        logonMap.put(now.getUserName(), now);
                        logger.info("=======================================================\n用户不用系统的时间 小于5分钟时 IP相同 直接刷新登录时间");
                        isIntercept=true;
                        return true;
                    }else{
                        logger.info("=======================================================\n用户不用系统的时间 小于5分钟时 IP不同相同 进入登录页面");
                        isIntercept=false;
                        return false;
                    }
                }
            } else{
                isIntercept=false;
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
    /**
     * 将登录用户保存到登录用户集合中
     * @param now
     */
    public static boolean logon(SysUser now) {
        lock.lock();
        try {
            if (logonMap.containsKey(now.getUserName()))
            {//如果在登录用户容器 logonMap 中指定的Key已存在
                //已登录用户信息
                SysUser old = logonMap.get(now.getUserName());
                //已登录用户不用系统的时间
                long difference=now.getLogonTime().getTime()-old.getLogonTime().getTime();
                if(difference>=1*10*1000)
                {//已登录用户不用系统的时间 大于5分钟时 可以登录
                    logonMap.remove(old.getUserName());
                    logonMap.put(now.getUserName(), now);
                    isIntercept=true;
                    return true;
                }else{//已登录用户不用系统的时间 小于5分钟 同一ip下允许登录
                    if(old.getLogonIp().equals(now.getLogonIp()))
                    {
                        logonMap.remove(old.getUserName());
                        logonMap.put(now.getUserName(), now);
                        isIntercept=true;
                        return true;
                    }else{
                        isIntercept=false;
                        return false;
                    }
                }
            } else {//用户不存在直接登录
                logonMap.put(now.getUserName(), now);
                count++;
            }
        } finally {
            lock.unlock();
        }
        isIntercept=true;
        return true;
    }

    /**
     * 退出登录 删除登录用户集合中的相关用户记录
     * @param userID
     */
    public static void unLogon(String userID) {
        lock.lock();
        try {
            if (logonMap.containsKey(userID)) {
                logonMap.remove(userID);
                count--;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 是否要拦截
     * @return
     */
    public static boolean getIsIntercept() {
        return isIntercept;
    }

    /**
     * 获取在线人数
     * @return
     */
    public static int getLogonCount() {
        return count;
    }



    /**
     * 根据用户的登录时间判断登录的先后顺序
     * @param lu
     * @return
     */
    public static boolean isOldLogon(SysUser lu) {
        lock.lock();
        try {
            //如果集合为空的,则默认这个用户为新用户(也就是没有可比较的对象)，则 返回false
            if(logonMap.containsKey(lu.getUserName())) {
                SysUser old = logonMap.get(lu.getUserName());
                //如果用户名一致，登录时间lu在old之前 则返回 true
                if((lu.getUserName().equals(old.getUserName())) && lu.getLogonTime().before(old.getLogonTime())){
                    return true;
                }
            }
            return false;
        }finally {
            lock.unlock();
        }
    }
    public static String getIp(String remoteAddr,String forwarded,String realIp) {
        String ip=null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }
}
