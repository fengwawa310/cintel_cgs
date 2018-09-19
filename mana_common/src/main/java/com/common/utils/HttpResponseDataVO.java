package com.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @描述: 返回一个实体集合
 * @作者: sqc
 * @日期: 2016年7月6日
 */
public class HttpResponseDataVO<T> implements Serializable {

	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	//	返回状态
	private String success;
	//	返回数据
	private List<T> msg;

	public HttpResponseDataVO(String success, List<T> msg) {
		this.success = success;
		this.msg = msg;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public List<?> getMsg() {
		return msg;
	}

	public void setMsg(List<T> msg) {
		this.msg = msg;
	}
}
