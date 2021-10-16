package com.helpy.controller;

import com.helpy.dto.TagRequest;
import com.helpy.dto.TagResponse;
import com.helpy.exception.ResourceNotFoundException;
import com.helpy.repository.GameRepository;
import com.helpy.service.*;
import com.helpy.util.TagConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TagConverter converter;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAll() throws Exception{
        var tags = tagService.getAll();
        return new ResponseEntity<>(converter.convertTagResponse(tags), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var tag = tagService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        return new ResponseEntity<>(converter.convertTagToResponse(tag), HttpStatus.OK);
    }

    @PostMapping("/game/{gameId}")
    public ResponseEntity<TagResponse> createTag(@Valid @RequestBody TagRequest request,
                                                           @Valid @PathVariable(name = "gameId") Long gameId) throws Exception{
        var game = gameRepository.getById(gameId);


        var tem = converter.convertTagToEntity(request);
        tem.setGame(game);


        var tag = tagService.create(tem);
        return new ResponseEntity<>(converter.convertTagToResponse(tag), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> updateTag(@Valid @RequestBody TagRequest request,
                                                           @Valid @PathVariable(name = "id") Long id) throws Exception{
        var tag = tagService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));


        tag.setImage(request.getImage());
        tag.setName(request.getName());

        tagService.update(tag);
        return new ResponseEntity<>(converter.convertTagToResponse(tag), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var tag = tagService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        tagService.delete(id);
        return ResponseEntity.ok().build();
    }
}
