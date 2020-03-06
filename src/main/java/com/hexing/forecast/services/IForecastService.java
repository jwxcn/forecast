package com.hexing.forecast.services;

public interface IForecastService {

    //训练模型
    boolean train();

    //测试模型
    void evaluate();

    //利用模型预测负荷
    double forecast();

}
