package com.common.utils;

import org.apache.logging.log4j.core.util.UuidUtil;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Auri on 2018/1/5.
 * Desc: 用于生成唯一ID 或 编号
 */
public class IDNoHelp {

    public static String buildNO(Class<?> clazz) {
        String className = clazz == null ? "UNKOWN" : clazz.getSimpleName().toUpperCase();
        String timeStr = TimeUtil.formatDateToStr(new Date(), "yyyyMMddHHmmss");
        int temp = (int) Math.random() * 10000;
        return className + timeStr + temp;
    }

    public static String buildID() {
        String timeStr = TimeUtil.formatDateToStr(new Date(), "yyyyMMddHHmmss");
        int temp = (int) Math.random() * 10000;
        return timeStr + temp;
    }
}
