package com.hexing.forecast.repository;

import com.hexing.forecast.entity.PanelTemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PanelTemperatureRepository extends JpaRepository<PanelTemperatureEntity, byte[]> {

    @Query(value = "select * from panel_temperature where SubstationID = :substationId and (DATE(Date) between :startTime and :endTime) order by Date", nativeQuery = true)
    List<PanelTemperatureEntity> findByDateAndSubstationId(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("substationId") byte[] substationId);
}
