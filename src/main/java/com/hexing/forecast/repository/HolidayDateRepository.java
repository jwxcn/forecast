package com.hexing.forecast.repository;

import com.hexing.forecast.entity.HolidayDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayDateRepository extends JpaRepository<HolidayDateEntity, byte[]> {
}
