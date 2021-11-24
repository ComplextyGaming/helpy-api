package com.helpy.controller;

import com.helpy.dto.ListTagRequest;
import com.helpy.dto.MaterialRequest;
import com.helpy.dto.MaterialResponse;
import com.helpy.dto.TagRequest;

import com.helpy.dto.MaterialesResumenDTO;

import com.helpy.exception.ResourceNotFoundException;
import com.helpy.model.Material;
import com.helpy.model.Tag;
import com.helpy.repository.ExpertRepository;
import com.helpy.repository.GameRepository;
import com.helpy.service.*;
import com.helpy.util.MaterialConverter;
import com.helpy.util.TagConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Qualifier("MaterialServiceImpl")
    @Autowired
    private MaterialService materialService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private MaterialConverter converter;

    @Autowired
    private TagConverter tagConverter;

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> getAll() throws Exception{
        var materials = materialService.getAll();
        return new ResponseEntity<>(converter.convertMaterialToResponse(materials), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var material = materialService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Material not found"));
        return new ResponseEntity<>(converter.convertMaterialToResponse(material), HttpStatus.OK);
    }

    @PostMapping("/expert/{expertId}/game/{gameId}")
    public ResponseEntity<MaterialResponse> createMaterial(@Valid @RequestBody MaterialRequest request,
                                                           @Valid @PathVariable(name = "expertId") Long expertId,
                                                           @Valid @PathVariable(name = "gameId") Long gameId) throws Exception{

        var expert = expertService.getById(expertId).orElseThrow(() -> new ResourceNotFoundException("Expert not found"));
        var game = gameRepository.getById(gameId);

        var tags = request.getTags().stream().map(tag -> {
            try {
                return tagService.getById(tag.getId()).orElse(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        var tem = converter.convertMaterialToEntity(request);
        tem.setExpert(expert);
        tem.setGame(game);
        tem.setTags(tags);

        var material = materialService.create(tem);
        return new ResponseEntity<>(converter.convertMaterialToResponse(material), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> updateMaterial(@Valid @RequestBody MaterialRequest request,
                                                           @Valid @PathVariable(name = "id") Long id)
                                                           throws Exception{
        var material = materialService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Material not found"));

        material.setDescription(request.getDescription());
        material.setCost(request.getCost());
        material.setDiscount(request.getDiscount());

        materialService.update(material);
        return new ResponseEntity<>(converter.convertMaterialToResponse(material), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var material = materialService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Material not found"));
        materialService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/expert/{id}")
    public ResponseEntity<List<MaterialResponse>> getByExpertId(@Valid @PathVariable(name = "id") Long id) throws Exception {
        var materials = materialService.getByExpertId(id);
        return new ResponseEntity<>(converter.convertMaterialToResponse(materials), HttpStatus.OK);
    }

    @GetMapping("/expert/{expertId}/tag/{tagId}")
    public ResponseEntity<List<MaterialResponse>> getByExpertIdAndTagId(@Valid @PathVariable(name = "expertId") Long expertId,
                                                                        @Valid @PathVariable(name = "tagId") Long tagId) throws Exception {

        var materials = materialService.getByExpertIdAndTagIg(expertId, tagId);
        return new ResponseEntity<>(converter.convertMaterialToResponse(materials), HttpStatus.OK);
    }

    @GetMapping(value = "/listarResumen")
    public ResponseEntity<List<MaterialesResumenDTO>> listarResumen() {
        List<MaterialesResumenDTO> materials = new ArrayList<>();
        materials = materialService.listarResumen();
        return new ResponseEntity<List<MaterialesResumenDTO>>(materials, HttpStatus.OK);
    }

    @GetMapping(value = "/leerArchivo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> leerArchivo() throws IOException {

        byte[] arr = materialService.generarReporte();

        return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
    }
}
