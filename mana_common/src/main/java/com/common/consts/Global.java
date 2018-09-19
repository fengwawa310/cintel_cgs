package com.common.consts;

import com.common.utils.httpclient.PropertiesLoader;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 全局静态对象类
 * @version 2016-1-15 上午11:10:04
 */
public class Global
{
	/**单例设计模板（此处无任何意义）*/
	private Global(){}
	private static Global instance = null;
	public synchronized static Global getInstance() {
		if (instance == null)
			instance = new Global();
		return instance;
	}
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * config属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("resource"+File.separator+"config.properties");
	/**
	 * db属性文件加载对象
	 */
	private static PropertiesLoader dbpropertiesLoader = new PropertiesLoader("resource"+File.separator+"db.properties");

	/**
	 * es属性文件加载对象
	 */
	private static PropertiesLoader espropertiesLoader = new PropertiesLoader("resource"+File.separator+"es.properties");
	
	/**
	 * task属性文件加载对象
	 */
	private static PropertiesLoader taskpropertiesLoader = new PropertiesLoader("resource"+File.separator+"task.properties");
	/**
	 * message属性文件加载对象
	 */
	private static PropertiesLoader messagepropertiesLoader = new PropertiesLoader("resource"+File.separator+"message.properties");

	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
 		String value = map.get(key);
		if (StringUtils.isBlank(value)){
			value = propertiesLoader.getProperty(key);
			map.put(key, value != null ? value : "");
		}
		try {
			return new String(value.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取配置
	 */
	public static String getUnicodeConfig(String key) {
		String value = map.get(key);
		if (StringUtils.isBlank(value)){
			value = propertiesLoader.getProperty(key);
			map.put(key, value != null ? value : "");
		}
		try {
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取db配置
	 */
	public static String getDb(String key) {
 		String value = map.get(key);
		if (StringUtils.isBlank(value)){
			value = dbpropertiesLoader.getProperty(key);
			map.put(key, value != null ? value : "");
		}
		try {
			return new String(value.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取es配置
	 */
	public static String getEs(String key) {
 		String value = map.get(key);
		if (StringUtils.isBlank(value)){
			value = espropertiesLoader.getProperty(key);
			map.put(key, value != null ? value : "");
		}
		try {
			return new String(value.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取task配置
	 */
	public static String getTask(String key) {
		String value = map.get(key);
		if (StringUtils.isBlank(value)){
			value = taskpropertiesLoader.getProperty(key);
			map.put(key, value != null ? value : "");
		}
		try {
			return new String(value.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取message配置
	 */
	public static String getMessage(String key) {
		String value = map.get(key);
		if (StringUtils.isBlank(value)){
			value = messagepropertiesLoader.getProperty(key);
			map.put(key, value != null ? value : "");
		}
		try {
			return new String(value.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
