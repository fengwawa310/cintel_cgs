package com.vo.ticket;

public enum ESCode {

    OK(200),
    ERROR(500);

    public Integer code;

    private ESCode(Integer code) {
        this.code = code;
    }

}
