package com.vo.ticket;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 话单查询包装类
 */
public class TicketVo {

    private String startTime;
    private String finishTime;
    private String phoneNumber;
    private String prevNumber;
    private Integer showLevel; //展示层级 2层或者3层
    private Integer moveMessage; //流转信息 1:通话频次 2:通话时长
    
    public String getPrevNumber() {
		return prevNumber;
	}

	public void setPrevNumber(String prevNumber) {
		this.prevNumber = prevNumber;
	}

	public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getShowLevel() {
        return showLevel;
    }

    public void setShowLevel(Integer showLevel) {
        this.showLevel = showLevel;
    }

    public Integer getMoveMessage() {
        return moveMessage;
    }

    public void setMoveMessage(Integer moveMessage) {
        this.moveMessage = moveMessage;
    }
}
