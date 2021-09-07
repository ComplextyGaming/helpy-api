package com.helpy.controller;
import com.helpy.dto.PlayerRequest;
import com.helpy.dto.PlayerResponse;
import com.helpy.service.PlayerService;
import com.helpy.util.PlayerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerConverter converter;
    @Autowired
    private PlayerService service;

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> getAllPlayers() throws Exception {
        var players = service.getAll();
        return new ResponseEntity<>(converter.convertPlayerToResponse(players), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PlayerResponse> createPlayer(@Valid @RequestBody PlayerRequest request) throws Exception {
        var player = service.create(converter.convertPlayerToEntity(request));
        return new ResponseEntity<>(converter.convertPlayerToResponse(player), HttpStatus.CREATED);
    }
}
