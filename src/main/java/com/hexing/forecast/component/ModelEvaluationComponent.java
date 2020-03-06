package com.hexing.forecast.component;

import org.springframework.stereotype.Component;

/**
 * 模型测试与评估
 */
@Component
public class ModelEvaluationComponent {

    /**
     * 平方和误差
     * @param outputReal 输出实际值
     * @param outputCal  计算输出
     * @return
     */
    public double sse(double[] outputReal, double[] outputCal) {
        double result = 0;
        for (int i = 0; i < outputReal.length; i++) {
            result += Math.pow(outputReal[i] - outputCal[i], 2);
        }
        return result;
    }

    /**
     * 平均绝对误差
     * @param outputReal
     * @param outputCal
     * @return
     */
    public double MAE(double[] outputReal, double[] outputCal) {
        double result = 0;
        for (int i = 0; i < outputReal.length; i++) {
            result += Math.abs(outputReal[i] - outputCal[i]);
        }
        return result / outputReal.length;
    }

    /**
     * 均方根误差
     * @param outputReal
     * @param outputCal
     * @return
     */
    public double RMSE(double[] outputReal, double[] outputCal) {
        double result = 0;
        for (int i = 0; i < outputReal.length; i++) {
            result += Math.pow(outputReal[i] - outputCal[i], 2);
        }
        return Math.sqrt(result / outputReal.length);
    }
}
