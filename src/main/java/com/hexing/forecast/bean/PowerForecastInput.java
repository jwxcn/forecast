package com.hexing.forecast.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 发电预测输入实体类
 * 预测日前一天8时~18时发电量，预测日和预测日前一天的最高温度、最低温度、天气状况、湿度
 */
@Getter
@Setter
public class PowerForecastInput extends PowerForecastOutput {
    private double maxTemperatureYes;  //预测日前一天的最高环境温度
    private double minTemperatureYes;  //预测日前一天的最低环境温度
    private double maxTemperature;  //预测日最高环境温度
    private double minTemperature;  //预测日最低环境温度
    private double panelTemperatureYes;  //预测日前一天的电池板温度
    private double panelTemperature;  //预测日电池板温度
    private double weatherConditionYes;  //预测日前一天的天气状况
    private double weatherCondition;  //预测日的天气状况
    private double humidityYes;  //预测日前一天的湿度
    private double humidity;  //预测日湿度
}
