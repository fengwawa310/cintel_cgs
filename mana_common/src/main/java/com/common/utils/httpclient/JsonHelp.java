package com.common.utils.httpclient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @描述  : Json-Object转换帮助类
 * @作者  :	乐胜
 * @日期  :	2016/3/18
 * @时间  :	15:24
 */
public class JsonHelp {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * @描述		:	对象转Json
     * @作者		:	乐胜
     * @创建日期	:	2016年3月16日 下午3:31:27
     */
    public static String toJSon(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @描述		:	json字符串转java对象
     * @作者		:	乐胜
     * @创建日期	:	2016年3月16日 下午3:31:27
     */
    public static <T> T readJson(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @描述 :  读取泛型集合
     * @作者 :	pst
     * @日期 :	2016/5/18
     * @时间 :	16:21
     */
    public static <T> T readJson(String content, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return objectMapper.readValue(content, javaType);
    }

}
