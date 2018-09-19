package com.entity.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.Date;
import java.util.List;

public class JsonUtil {

	public static <T> JSONArray listToJson(List<T> list) {

		// list转成json
        JsonConfig config = new JsonConfig();
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
        config.registerJsonValueProcessor(String.class, jsonValueProcessor);
		return JSONArray.fromObject(list,config);
	}

	public <T> String objToJsonStr(T obj)
	{
		/**将list集合众的数据转换成json格式的字符串形式*/
		JsonConfig config = new JsonConfig();
		JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();
		config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		config.registerJsonValueProcessor(String.class, jsonValueProcessor);
		String jsonStr = JSONObject.fromObject(obj,config).toString();
		return jsonStr;
	}

	/*public List<SrcAlarmEntity> JsonToList(String json) {

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<SrcAlarmEntity> list2 = (List) JSONArray.toCollection(jsonArray);
		return list2;
	}*/
}
