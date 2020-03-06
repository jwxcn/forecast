package com.hexing.forecast.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    /**
     * 获取两个日期之间的所有日期，包括始末日期
     * @param startDate  开始日期
     * @param endDate   结束日期
     * @return  List集合
     */
    public List<Date> getDates(Date startDate, Date endDate) throws Exception {
        List<Date> list = new ArrayList<>(); //保存日期的集合
        Date date = startDate;
        Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
        while (date.getTime() <= endDate.getTime()){
            list.add(date);
            cd.setTime(date);
            cd.add(Calendar.DATE, 1);//增加一天 放入集合
            date = cd.getTime();
        }
        return list;
    }

    /**
     * 获取几天后的日期
     * @param date
     * @param days
     * @return
     */
    public Date getDaysAfter(Date date, int days) {
        Calendar cd = Calendar.getInstance();
        cd .setTime(date);
        cd.add(Calendar.DATE, days);
        return cd.getTime();
    }

    /**
     * 判断两个日期的年月日是否相等
     * @param date1
     * @param date2
     * @return
     */
    public boolean isSameDay(Date date1, Date date2) {
        Calendar cd1 = Calendar.getInstance();
        Calendar cd2 = Calendar.getInstance();
        cd1.setTime(date1);
        cd2.setTime(date2);
        if (cd1.get(Calendar.YEAR) == cd2.get(Calendar.YEAR) && cd1.get(Calendar.MONTH) == cd2.get(Calendar.MONTH) &&
                cd1.get(Calendar.DAY_OF_MONTH) == cd2.get(Calendar.DAY_OF_MONTH))
            return true;
        return false;
    }

    /**
     * 获取date的hour
     * @param date
     * @return
     */
    public int getHour(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.get(Calendar.HOUR);
    }


}
