package com.helpy.controller;
import com.helpy.dto.PlayerRequest;
import com.helpy.dto.PlayerResponse;
import com.helpy.exception.ModelNotFoundException;
import com.helpy.service.PlayerService;
import com.helpy.util.PlayerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerConverter converter;
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> getAllPlayers() throws Exception {
        var players = playerService.getAll();
        return new ResponseEntity<>(converter.convertPlayerToResponse(players), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable(name = "id") Long id) throws Exception{
        var player = playerService.getById(id).orElseThrow(() -> new ModelNotFoundException("Player not found with id " + id));
        return new ResponseEntity<>(converter.convertPlayerToResponse(player), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> createPlayer(@Valid @RequestBody PlayerRequest request) throws Exception {
        var player = playerService.create(converter.convertPlayerToEntity(request));
        return new ResponseEntity<>(converter.convertPlayerToResponse(player), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpert(@PathVariable(name = "id") Long id) throws Exception {
        playerService.delete(id);
        return ResponseEntity.ok().build();
    }

}
