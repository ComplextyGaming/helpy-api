package com.helpy.service.impl;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import org.junit.jupiter.api.Test;
import proto.Game;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameProviderServiceImplTest {
    private final IGDBWrapper wrapper = IGDBWrapper.INSTANCE;

    public GameProviderServiceImplTest() {
        wrapper.setCredentials("ov3od6hqyjyb0iurx0hbbvp6ijde7v", "l78rkd8shk01fc8inhnvc1uekq8ccn");
    }

    @Test
    void getAllGames() throws RequestException {
        String fields = "24037";
        APICalypse apicalypse = new APICalypse().fields("id, name, cover.image_id, screenshots.image_id, storyline, summary")
                .where("id = (" + fields + ")");
        List<Game> games = ProtoRequestKt.games(wrapper, apicalypse);
        System.out.print(games.get(0));
    }

}