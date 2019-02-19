package com.yf.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换类
 * @author ouyangyufeng
 */
public class DateUtil {

    private static final String CUR_TIME_FORMAT="YYYYMMddhhmmssSSS";

    private static final String CUR_TIME_FORMAT2="yyyy-MM-dd HH:mm:ss";


    /**
     * 获取当前时间，精确到毫秒级
     * @param time：毫秒值
     * @return
     */
    public String getCurrentTime(long time){
        Date date = new Date(time);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat(CUR_TIME_FORMAT2);
        return dateFormat.format(date);
    }

    /**
     * 时间戳日期
     * @param seconds：时间戳
     * @param format：日期格式
     * @return
     */
    public static Date timeStamp2Date(String seconds,String format) {
                 if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
                         return new Date();
                     }
                 if(format == null || format.isEmpty()){
                         format = "yyyy-MM-dd HH:mm:ss";
                     }
                 SimpleDateFormat sdf = new SimpleDateFormat(format);
                 return new Date(Long.valueOf(seconds+"000"));
    }

    public static void main(String[] args){

    }
}
