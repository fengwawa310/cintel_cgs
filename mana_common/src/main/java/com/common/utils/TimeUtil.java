package com.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/18.
 */
public class TimeUtil {

    /**
     * @param time
     * @param formatStr 例如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateToStr(Date time, String formatStr) {
        if (time == null) {
            time = new Date();
        }
        if(StringHelp.isEmpty(formatStr))
        {
            formatStr = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String timeStr = sdf.format(time);
        return timeStr;
    }

    /**
     * 将时间字符串依照指定格式 转变为java.util.Date
     *
     * @param timeStr   例如 2017-12-18 00:00:00
     * @param formatStr 例如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseTimeStr(String timeStr, String formatStr) {
        if (StringHelp.isEmpty(timeStr) || StringHelp.isEmpty(formatStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date beginTime = null;
        try {
            beginTime = sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            LogUtils.info("时间解析失败 timeStr:" + timeStr + " ,formatStr:" + formatStr);
        }
        return beginTime;
    }


    /**
     * 将map中的时间类型统一成 yyyy-MM-dd HH:mm:ss
     * @param rowData
     * @return
     */
    public static Map<String,Object> toDateMap(Map<String,Object> rowData){
        //时间转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (String key:rowData.keySet()) {
            String value = rowData.get(key).toString();
            if(value.length()<=10&&value.indexOf("/")==4&&(value.lastIndexOf("/")==6||value.lastIndexOf("/")==7)){
                value=value.replace("/","-");
            }
            //2012-10-01转换yyyy-MM-dd HH:mm:ss
            if(value.indexOf("-")==4&&(value.lastIndexOf("-")==6||value.lastIndexOf("-")==7)){
                if(value.length()<19){
                    if(value.length()==8||value.length()==9||value.length()==10){
                        value+=" ";
                    } if(value.length()==9||value.length()==10||value.length()==11){
                        value+="00";
                    } if(value.length()==11||value.length()==12||value.length()==13){
                        value+=":00";
                    } if(value.length()==14||value.length()==15||value.length()==16){
                        value+=":00";
                    }
                }else if(value.length()>19){
                    if(value.indexOf("AM")!=-1){
                        value=value.replace(" AM","");
                    } if(value.indexOf("PM")!=-1){
                        value=value.replace(" PM","");
                    } if(value.indexOf(".0")!=-1){
                        value=value.replace(".0","");
                    }
                }
            }
            //Thu Feb 01 09:00:00 CST 2018转换yyyy-MM-dd HH:mm:ss
            if(value.indexOf("CST")!=-1
                    ||value.indexOf("CDT")!=-1
                    ||value.indexOf("EDT")!=-1
                    ||value.indexOf("EST")!=-1
                    ||value.indexOf("GMT")!=-1
                    ||value.indexOf("PST")!=-1
                    ||value.indexOf("PDT")!=-1
                    ||value.indexOf("UTC")!=-1
                    ){
                SimpleDateFormat simdate = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
                Date date;
                try {
                    date = simdate.parse(rowData.get(key).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }
                value = sdf.format(date);
            }
            //201210010708转换yyyy-MM-dd HH:mm:ss
            if(key.indexOf("ETIME")!=-1
                    ||key.indexOf("RTIME")!=-1
                    ||key.indexOf("STIME")!=-1
                    ||key.indexOf("INPUTTIME")!=-1
                    ||key.indexOf("CREATETIME")!=-1
                    ){
                SimpleDateFormat simdate = new SimpleDateFormat("yyyyMMddHHmmss");
                if(value.length()<14){
                    int j = 14 - value.length();
                    for (int i=0;i<j;i++){
                        value+="0";
                    }
                }
                try {
                    Date parse = simdate.parse(value);
                    value=sdf.format(parse);
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            rowData.put(key,value);
        }
        return rowData;
    }
}
