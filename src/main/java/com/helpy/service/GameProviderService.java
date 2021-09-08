package com.helpy.service;

import proto.Game;
import java.util.List;
public interface GameProviderService {
    List<Game> getAllGames(String fields) throws Exception;
    Game getGameById(Long gameId) throws Exception;
}
