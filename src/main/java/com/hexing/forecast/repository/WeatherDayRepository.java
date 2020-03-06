package com.hexing.forecast.repository;

import com.hexing.forecast.entity.WeatherDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherDayRepository extends JpaRepository<WeatherDayEntity, byte[]> {

    @Query(value = "select * from weather_day where RegionID = :regionId and (DATE(Date) between :startTime and :endTime) order by Date", nativeQuery = true)
    List<WeatherDayEntity> findByDatesAndRegionId(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("regionId") byte[] regionId);
}
