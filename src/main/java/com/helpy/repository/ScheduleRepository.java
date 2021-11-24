package com.helpy.repository;

import com.helpy.model.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScheduleRepository extends GenericRepository<Schedule, Long> {
    List<Schedule> findByExpertId(Long expertId);
}
