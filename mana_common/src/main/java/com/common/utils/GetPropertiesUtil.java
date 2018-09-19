package com.common.utils;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class GetPropertiesUtil {
	
	//获取整个配置文件(business.properties)
	public static Map<String, String> findProperties() {
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = new Properties();
		try {
			InputStream inStream = GetPropertiesUtil.class.getClassLoader().getResourceAsStream("business.properties");
			prop.load(inStream); /// 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				map.put(key, prop.getProperty(key));
			}
			inStream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	}
	
	//根据key名称，获取单个配置文件(business.properties)中的配置
	public static String findPropertie(String name) {
		String result="";
		Properties prop = new Properties();
		try {
			InputStream inStream = GetPropertiesUtil.class.getClassLoader().getResourceAsStream("business.properties");
			prop.load(inStream); /// 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if(key.equals(name)){
					result=prop.getProperty(key);
					break;
				}
			}
			inStream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	//获取整个配置文件
	public static Map<String, String> findProperties(String proName) {
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = new Properties();
		try {
			InputStreamReader inStream = new InputStreamReader(GetPropertiesUtil.class.getClassLoader().getResourceAsStream(proName), "UTF-8");
			prop.load(inStream); /// 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				map.put(key, prop.getProperty(key));
			}
			inStream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	}
	
	//根据key名称，获取单个配置文件
	public static String findSingleProperties(String fileName,String keyName) {
		String result = "";
		Properties prop = new Properties();
		try {
			InputStreamReader inStream = new InputStreamReader(GetPropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
			prop.load(inStream); /// 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if(key.equals(keyName)){
					result=prop.getProperty(key);
					break;
				}
			}
			inStream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * 传递键值对的Map，更新properties文件
	 *
	 * @param fileName
	 *            文件名(放在resource源包目录下)，需要后缀
	 * @param keyValueMap
	 *            键值对Map
	 */
	public static void updateProperties(String fileName,Map<String, String> keyValueMap) {
		// InputStream
		// inputStream=PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);//输入流
		String filePath = PropertiesUtil.class.getClassLoader()
				.getResource(fileName).getFile();// 文件的路径
		System.out.println("propertiesPath:" + filePath);
		Properties props = new Properties();
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			// 从输入流中读取属性列表（键和元素对）
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			props.load(br);
			br.close();

			// 写入属性文件
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
//			props.clear();// 清空旧的文件
			for (String key : keyValueMap.keySet())
				props.setProperty(key, keyValueMap.get(key));
			props.store(bw, "");
			bw.close();
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating " + ""+ " value error");
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		

}
