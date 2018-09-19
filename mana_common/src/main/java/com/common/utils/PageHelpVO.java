package com.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * dataTable-数据返回包装类。
 * Created by admin on 2017/6/14.
 */
public class PageHelpVO<T> implements Serializable {

	private Long total;

    private List<T> list= new ArrayList<>();

    public PageHelpVO() {
        this.total = 0l;
        this.list = Collections.emptyList();
    }

    public PageHelpVO( List<T> list) {
        this.list = list;
    }
    public PageHelpVO(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
