package com.service.fastdfs.impl;

import com.service.fastdfs.FastDfsUtil;
import com.service.fastdfs.FileCRUDService;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.jboss.resteasy.plugins.server.sun.http.HttpServerRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import java.io.*;
import java.util.Map;

@Transactional
@Service("fileCRUDService")
public class FileCRUDServiceImpl implements FileCRUDService {

//	private static String fdfs_client = FileCRUDServiceImpl.class.getClassLoader().getResource("business.properties")
//			.getFile().split("WEB-INF")[0]+"WEB-INF"+ File.separator+"classes"+File.separator+"business.properties";
    private static String fdfs_client = FastDfsUtil.class.getClassLoader().getResource("resource/fastdfs.properties")
            .getFile();
	@Resource
	private FastDfsUtil fastDfsUtil;

	/*
	 * 上传文件
	 */
	@Override
	public String upload(Map<String,byte[]> files) {
		String ext_Name= null;
		String fileName= null;
		try {
			ext_Name=new String(files.get("ext_Name"),"utf-8");
			fileName = new String(files.get("fileName"),"utf-8");
		} catch (UnsupportedEncodingException e) {

		}
		String responseBody = "";
		byte[] bytes=null;
		bytes = files.get("bytes");
		String videoPath = uploadFile(bytes, ext_Name);
		responseBody = videoPath + "$$" + fileName + "@@";
		if (!"".equals(responseBody)) {
			responseBody = responseBody.substring(0, responseBody.length() - 2);
		}

		return responseBody;
	}
	public String upload(HttpServerRequest files,String ext_Name,String fileName) {
		MultipartRequest multipartRequest=(MultipartRequest) files;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = fileMap.get(multipartRequest.getFileNames().next());
		InputStream inputStream=null;
		try {
			inputStream = multipartFile.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		String responseBody = "";
		byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
		int rc = 0;
		byte[] in_b =null;
		try {
			while ((rc = inputStream.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
				swapStream.toByteArray(); //in_b为转换之后的结果
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String videoPath = uploadFile(in_b, ext_Name);
		responseBody = videoPath + "$$" + fileName + "@@";
		if (!"".equals(responseBody)) {
			responseBody = responseBody.substring(0, responseBody.length() - 2);
		}

		return responseBody;
	}

	public String uploadFile(byte[] byteFile, String ext_file) {
		StringBuffer sbPath = new StringBuffer();
		try {
			// 初始化文件资源
			ClientGlobal.init(fdfs_client);

			// 链接FastDFS服务器，创建tracker和Stroage
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			// 利用字节流上传文件
			String[] strings = storageClient.upload_file(byteFile, ext_file, null);
			trackerServer.close();
			for (String string : strings) {
				sbPath.append("/" + string);
				System.out.println(string);
			}
			// 全路径
			System.out.println(sbPath);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
		return sbPath.toString();
	}



	@Override
//	@Log(value="公共下载",params={@LogParam("下载地址")}, module = "1",type="4")
	public byte[] downLoadFile(String fileURL) {
		byte[] buffer = null;
		fileURL=fileURL.split("\\$\\$")[0];
		if (fileURL != null && !"".equals(fileURL)) {
			String groupName = fileURL.split("/")[1];
			String url = fileURL.split(groupName + "/")[1];
			try {
				ClientGlobal.init(fdfs_client);
				TrackerClient tracker = new TrackerClient();
				TrackerServer trackerServer = tracker.getConnection();
				StorageServer storageServer = null;
				StorageClient storageClient = new StorageClient(trackerServer, storageServer);
				// 从文件服务器获取文件字节
				buffer = storageClient.download_file(groupName, url);
			} catch (IOException | MyException ex) {
				ex.printStackTrace();
			}
		}
//		InputStream input = new ByteArrayInputStream(buffer);
		return buffer;
	}

	/*
	 * 文件删除方法 相关数据库中的数据请自行删除 如果需要删除多个文件，多个文件的url请用“$$”分割
	 */
	@Override
	public String deleteFile(String fileURL) {
		String result = "false";
		if (fileURL != null && !"".equals(fileURL)) {
			String[] urls = fileURL.split("\\$\\$");
			for (String fUrl : urls) {
				String groupName = fUrl.split("/")[1];
				String url = fUrl.split(groupName + "/")[1];
				try {
					ClientGlobal.init(fdfs_client);
					TrackerClient tracker = new TrackerClient();
					TrackerServer trackerServer = tracker.getConnection();
					StorageServer storageServer = null;
					StorageClient storageClient = new StorageClient(trackerServer, storageServer);
					/*
						删除fastDFS上的文件
					 */
					try {
						int i = storageClient.delete_file(groupName, url);
						System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);
						result = (i == 0 ? "true" : "false");
						trackerServer.close();
					} catch (Exception e) {
						trackerServer.close();
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}


	@Override
	public String poolUpload(Map<String,byte[]> files){
		String upload = fastDfsUtil.upload(files);
		return upload;
	}

	@Override
	public String poolDelete(String fileName){
		String upload = fastDfsUtil.deleteFile(fileName);
		return upload;
	}
	@Override
	public byte[] poolDownload(String fileName){
		byte[] bytes = fastDfsUtil.downLoadFile(fileName);
		return bytes;
	}


}
