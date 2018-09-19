package com.common;

import com.alibaba.fastjson.JSONArray;

public class ESTicketResponse {

    private Integer code;
    private JSONArray data;

    public ESTicketResponse() {

    }

    public static ESTicketResponse success(JSONArray data) {
        ESTicketResponse esTicketResponse = new ESTicketResponse();
        esTicketResponse.setCode(ESCode.OK.code);
        esTicketResponse.setData(data);

        return esTicketResponse;
    }

    public static ESTicketResponse error() {
        ESTicketResponse esTicketResponse = new ESTicketResponse();
        esTicketResponse.setCode(ESCode.ERROR.code);

        return esTicketResponse;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }
}
