package com.st.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName: DateUtil
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/14 11:50
 */
public class DateUtil {

    public static String getDateStart(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
        cld.setTimeInMillis(System.currentTimeMillis());//当前时间
        cld.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        String startTime = df.format(cld.getTime());
        return startTime;
    }

    public static String getDateEnd(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY + 7);//周日
        cld.add(Calendar.DATE,7);
        String endTime = df.format(cld.getTime());
        return endTime;
    }

    public static void main(String[] args) {
        System.out.println(getDateStart());
        System.out.println(getDateEnd());
    }
}
