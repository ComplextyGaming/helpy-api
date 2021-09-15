package com.helpy.util;

import com.helpy.dto.SolicitudeRequest;
import com.helpy.dto.SolicitudeResponse;
import com.helpy.model.Solicitude;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolicitudeConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Solicitude convertSolicitudeToEntity(SolicitudeRequest request){
        return modelMapper.map(request, Solicitude.class);
    }

    public SolicitudeResponse convertSolicitudeToResponse(Solicitude solicitude){
        return modelMapper.map(solicitude, SolicitudeResponse.class);
    }

    public List<SolicitudeResponse> convertSolicitudeToResponse(List<Solicitude> solicitudes){
        return solicitudes.stream().map(solicitude -> modelMapper.map(solicitude, SolicitudeResponse.class)).collect(Collectors.toList());
    }
}
