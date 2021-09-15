package com.helpy.util;

import com.helpy.dto.ScheduleRequest;
import com.helpy.dto.ScheduleResponse;
import com.helpy.dto.SocialRequest;
import com.helpy.dto.SocialResponse;
import com.helpy.model.Schedule;
import com.helpy.model.Social;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ScheduleConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Schedule convertScheduleToEntity(ScheduleRequest request){
        return modelMapper.map(request, Schedule.class);
    }
    public ScheduleResponse convertScheduleToResponse(Schedule schedule){
        return modelMapper.map(schedule, ScheduleResponse.class);
    }
    public List<ScheduleResponse> convertScheduleToResponse(List<Schedule> schedules){
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleResponse.class)).collect(Collectors.toList());
    }

}
