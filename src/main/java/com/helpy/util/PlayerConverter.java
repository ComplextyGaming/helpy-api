package com.helpy.util;

import com.helpy.dto.PlayerRequest;
import com.helpy.dto.PlayerResponse;
import com.helpy.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public Player convertPlayerToEntity(PlayerRequest request){
        return modelMapper.map(request, Player.class);
    }
    public PlayerResponse convertPlayerToResponse(Player player){
        return modelMapper.map(player, PlayerResponse.class);
    }
    public List<PlayerResponse> convertPlayerToResponse(List<Player> players){
        return players.stream().map(player -> modelMapper.map(player, PlayerResponse.class))
                .collect(Collectors.toList());
    }
}
