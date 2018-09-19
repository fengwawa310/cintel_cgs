package com.common.utils;

import java.io.Serializable;

/**
 * @描述: 返回一个实体对象
 * @作者: sqc
 * @日期: 2016年7月6日
 */
public class ResponseStateVO implements Serializable {

	public static final int SUCCESS = 1;
	public static final int FAIL = 2;
	private int code;
	private String msg;

	public ResponseStateVO(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


}
