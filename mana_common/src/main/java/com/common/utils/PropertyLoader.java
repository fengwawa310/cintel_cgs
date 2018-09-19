package com.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ticket.model.TitleMapper;

public class PropertyLoader {
	
	public static Properties loadProperties(String url) {
		Properties prop = new Properties();     
        try{
            //读取属性文件properties
            InputStream in = new BufferedInputStream (new FileInputStream(url));
            prop.load(new InputStreamReader(in, "utf-8"));     ///加载属性列表
//            Iterator<String> it=prop.stringPropertyNames().iterator();
//            while(it.hasNext()){
//                String key=it.next();
//                System.out.println(key+":"+prop.getProperty(key));
//            }
            in.close();
            
//            保存属性到b.properties文件
//            FileOutputStream oFile = new FileOutputStream("b.properties", true);//true表示追加打开
//            prop.setProperty("phone", "10086");
//            prop.store(oFile, "The New properties file");
//            oFile.close();
            
        }
        catch(Exception e){
            System.out.println(e);
        }

        return prop;
	}
	
	
	public static ArrayList<TitleMapper> loadTitleMapper(){
		ArrayList<TitleMapper> titlemapper = null;
		try {
			String readTxtFile = TxtUtil.readTxtFile(new File(PathUtil.getProjectPath() + "\\" + "titlemapper.json"));
			
			titlemapper = JSON.parseObject(readTxtFile, new TypeReference<ArrayList<TitleMapper>>() {});
			
//			for(TitleMapper one : titlemapper) {System.out.println(one.getTitlename());}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return titlemapper;
	}
	
}
