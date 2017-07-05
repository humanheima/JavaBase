package com.hm.regular;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dumingwei on 2017/6/20.
 */
public class TimeUtil {

    public static SimpleDateFormat format;
    public static SimpleDateFormat format2;

    public static void main(String[] args) {
        format = new SimpleDateFormat("yyyy年M月d日");
        format2 = new SimpleDateFormat("yyyy-M-d");
        try {
            Date date = format.parse("2017年6月20日");
            System.out.println(format2.format(date));
            System.out.println(getCalendarWeek(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }


    public static String getCalendarWeek(Date date) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        calendar.setTime(date);
        if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH))
            return "今天";
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }
}
