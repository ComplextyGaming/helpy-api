package com.helpy.service.impl;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.helpy.exception.ModelNotFoundException;
import com.helpy.service.GameProviderService;
import org.springframework.stereotype.Service;
import proto.Game;

import java.util.List;
// TODO: Change exceptions
@Service
public class GameProviderServiceImpl implements GameProviderService {
    private final IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
    private final TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;

    public GameProviderServiceImpl() {
        //TwitchToken token = tAuth.requestTwitchToken("ov3od6hqyjyb0iurx0hbbvp6ijde7v", "4jmt7pvh71k1y0drgnljoy9p528djb");
        TwitchToken token = tAuth.requestTwitchToken("ov3od6hqyjyb0iurx0hbbvp6ijde7v", "4jmt7pvh71k1y0drgnljoy9p528djb");
      
        //TwitchToken token = tAuth.requestTwitchToken("ov3od6hqyjyb0iurx0hbbvp6ijde7v", "4jmt7pvh71k1y0drgnljoy9p528djb");
        wrapper.setCredentials("ov3od6hqyjyb0iurx0hbbvp6ijde7v", "s1z3o4nn33356mgsfj1xcb5swut2b9");
    }

    @Override
    public List<Game> getAllGames(String fields) throws Exception {

        APICalypse apicalypse = new APICalypse().fields("id, name, cover.image_id, screenshots.image_id, storyline, summary")
                .where("id = (" + fields + ")");
        try {
            return ProtoRequestKt.games(wrapper, apicalypse);
        } catch (Exception ex){
          throw new Exception(ex);
        }
    }

    @Override
    public Game getGameById(Long gameId) throws Exception {
        APICalypse apicalypse = new APICalypse().fields("id, name, cover.image_id, screenshots.image_id, storyline, summary")
                .where("id = " + gameId);
        try {
            return ProtoRequestKt.games(wrapper, apicalypse).get(0);
        } catch (Exception ex){
            throw new ModelNotFoundException("Game not found with provider Id " + gameId);
        }
    }
}
