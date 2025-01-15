package com.trips.busservice.repository;

import com.trips.busservice.data.entity.ScheduleDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDateRepository extends JpaRepository<ScheduleDateEntity, Long> {
}
