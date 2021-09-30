package com.helpy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleResponse {
    private Long id;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String state;
    private Long startHour;
    private Long startMin;
    private Long finishHour;
    private Long finishMin;
}
