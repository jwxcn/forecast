package com.hexing.forecast.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 发电预测输出
 * 预测日8时~18时11个时刻的发电量
 */
@Getter
@Setter
public class PowerForecastOutput {
    private double power8Yes;  //预测日8时的发电量
    private double power9Yes;  //预测日9时的发电量
    private double power10Yes;  //预测日10时的发电量
    private double power11Yes;  //预测日11时的发电量
    private double power12Yes;  //预测日12时的发电量
    private double power13Yes;  //预测日13时的发电量
    private double power14Yes;  //预测日14时的发电量
    private double power15Yes;  //预测日15时的发电量
    private double power16Yes;  // 预测日16时的发电量
    private double power17Yes;  //预测日17时的发电量
    private double power18Yes;  //预测日18时的发电量
}
