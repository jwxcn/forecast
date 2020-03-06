package com.hexing.forecast.services.loadForecast;

import com.hexing.forecast.services.IForecastService;
import org.springframework.stereotype.Service;

/**
 * 负荷预测操作
 */
@Service
public class LoadForecastService implements IForecastService {

    //训练模型
    @Override
    public boolean train() {
        return true;
    }

    //测试模型
    @Override
    public void evaluate() {

    }

    //利用模型预测负荷
    @Override
    public double forecast() {
        return 0;
    }
}
