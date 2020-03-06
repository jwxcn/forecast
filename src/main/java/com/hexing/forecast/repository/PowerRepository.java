package com.hexing.forecast.repository;

import com.hexing.forecast.entity.PowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PowerRepository extends JpaRepository<PowerEntity, byte[]> {

    @Query(value = "select * from power where SubstationID = :substationId and (DATE(Date) between :startTime and :endTime) and HOUR(Date) >= 8 and HOUR(Date) <= 18 order by Date", nativeQuery = true)
    List<PowerEntity> findByDatesAndSubstationId(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("substationId") byte[] substationId);
}
