package com.kcs.rest.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//返回系统当前的年份，月份，直接调用 如 GetTime.YEAR_MONTH,格式为 2019-12
public class GetTime {
    static Calendar cal = Calendar.getInstance();
    static SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String YEAR_MONTH;

    public static String TIME;


    //返回当前时间，yyyy-MM-dd
    public static String getTime(){
        TIME = dateFormat.format(cal.getTime());
        return TIME;
    }

    //返回年月，yyyy-MM
    public String getYearMonth(){
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = null;
        int m = cal.get(Calendar.MONTH)+1;
        if (m<10){
            month ="0"+String.valueOf(m);
        }
        else
            month = String.valueOf(m);
        YEAR_MONTH =year+"-"+month;
        return YEAR_MONTH;
    }
}
