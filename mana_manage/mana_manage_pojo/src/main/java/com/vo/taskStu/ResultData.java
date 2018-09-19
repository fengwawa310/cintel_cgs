package com.vo.taskStu;

import org.apache.poi.ss.formula.functions.T;

/**
 * @Author: sky
 * @Description: 通用response返回值
 * @Date: Create in 上午11:45 2018/4/23
 */
public class ResultData {

    private String code;
    private String msg;
    private T data;

    public ResultData() {}

    public ResultData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
