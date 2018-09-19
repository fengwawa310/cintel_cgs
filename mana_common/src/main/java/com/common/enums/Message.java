package com.common.enums;

/**
 * 结果描述
 *
 * @author OneDove
 */
public class Message {


    public enum Type {

        SUCCESS,  //成功

        ERROR,  //错误

        WORNING   //警告

    }

    private Message.Type type;//结果类型

    private String context;//结果描述

    private String param0;

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Message(Type type, String context, String param0) {
        super();
        this.type = type;
        this.context = context;
        this.param0 = param0;
    }

    public Message.Type getType() {
        return type;
    }

    public void setType(Message.Type type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getParam0() {
        return param0;
    }

    public void setParam0(String param0) {
        this.param0 = param0;
    }
}
