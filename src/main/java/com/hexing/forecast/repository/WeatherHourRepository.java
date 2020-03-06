package com.hexing.forecast.repository;

import com.hexing.forecast.entity.WeatherHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherHourRepository extends JpaRepository<WeatherHourEntity, byte[]> {
}
