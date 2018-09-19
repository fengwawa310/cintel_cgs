package com.mana.ticket.util;

import java.io.Serializable;
/** 
 * 自定义response
 * @author cuidianlong
 * 2017-04-28
 */
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code; //状态码
    private String time; //时间
    private Object desc; //描述
    private Object data; //数据
    private Integer totalRecord;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }
    

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", time='" + time + '\'' +
                ", desc=" + desc +
                ", data=" + data +
                '}';
    }

    public static BaseResponse success(Object data, String desc, Integer totalRecord) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setData(data);
        baseResponse.setDesc(desc);
        baseResponse.setTotalRecord(totalRecord);

        return baseResponse;
    }

    public static BaseResponse fail(String desc) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("201");
        baseResponse.setDesc(desc);

        return baseResponse;
    }
}
