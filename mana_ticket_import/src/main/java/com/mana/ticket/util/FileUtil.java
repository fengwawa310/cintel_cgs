package com.mana.ticket.util;

import java.io.File;
import java.util.Random;

public class FileUtil {
	public static void moveTotherFolders(String fromPath,String fileName,String toPath){
//	    String startPath = fromPath + File.separator + fileName;
//	    String endPath = toPath;
	    try {
	        File startFile = new File(fromPath);
	        File tmpFile = new File(toPath);//获取文件夹路径
	        if(!tmpFile.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
	            tmpFile.mkdirs();
	        }
	        
	        File toFile = new File(toPath + File.separator + startFile.getName());
	        if(toFile.exists()) {
	        	//文件名
				String fn = startFile.getName().substring(0, startFile.getName().lastIndexOf("."));
				//扩展名
				String kz = startFile.getName().substring(startFile.getName().lastIndexOf("."));
				Random rand = new Random();

	        	toFile = new File(toPath + File.separator + fn+"_"+(rand.nextInt(100)+1)+kz);
	        }
	        
	        if(startFile.exists()) {
	        	if (startFile.renameTo(toFile)) {
	        		System.out.println("File is moved successful!");
	        	} else {
	        		System.out.println("File is failed to move!" + startFile.getAbsolutePath());
	        	}
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void moveTotherFolders(File sourceFile,String fileName,String toPath){
//	    String startPath = fromPath + File.separator + fileName;
//	    String endPath = toPath;
		try {
			File startFile = sourceFile;
			File tmpFile = new File(toPath);//获取文件夹路径
			if(!tmpFile.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
				tmpFile.mkdirs();
			}
			if (startFile.renameTo(new File(toPath + File.separator + fileName))) {
				System.out.println("File is moved successful!");
			} else {
				System.out.println("File is failed to move!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
