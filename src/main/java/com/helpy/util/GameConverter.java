package com.helpy.util;
import com.api.igdb.utils.ImageBuilderKt;
import com.api.igdb.utils.ImageSize;
import com.api.igdb.utils.ImageType;
import com.helpy.dto.GameRequest;
import com.helpy.dto.GameResponse;
import com.helpy.model.Game;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class GameConverter {

    @Autowired
    private ModelMapper mapper;

    public Game convertGameToEntity(GameRequest request){
        return Game.builder()
                .name(request.getName())
                .providerId(request.getProviderId())
                .build();
    }
    public GameResponse convertGameToResponse(Long id, proto.Game game){
        String coverUrl = ImageBuilderKt.imageBuilder(game.getCover().getImageId(), ImageSize.LOGO_MEDIUM, ImageType.PNG);
        Random rand = new Random();
        int upperbound = game.getScreenshotsList().size();
        int position = rand.nextInt(upperbound);
        String backgroundUrl = ImageBuilderKt
                .imageBuilder(game.getScreenshots(position).getImageId(), ImageSize.SCREENSHOT_BIG, ImageType.PNG);
        return GameResponse.builder()
                .id(id)
                .name(game.getName())
                .summary(game.getSummary())
                .storyLine(game.getStoryline())
                .coverUrl(coverUrl)
                .backgroundImageUrl(backgroundUrl)
                .build();

    }
    public List<GameResponse> convertGameToResponse(List<Game> games, List<proto.Game> providedGames){
        return games.stream().map(game -> this.convertGameToResponse(game.getId(), providedGames.stream()
                        .filter(g -> g.getId() == game.getProviderId()) .findFirst()
                        .orElseThrow(() -> new ResourceAccessException("Game not found with id " + game.getProviderId()))))
                .collect(Collectors.toList());
    }
    public List<Game> convertToListGame(List<Game> games){
        return games.stream().map(game -> mapper.map(game, Game.class)).collect(Collectors.toList());
    }
}
