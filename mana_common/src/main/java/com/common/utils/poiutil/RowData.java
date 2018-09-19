package com.common.utils.poiutil;

import java.util.HashMap;
import java.util.Map;

public class RowData<T>
{
    private int rowNum;

    private T data;

    public RowData(int rowNum)
    {
        super();
        this.rowNum = rowNum;
    }

    public int getRowNum()
    {
        return rowNum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
