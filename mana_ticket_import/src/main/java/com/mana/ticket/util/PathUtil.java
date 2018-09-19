package com.mana.ticket.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PathUtil {
	
	/**
	 * 获取程序运行的绝对路径
	 * @return
	 */
	public static String getProjectPath() {
	       java.net.URL url = PathUtil.class.getProtectionDomain().getCodeSource().getLocation();
	       String filePath = null;
	       try {
	           filePath = java.net.URLDecoder.decode (url.getPath(), "utf-8");
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	    if (filePath.endsWith(".jar"))
	       filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
	    java.io.File file = new java.io.File(filePath);
	    filePath = file.getAbsolutePath();
	    return filePath;
	}
}
