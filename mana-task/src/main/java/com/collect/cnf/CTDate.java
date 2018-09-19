package com.collect.cnf;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/18.
 */
public class CTDate {
    private String timeToString;
    private Date time;

    public CTDate(String timeToString, Date time) {
        this.timeToString = timeToString;
        this.time = time;
    }

    public String getTimeToString() {
        return timeToString;
    }

    public Date getTime() {
        return time;
    }
}
