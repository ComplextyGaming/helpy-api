package com.helpy.service;


import com.helpy.model.Schedule;

import java.util.List;

public interface ScheduleService extends CrudService<Schedule, Long> {
    List<Schedule> getAllByExpertId(Long expertId);
}
