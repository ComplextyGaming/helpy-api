package com.helpy.service.impl;


import com.helpy.model.Solicitude;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.SolicitudeRepository;
import com.helpy.service.SolicitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudeServiceImpl extends CrudServiceImpl<Solicitude, Long> implements SolicitudeService {

    @Autowired
    private SolicitudeRepository solicitudeRepository;

    @Override
    protected GenericRepository<Solicitude, Long> getRepository() {
        return solicitudeRepository;
    }
}
