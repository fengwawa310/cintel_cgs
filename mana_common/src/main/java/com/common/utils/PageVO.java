package com.common.utils;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/14.
 *
 * dataTable-分页使用
 */
public class PageVO implements Serializable {

    protected Integer start;

    protected Integer length;

    public PageVO() {
    }

    public PageVO(Integer start, Integer length) {
        if(start.intValue()==0){
            this.start = 1;
        }else {
            this.start=start/length+1;
        }
        this.length = length;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
