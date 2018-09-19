package com.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @描述: 返回一个实体集合
 * @作者: sqc
 * @日期: 2016年7月6日
 */
public class ResponseVO implements Serializable {

	public static final int SUCCESS = 1;
	public static final int FAIL = 2;
	//	返回状态
	private int code;
	//	返回信息描述
	private String cmd;
	//	返回数据
	private List<?> data;

	public ResponseVO(int code, String cmd, List<?> data) {
		this.code = code;
		this.cmd = cmd;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}
