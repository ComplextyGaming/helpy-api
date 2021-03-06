package com.helpy.controller;

import com.helpy.dto.*;
import com.helpy.exception.ModelNotFoundException;
import com.helpy.model.Game;
import com.helpy.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAllGames() throws Exception {
        var games = gameService.getAll();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var game = gameService.getById(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Game> createGame(@Valid @RequestBody GameRequest request) throws Exception {
        return new ResponseEntity<>(gameService.create(request), HttpStatus.CREATED);
    }
    @GetMapping("/a/{nose}")
    public ResponseEntity<List<GameResponse>> getNameLike(@Valid @PathVariable(name = "nose") String nose) throws Exception{
        return new ResponseEntity<>(gameService.findByNameContaining(nose), HttpStatus.CREATED);
    }
}
