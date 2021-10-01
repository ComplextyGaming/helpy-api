package com.helpy.service.impl;

import com.helpy.dto.ExpertResponse;
import com.helpy.dto.GameRequest;
import com.helpy.dto.GameResponse;
import com.helpy.exception.ModelNotFoundException;
import com.helpy.model.Game;
import com.helpy.repository.GameRepository;
import com.helpy.repository.GenericRepository;
import com.helpy.service.GameProviderService;
import com.helpy.service.GameService;
import com.helpy.util.GameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameProviderService providerService;
    @Autowired
    private GameConverter converter;

    @Override
    public List<GameResponse> getAll() throws Exception {
        List<Game> games = gameRepository.findAll();
        String fields = games.stream().map(Game::getProviderId).collect(Collectors.toList()).toString();
        var providedGames = providerService.getAllGames(fields.substring(1,fields.length()-1));
        return converter.convertGameToResponse(games, providedGames);
    }

    @Override
    public GameResponse getById(Long id) throws Exception {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Game not found with id " + id));
        var providedGame = providerService.getGameById(game.getProviderId());
        return converter.convertGameToResponse(id, providedGame);
    }

    @Override
    public Game create(GameRequest game) throws Exception {
        if (providerService.getGameById(game.getProviderId()) != null){
            return gameRepository.save(converter.convertGameToEntity(game));
        }
        throw new ModelNotFoundException("Game not found in provider with id " + game.getProviderId());
    }

}
