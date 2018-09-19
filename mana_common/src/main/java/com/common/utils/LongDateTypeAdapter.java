package com.common.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Date;

class LongDateTypeAdapter extends TypeAdapter<Date> {
    @Override  
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null|| StringUtils.isBlank(value.toString())) {
            out.nullValue();  
        } else {  
            out.value(value.getTime());  
        }  
    }  
  
    @Override  
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == null) {  
            return null;  
        }  
        String str = in. nextString();
        if(StringUtils.isNotBlank(str)) {//判空
//            Date d = new Date(Long.parseLong(str));
            Date d = null;
            try {
                try {
//                    d = DateHelp.str2Date(str,DateHelp.DATATIMEF_STR);//模式一
                } catch (Exception e) {
//                    d = DateHelp.str2Date(str,DateHelp.DATAFORMAT_STR);//模式二
                }
                d = DateHelp.str2Date(str, DateHelp.DATAFORMAT_STR);//模式二
            } catch (Exception e) {
                e.printStackTrace();
            }
            return d;
        }else
            return null;
    }  
}  