package com.helpy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SolicitudeRequest {
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
}
