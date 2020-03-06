package com.hexing.forecast.repository;

import com.hexing.forecast.entity.LoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadRepository extends JpaRepository<LoadEntity, byte[]> {
}
