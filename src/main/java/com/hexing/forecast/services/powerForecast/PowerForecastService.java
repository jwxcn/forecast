package com.hexing.forecast.services.powerForecast;

import com.hexing.forecast.component.BPNetworkComponent;
import com.hexing.forecast.services.IForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发电预测操作
 */
@Service
public class PowerForecastService extends CreatePowerDataService implements IForecastService {
    @Autowired
    BPNetworkComponent bpNetworkComponent;

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
