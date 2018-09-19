package com.mana.ticket.model;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CallRecord {

    private String called = "";
    private String calling = "";
    private String calling_place = ""; //通话地
    private String calling_time; //通话时长
    private String remake = ""; //备注
    private String calling_type = ""; //呼叫类型
    private String start_time = ""; //开始时间

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getCalling() {
        return calling;
    }

    public void setCalling(String calling) {
        this.calling = calling;
    }

    public String getCalling_place() {
        return calling_place;
    }

    public void setCalling_place(String calling_place) {
        this.calling_place = calling_place;
    }

    public String getCalling_time() {
        return calling_time;
    }

    public void setCalling_time(String calling_time) {
        this.calling_time = calling_time;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getCalling_type() {
        return calling_type;
    }

    public void setCalling_type(String calling_type) {
        this.calling_type = calling_type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    /**
     * 返回XContentBuilder对象
     * @return
     * @throws IOException
     */
    public XContentBuilder getXContentBuilder() throws IOException {
        return XContentFactory.jsonBuilder()
                .startObject()
                .field("CALLED", called)
                .field("CALLING", calling)
                .field("CALLING_PLACE", calling_place)
                .field("CALLING_TIME", getCallingTime())
                .field("REMAKE", remake)
                .field("CALLING_TYPE", calling_type)
                .field("START_TIME", start_time)
                .endObject();
    }

    /**
     * 获取时长（秒）
     * @return
     */
    private Integer getCallingTime() {
    	Integer result = Integer.valueOf(0);
    	if(StringUtils.isBlank(calling_time))
    		return result;
    	
    	String tmp_calling_time = String.valueOf(calling_time);
    	try {
    		if(tmp_calling_time.contains("秒") || tmp_calling_time.contains("时") || tmp_calling_time.contains("分")) { //格式 12时12分12秒
    			int hour = 0,minute = 0,second = 0;
    			if(tmp_calling_time.contains("时")) {
    				int startIndex = tmp_calling_time.indexOf("时");
    				String hourStr = tmp_calling_time.substring(0, startIndex);
    				hour = Integer.parseInt(hourStr);
    				if(!tmp_calling_time.endsWith("时"))
    					tmp_calling_time = tmp_calling_time.substring(startIndex + 1);
    			}
    			if(tmp_calling_time.contains("分")) {
    				int startIndex = tmp_calling_time.indexOf("分");
    				String minuteStr = tmp_calling_time.substring(0, startIndex);
    				minute = Integer.parseInt(minuteStr);
    				if(!tmp_calling_time.endsWith("分"))
    					tmp_calling_time = tmp_calling_time.substring(startIndex + 1);
    			}
    			if(tmp_calling_time.contains("秒")) {
    				int endIndex = tmp_calling_time.indexOf("秒");
    				String secondStr = tmp_calling_time.substring(0,endIndex);
    				second = Integer.parseInt(secondStr);
    			}
    			result = Integer.valueOf(hour * 60 * 60 + minute * 60 + second);
    		} else if (tmp_calling_time.contains(":")) { //格式: 12:12:12
    			Integer total = 0;
    			String[] strings = tmp_calling_time.split(":");
    			if(strings.length > 2 && StringUtils.isNotBlank(strings[2])) { //存在时
    				total += Integer.parseInt(strings[0]) * 60 * 60;
    				total += Integer.parseInt(strings[1]) * 60;
    				total += Integer.parseInt(strings[2]);
    			} else {
    				total += Integer.parseInt(strings[0]) * 60;
    				total += Integer.parseInt(strings[1]);
    			}
    			
    			result = Integer.valueOf(total);
    		}else {
    			result = Integer.valueOf(tmp_calling_time);
    		}
    	}catch(Exception e) {
    		System.out.println(e);
    	}

        return result;
    }

}
