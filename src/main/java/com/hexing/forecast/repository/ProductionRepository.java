package com.hexing.forecast.repository;

import com.hexing.forecast.entity.ProductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<ProductionEntity, byte[]> {
}
