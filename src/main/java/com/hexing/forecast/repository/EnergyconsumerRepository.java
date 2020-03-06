package com.hexing.forecast.repository;

import com.hexing.forecast.entity.EnergyconsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyconsumerRepository extends JpaRepository<EnergyconsumerEntity, byte[]> {
}
