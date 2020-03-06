package com.hexing.forecast.repository;

import com.hexing.forecast.entity.NormalizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NormalizationRepository extends JpaRepository<NormalizationEntity, byte[]> {

    List<NormalizationEntity> findBySymbol(String symbol);
}
