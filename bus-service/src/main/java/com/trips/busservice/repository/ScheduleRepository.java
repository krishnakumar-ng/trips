package com.trips.busservice.repository;

import com.trips.busservice.data.entity.BusEntity;
import com.trips.busservice.data.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("SELECT s FROM ScheduleEntity s JOIN s.dates sd WHERE s.route.id = :routeId AND sd.date = :travelDate AND " +
            "sd.isAvailable = :isAvailable")
    List<ScheduleEntity> findByRouteAndDate(@Param("routeId") String routeId,
                                            @Param("travelDate") LocalDate travelDate,
                                            @Param("isAvailable") boolean isAvailable);

    @Query("SELECT s FROM ScheduleEntity s WHERE s.bus = :bus")
    List<ScheduleEntity> findByBus(@Param("bus") BusEntity bus);
}
