package com.service.fastdfs;

import java.util.Map;

public interface FileCRUDService {

	// 文件上传
	public String upload(Map<String, byte[]> files);

	// 文件下载
	public byte[] downLoadFile(String fileURL);

	// 文件删除
	public String deleteFile(String fileURL);

	// 文件上传
	public String poolUpload(Map<String, byte[]> files);

	// 文件删除
	String poolDelete(String fileName);
	// 文件下载
	byte[] poolDownload(String fileName);
}
