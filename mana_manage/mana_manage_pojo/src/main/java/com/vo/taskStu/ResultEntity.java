package com.vo.taskStu;

import org.apache.poi.ss.formula.functions.T;

/**
 * @Author: sky
 * @Description: 通用response返回值
 * @Date: Create in 上午11:45 2018/4/23
 */
public class ResultEntity {

    private String code;
    private String msg;
    private Object data;

    public ResultEntity() {}

    public ResultEntity(String code, String msg, T data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
