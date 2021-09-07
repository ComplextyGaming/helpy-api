package com.helpy.controller;

import com.helpy.dto.ExpertRequest;
import com.helpy.dto.ExpertResponse;
import com.helpy.model.Expert;
import com.helpy.service.ExpertService;
import com.helpy.util.ExpertConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/experts")
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
    @PostMapping
    public ResponseEntity<ExpertResponse> createExpert(@Valid @RequestBody ExpertRequest request) throws Exception {
        var expert = service.create(converter.convertExpertToEntity(request));
        return new ResponseEntity<>(converter.convertExpertToResponse(expert), HttpStatus.CREATED);
    }
}
