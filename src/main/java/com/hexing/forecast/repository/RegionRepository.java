package com.hexing.forecast.repository;

import com.hexing.forecast.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionEntity, byte[]> {
}
