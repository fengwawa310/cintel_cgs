package com.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @描述 :
 * @作者 :	pst
 * @日期 :	2016/3/18
 * @时间 :	13:24
 */
public class StringHelp {

    /**
     * @return 字符串为 null 或者"" 返回true
     * @描述 :	判断字符串是否为空
     * @作者 :	乐胜
     * @创建日期 :	2016年3月16日 下午2:12:27
     */
    public static boolean isEmpty(Object str) {
        return str == null || String.valueOf(str).length() == 0;
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * @描述 :	只要有一个为null或空字符串则返回true
     * @作者 :	乐胜
     * @创建日期 :	2016年3月30日 上午9:12:27
     */
    public static boolean hasEmpty(Object... objects) {
        if (null == objects) {
            return true;
        }
        for (Object obj : objects) {
            if (isEmpty(obj))
                return true;
        }
        return false;
    }

    /**
     * 去除字符中的 空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String buildMessage(String content, Object[] args) {
        String contentStr = String.valueOf(content);
        if (args == null || args.length == 0) {
            return contentStr;
        }
        StringBuilder stringBuilder = new StringBuilder(contentStr);
        for (Object obj : args) {
            if (obj == null) {
                continue;
            }
            String objStr = String.valueOf(obj.toString());
            stringBuilder.append(objStr);
        }
        return stringBuilder.toString();
    }
}
