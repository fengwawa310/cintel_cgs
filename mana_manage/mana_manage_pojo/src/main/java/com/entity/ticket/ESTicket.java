package com.entity.ticket;

import java.util.HashSet;

import javax.swing.plaf.synth.SynthStyle;

/**
 * es话单对应pojo对象
 */
public class ESTicket {

    private String calling;
    private String called;
    private String start_Time;
    private Integer calling_Time;
    private String calling_Place;
    private String calling_type;
    
	public String getCalling_type() {
        return calling_type;
    }
    public void setCalling_type(String calling_type) {
        this.calling_type = calling_type;
    }
    public String getCalling() {
		return calling;
	}
	public void setCalling(String calling) {
		this.calling = calling;
	}
	public String getCalled() {
		return called;
	}
	public void setCalled(String called) {
		this.called = called;
	}
	public String getStart_Time() {
		return start_Time;
	}
	public void setStart_Time(String start_Time) {
		this.start_Time = start_Time;
	}
	public Integer getCalling_Time() {
		return calling_Time;
	}
	public void setCalling_Time(Integer calling_Time) {
		this.calling_Time = calling_Time;
	}
	public String getCalling_Place() {
		return calling_Place;
	}
	public void setCalling_Place(String calling_Place) {
		this.calling_Place = calling_Place;
	}
    @Override
    public int hashCode() {
        return calling.hashCode()+called.hashCode()+start_Time.hashCode()+calling_Time.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ESTicket other = (ESTicket) obj;
        if (called == null) {
            if (other.calling != null)
                return false;
        } else if (!called.equals(other.calling))
            return false;
        
        if (calling == null) {
            if (other.called != null)
                return false;
        } else if (!calling.equals(other.called))
            return false;
        
        
        if (calling_Time == null) {
            if (other.calling_Time != null)
                return false;
        } else if (!calling_Time.equals(other.calling_Time))
            return false;
        
        if (start_Time == null) {
            if (other.start_Time != null)
                return false;
        } else if (!start_Time.equals(other.start_Time))
            return false;
        return true;
    }
   
    @Override
    public String toString() {
        return "ESTicket [calling=" + calling + ", called=" + called + ", start_Time=" + start_Time + ", calling_Time="
                + calling_Time + ", calling_Place=" + calling_Place + ", calling_type=" + calling_type + "]";
    }
	
}
