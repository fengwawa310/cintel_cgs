package com.entity.utils;

import com.common.utils.StringHelp;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将 java.util.Date 类型数据翻译成 yyyy-MM-dd HH:mm:ss
 * Created by Administrator on 2017/12/11.
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public Object processArrayValue(Object value, JsonConfig config) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value, JsonConfig config) {
        return process(value);
    }

    private Object process(Object value) {
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(value);
        }
        if (value instanceof String) {
            String str = String.valueOf(value);
            str = StringHelp.replaceBlank(str);
            if (str.indexOf("\"") > 0) {
                value = str.replaceAll("\"", "#&34");
            }
            return value;
        }
        return value == null ? "" : value.toString();
    }
}
