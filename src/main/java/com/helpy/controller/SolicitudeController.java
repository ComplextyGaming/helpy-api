package com.helpy.controller;

import com.helpy.dto.SocialResponse;
import com.helpy.dto.SolicitudeRequest;
import com.helpy.dto.SolicitudeResponse;
import com.helpy.exception.ExceptionResponse;
import com.helpy.exception.ResourceNotFoundException;
import com.helpy.model.Expert;
import com.helpy.model.Solicitude;
import com.helpy.service.ExpertService;
import com.helpy.service.SolicitudeService;
import com.helpy.util.SolicitudeConverter;
import org.hibernate.jdbc.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudeController {

    @Autowired
    private SolicitudeConverter converter;

    @Autowired
    private SolicitudeService solicitudeService;

    @Autowired
    private ExpertService expertService;

    @GetMapping
    public ResponseEntity<List<SolicitudeResponse>> getAll() {
        List<Solicitude> solicitudes = null;
        try {
            solicitudes = solicitudeService.getAll();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error ");
        }
        return new ResponseEntity<>(converter.convertSolicitudeToResponse(solicitudes), HttpStatus.OK);
    }

    @PostMapping("/expert/{id}")
    public ResponseEntity<SolicitudeResponse> createSolicitude(@Valid @RequestBody SolicitudeRequest request,
                                                                @Valid @PathVariable (value = "id") Long id) throws Exception {

        Expert exist = expertService.getById(id).orElse(null);

        if(exist == null){ throw new ResourceNotFoundException("expert not exist"); }

        var tem = converter.convertSolicitudeToEntity(request);
        tem.setExpert(exist);
        var solicitude = solicitudeService.create(tem);


        return new ResponseEntity<>(converter.convertSolicitudeToResponse(tem), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudeResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var solicitude = solicitudeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitude not found"));
        return new ResponseEntity<>(converter.convertSolicitudeToResponse(solicitude), HttpStatus.OK);
    }

    @PutMapping("/{id}/expert/{expertId}")
    public ResponseEntity<SolicitudeResponse> updateSolicitude(@Valid @RequestBody SolicitudeRequest request,
                                                     @Valid @PathVariable(name = "id") Long id,
                                                     @Valid @PathVariable(name = "expertId") Long expertId) throws Exception{
        var solicitude = solicitudeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitude not found"));
        var existExpert = expertService.getById(expertId).orElseThrow(() -> new ResourceNotFoundException("Expert not found"));

        solicitude.setExpert(existExpert);
        solicitude.setDate(request.getDate());
        solicitudeService.update(solicitude);
        return new ResponseEntity<>(converter.convertSolicitudeToResponse(solicitude), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolicitude(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var solicitude = solicitudeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitude not found"));
        solicitudeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
