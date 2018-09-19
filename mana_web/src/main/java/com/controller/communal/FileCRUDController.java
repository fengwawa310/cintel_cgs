package com.controller.communal;

import com.entity.sys.SysUser;
import com.service.fastdfs.FileCRUDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/fileCRUD")
public class FileCRUDController {
    private static final Logger logger = LoggerFactory.getLogger(FileCRUDController.class);
    @Autowired
    private FileCRUDService fileCRUDService;

    /*
     * 上传文件
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
        Map<String,Object> json = new HashMap<>();
        // 文件转换
        MultipartHttpServletRequest mHttpServletRequest = (MultipartHttpServletRequest) request;
        byte[] bytes = null;
        Map<String, MultipartFile> fileMap = mHttpServletRequest.getFileMap();
        Set<String> keySet = fileMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        String fileName = "";
        String ext_Name = "";
        String responseBody = "";
        List<MultipartFile> fileElementId = mHttpServletRequest.getFiles(iterator.next());
        for (int j = 0; j < fileElementId.size(); j++) {
            // 获取文件名用于返回
            fileName = fileElementId.get(j).getOriginalFilename();
            // 获取文件扩展名
            ext_Name = fileName.split("\\.")[fileName.split("\\.").length - 1];
            try {
                bytes = fileElementId.get(j).getBytes();
                Map<String, byte[]> files = new HashMap<>();
                files.put("bytes", bytes);
                files.put("ext_Name", ext_Name.getBytes());
                files.put("fileName", fileName.getBytes("UTF-8"));
                responseBody = fileCRUDService.upload(files);
                if(responseBody!=null&&!"".equals(responseBody)){
                    json.put("flag",true);
                    json.put("url",responseBody);
                }else{
                    json.put("flag",false);
                }
            } catch (IOException e) {
                json.put("flag",false);
                e.printStackTrace();
                logger.error(e.toString());
            }
        }
        return json;
    }


    /**
     * 下载
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downLoadFile", method = RequestMethod.GET)
    public OutputStream downLoadFile(HttpServletRequest request, HttpServletResponse response,String fileName,String fileURL ) {
        byte[] a= new byte[0];//用tomcat的格式（iso-8859-1）方式去读。
        byte[] b= new byte[0];//用tomcat的格式（iso-8859-1）方式去读。
        OutputStream toClient =null;
        try {
            a = fileName.getBytes("ISO-8859-1");
            b = fileURL.getBytes("ISO-8859-1");
            fileName=new String(a,"utf-8");//采用utf-8去接string
            fileURL=new String(b,"utf-8");//采用utf-8去接string
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int browserType = request.getHeader("User-Agent").toUpperCase().indexOf("MSIE");

        byte[] buffer = fileCRUDService.downLoadFile(fileURL);
        if (buffer != null && !"".equals(buffer)) {
            if (fileName == null || "".equals(fileName)) {
                fileName = fileURL.split("/")[fileURL.split("/").length - 1];
            }
            try {
                // 清空response
                response.reset();
                // 解决中文文件名下载时乱码问题
                if (browserType > 0) {
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                } else {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                }
                // 设置response的Header
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                response.addHeader("Content-Length", "" + buffer.length);
                toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return toClient;
    }

    /*
     * 文件删除方法 相关数据库中的数据请自行删除 如果需要删除多个文件，多个文件的url请用“$$”分割
     */
    @ResponseBody
    @RequestMapping("/deleteFile")
    public String deleteFile(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("loginUser");
        SysUser user = (SysUser) request.getAttribute("user");
        String fileURL = request.getParameter("fileURL");
        String result = fileCRUDService.deleteFile(fileURL);
        return result;
    }

    /*
     * 音频播放功能
     */
    @RequestMapping("/fileToPlay")
	public void fileToPlay(String fileURL, HttpServletRequest request, HttpServletResponse response) {
    	String[] fileStr=fileURL.split("/");
    	int browserType = request.getHeader("User-Agent").toUpperCase().indexOf("MSIE");
    	String fileName="";
    	if(fileStr.length>1){
    		fileName=fileStr[1];
    	}else{
    		fileName=fileURL.split("/")[fileURL.split("/").length - 1];
    	}
    	
    	// 清空response
        response.reset();
        // 解决中文文件名下载时乱码问题
        try {
	        if (browserType > 0) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
	        } else {
	            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
	        }
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }

		byte[] buffer = null;
		if (fileURL != null && !"".equals(fileURL)) {
			if(fileURL.startsWith("/")){
				buffer=fileCRUDService.downLoadFile(fileURL);
			}else{
				buffer=fileCRUDService.downLoadFile("/"+fileURL);
			}
		}
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.addHeader("Content-Length", "" + buffer.length);
		response.setContentType("application/octet-stream");
		OutputStream os;
		try {
			os = response.getOutputStream();
			os.write(buffer);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
