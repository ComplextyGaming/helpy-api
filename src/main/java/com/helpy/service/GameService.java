package com.helpy.service;

import com.helpy.dto.GameRequest;
import com.helpy.dto.GameResponse;
import com.helpy.model.Game;

import java.util.List;

public interface GameService {
    List<GameResponse> getAll() throws Exception;
    GameResponse getById(Long id) throws Exception;
    Game create(GameRequest game) throws Exception;
}
