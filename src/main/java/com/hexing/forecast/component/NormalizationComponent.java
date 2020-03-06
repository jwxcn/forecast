package com.hexing.forecast.component;

import com.hexing.forecast.entity.NormalizationEntity;
import com.hexing.forecast.repository.NormalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据的归一化处理
 */
@Component
public class NormalizationComponent {
    @Autowired
    NormalizationRepository normalizationRepository;

    /**
     * 保存归一化参数，并删除原有数据
     * @param maxData
     * @param minData
     * @param symbol
     */
    public void insertNormalization(double maxData, double minData, String symbol) {
        List<NormalizationEntity> existedNormalizations = normalizationRepository.findBySymbol(symbol);
        if (existedNormalizations != null && existedNormalizations.size() != 0) {
            for (NormalizationEntity normalizationEntity: existedNormalizations) {
                normalizationRepository.deleteById(normalizationEntity.getId());
            }
        }

        NormalizationEntity normalizationEntity = new NormalizationEntity();
        normalizationEntity.setSymbol(symbol);
        normalizationEntity.setMaxValue(maxData);
        normalizationEntity.setMinValue(minData);
        normalizationRepository.save(normalizationEntity);
    }

    /**
     * 归一化计算
     * @param maxData
     * @param minData
     * @param real
     * @return
     */
    public double normalize(double maxData, double minData, double real) {
        if (maxData == minData)
            return 1;
        return (real - minData) / (maxData - minData);
    }
}
