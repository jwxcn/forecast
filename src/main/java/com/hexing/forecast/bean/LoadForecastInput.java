package com.hexing.forecast.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 负荷预测输入实体类
 */
@Getter
@Setter
public class LoadForecastInput {
    private double temperature;  //预测日温度
    private double oneDayBeforeTemperature;  //预测日前一天的温度
    private double oneWeekBeforeTemperature;   //预测日前一周的温度

    private double oneHourBeforeLoad;  //预测日t时刻上一小时负荷
    private double oneDayBeforeLoad;   //预测日前一日t时刻负荷
    private double oneWeekBeforeLoad;  //预测日前一周t时刻负荷

    private int WeekType;  //预测日星期几
    private int IsHoliday;  //预测日是否是节假日
    private int oneDayBeforeIsHoliday;  //预测日前一日是否是节假日
    private int oneWeekBeforeIsHoliday;  //预测日前一周是否是节假日
}
