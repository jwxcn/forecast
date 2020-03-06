package com.hexing.forecast.repository;

import com.hexing.forecast.entity.SubstationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubstationRepository extends JpaRepository<SubstationEntity, byte[]> {
}
