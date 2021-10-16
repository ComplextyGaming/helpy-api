package com.helpy.controller;

import com.helpy.dto.ExpertResponse;
import com.helpy.service.ExpertService;
import com.helpy.util.ExpertConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games/{gameId}/experts")
public class GameExpertsController {
    @Autowired
    private ExpertConverter converter;

    @Autowired ExpertService expertService;

    @GetMapping
    public ResponseEntity<List<ExpertResponse>> getAllExpertsByGameId(@PathVariable(name = "gameId") Long gameId){
        var experts = expertService.getAllByGameId(gameId);
        return new ResponseEntity<>(converter.convertExpertToResponse(experts), HttpStatus.OK);
    }
}
