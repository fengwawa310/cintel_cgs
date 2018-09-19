package com.common.utils;

import java.io.*;
import java.util.Random;

/**
 * Name:FileUtil <br/>
 * Created by "Auri" on 2017/6/13 16:46 <br/>
 * Description:
 */
public class FileUtil {
    /**
     * 文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean isExists(File file) {
        boolean result = file.exists() && file.isFile();
        return result;
    }

    /**
     * @param path 文件路径
     * @return true存在;false不存在
     * @Description:判断文件是否存在<br/>
     * @author:Auri<br/>
     */
    public static boolean isExists(String path) {
        if (StringHelp.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return isExists(file);
    }

    /**
     * @param src 若源文件为空 则直接返回
     * @param des 若目标文件为空 则先根据路径创建一空文件 在执行拷贝
     * @throws IOException
     * @Description:文件拷贝方法<br/>
     * @author:Auri<br/>
     */
    private static void copyFile(String src, String des) {
        if (StringHelp.isEmpty(src) || StringHelp.isEmpty(des)) {
            return;
        }
        // 检验源文件
        boolean isSrcFileExists = isExists(src);
        if (!isSrcFileExists) {
            return;
        }
        // 检验目标文件
        boolean isDesFileExists = isExists(des);
        if (!isDesFileExists) {
            File file = new File(des);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        InputStream in = null;
        OutputStream out = null;
        try {
            in = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(src)));

            out = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(des)));

            byte[] b = new byte[1024 * 2];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 依据入参路径，创建目录
     *
     * @param dir
     */
    public static void createDir(String dir) {
        File file = new File(dir);
        createDir(file);
    }

    /**
     * 依据入参路径，创建目录
     *
     * @param file
     */
    public static void createDir(File file) {
        if (file == null) {
            return;
        }
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
    }

    /**
     * 从完整路径中提取文件所在目录路径
     *
     * @param filePath 文件完整路径
     * @return
     */
    public static String getFileDirFromPath(String filePath) {
        int temp = filePath.lastIndexOf(File.separator);
        if (temp > 0) {
            return filePath.substring(0, temp);
        }
        return filePath;
    }

    /**
     * 从完整路径中提取文件后
     *
     * @param filePath 文件完整路径
     * @return
     */
    public static String getFileNameFromPath(String filePath) {
        int temp = filePath.lastIndexOf(File.separator);
        if (temp > 0) {
            return filePath.substring(temp + 1, filePath.length());
        }
        return filePath;
    }

    /**
     * 从完整文件名中 截取文件真实名称
     *
     * @param fileName
     * @return
     */
    public static String getRealNameFromFileName(String fileName) {
        int temp = fileName.lastIndexOf('.');
        if (temp > 0) {
            return fileName.substring(0, temp);
        }
        return fileName;
    }

    /**
     * 从完整文件名中 截取文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String getSuffixNameFromFileName(String fileName) {
        int temp = fileName.lastIndexOf('.');
        String suffixName = temp == -1 ? fileName : fileName.substring(temp+1,fileName.length());
        return suffixName;
    }

    public static String checkAndReformDuplicateFileName(String filePath) {
        File file = new File(filePath);
        if (file == null) {
            return filePath;
        }
        if (file.exists()) {
            if (!file.isFile()) {
                return filePath;
            }
            String newFilePath = reformWhenDuplicateFileName(filePath);
            return checkAndReformDuplicateFileName(newFilePath);
        }
        return filePath;
    }

    /**
     * 当文件重名时 重新构造一个文件名 如：abc.txt -> abc_1.txt
     *
     * @param filePath 包含后缀名和路径的完整文件路径
     * @return
     */
    public static String reformWhenDuplicateFileName(String filePath) {
        String fileDir = getFileDirFromPath(filePath);
        String fileName = getFileNameFromPath(filePath);
        String realName = getRealNameFromFileName(fileName);
        String suffixName = getSuffixNameFromFileName(fileName);
        int temp = realName.lastIndexOf("--");
        if (temp > 0) {
            // 文件真名中有'--'
            if (temp == realName.length() - 1) {
                // 文件真名最后一个字符就是 '--' 直接赋值 '1'
                realName += '1';
            } else {
                // 文件真名中有'--' 判断后面字符是否是一整数
                String temp_i = realName.substring(temp + 1, realName.length());
                int temp_int = -1;
                try {
                    temp_int = Integer.parseInt(temp_i);
                } catch (NumberFormatException e) {
                }
                if (temp_int < 0) {
                    // 文件名中'--'后面不是一个整数字符 重新拼接 '--'
                    realName += "--1";
                } else {
                    // 文件名中'--'后面是一个整数 整数字符加1
                    String temp_ii = realName.substring(0, temp);
                    temp_int++;
                    realName = temp_ii + "--" + temp_int;
                }
            }
        } else {
            realName += "--1";
        }
        return fileDir + File.separator + realName + '.' + suffixName;
    }
    
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

}
