package com.helpy.controller;

import com.helpy.dto.ExpertRequest;
import com.helpy.dto.ExpertResponse;
import com.helpy.exception.ModelNotFoundException;
import com.helpy.model.Expert;
import com.helpy.service.ExpertService;
import com.helpy.util.ExpertConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/experts")
public class ExpertController {
    @Autowired
    private ExpertConverter converter;

    @Autowired
    private ExpertService service;



    @GetMapping
    public ResponseEntity<List<ExpertResponse>> getAllExperts() throws Exception {
        var experts = service.getAll();
        return new ResponseEntity<>(converter.convertExpertToResponse(experts), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExpertResponse> getExpertById(@PathVariable(name = "id") Long id) throws Exception{
        var expert = service.getById(id).orElseThrow(() -> new ModelNotFoundException("Expert not found with id " + id));
        return new ResponseEntity<>(converter.convertExpertToResponse(expert), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ExpertResponse> createExpert(@Valid @RequestBody ExpertRequest request) throws Exception {
        var expert = service.create(converter.convertExpertToEntity(request));
        return new ResponseEntity<>(converter.convertExpertToResponse(expert), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpert(@PathVariable(name = "id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
