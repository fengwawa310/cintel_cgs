package com.common.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Administrator on 2017/12/1.
 */
public class LogUtils {

    public static final int DEBUG = 1;

    public static final int INFO = 2;

    public static final int WARN = 3;

    public static final int ERROR = 4;

    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    private static final String currClassName = LogUtils.class.getName();

    public static void debug(Object msg) {
        String theMsg = buildStackMsg(msg);
        logger.log(Level.DEBUG, theMsg);
    }

    public static void debug(Object msg, Throwable throwable) {
        String theMsg = buildStackMsg(msg);
        logger.log(Level.DEBUG, theMsg, throwable);
    }

    public static void info(Object msg) {
        String theMsg = buildStackMsg(msg);
        logger.log(Level.INFO, theMsg);
    }

    public static void info(Object msg, Throwable throwable) {
        String theMsg = buildStackMsg(msg);
        logger.log(Level.INFO, theMsg, throwable);
    }

    public static void warn(Object msg) {
        String theMsg = buildStackMsg(msg);
        logger.log(Level.WARN, theMsg);
    }

    public static void warn(Object msg, Throwable throwable) {
        String theMsg = buildStackMsg(msg);
        logger.log(Level.WARN, theMsg, throwable);
    }

    public static void error(Object msg) {
        String theMsg = buildStackMsg(msg);
        logger.log(Level.ERROR, theMsg);
    }

    public static void error(Object msg, Throwable throwable) {
        String theMsg = buildStackMsg(msg);
        logger.log(Level.ERROR, theMsg, throwable);
    }

    public static void log(int level, Object message) {
        if (message == null) {
            return;
        }
        switch (level) {
            case DEBUG: {
                debug(message);
                break;
            }
            case INFO: {
                info(message);
                break;
            }
            case WARN: {
                warn(message);
                break;
            }
            case ERROR: {
                error(message);
                break;
            }
            default: {
                info(message);
            }
        }
    }

    public static void log(int level, Object message, Throwable throwable) {
        if (message == null || throwable == null) {
            return;
        }
        switch (level) {
            case DEBUG: {
                debug(message,throwable);
                break;
            }
            case INFO: {
                info(message,throwable);
                break;
            }
            case WARN: {
                warn(message,throwable);
                break;
            }
            case ERROR: {
                error(message,throwable);
                break;
            }
            default: {
                info(message,throwable);
            }
        }
    }

    private static String buildStackMsg(Object msg) {
        String msgStr = msg == null ? " No message." : msg.toString();
        // ʶ��Դ����ԭʼ��ջ
        StackTraceElement[] steArr = Thread.currentThread().getStackTrace();
        String srcStackMsg = getStackMsg(steArr);
        String messageStr = "[" + srcStackMsg + "]:" + msgStr;
        return messageStr;
    }

    private static String getStackMsg(StackTraceElement[] steArr) {
        if (steArr == null || steArr.length == 0) {
            return "UnKnowClass";
        }
        boolean tempFlag = false;
        for (int i = 0; i < steArr.length; i++) {
            StackTraceElement ste = steArr[i];
            // �����һ�ж�ջ�����Ǳ���Ķ�ջ������д�����ΪԴ�������ԭʼ��ջ��
            if (tempFlag) {
                // �п��ܻ��Ǹ��õĵ�ǰ��Ķ�ջ
                if (currClassName.equals(ste.getClassName()))
                {
                    continue;
                }
                return ste == null ? "UnKnowClass" : ste.toString();
            }
            if (currClassName.equals(ste.getClassName())) {
                tempFlag = true;
            }
        }
        return "UnKnowClass";
    }
}
