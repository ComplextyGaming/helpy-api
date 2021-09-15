package com.helpy.repository;

import com.helpy.model.Schedule;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends GenericRepository<Schedule, Long> {
}
