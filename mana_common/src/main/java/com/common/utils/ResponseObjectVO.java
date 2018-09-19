package com.common.utils;

import java.io.Serializable;

/**
 * @描述: 返回一个实体对象
 * @作者: sqc
 * @日期: 2016年7月6日
 */
public class ResponseObjectVO<T> implements Serializable {

	public static final int SUCCESS = 1;
	public static final int FAIL = 2;
	private int code;
	private String msg;
	private T data;

	public ResponseObjectVO(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
