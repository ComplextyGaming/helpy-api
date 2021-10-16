package com.helpy.controller;

import com.helpy.dto.MaterialRequest;
import com.helpy.dto.MaterialResponse;
import com.helpy.exception.ResourceNotFoundException;
import com.helpy.model.Tag;
import com.helpy.repository.GameRepository;
import com.helpy.service.*;
import com.helpy.util.MaterialConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private MaterialConverter converter;

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

        List<Tag> tags = new ArrayList<>();

        for (Tag tag: request.getTags()) {
            tags.add(tagService.getById(tag.getId()).orElseThrow(() -> new ResourceNotFoundException("Tag not found")));
        }

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
}
