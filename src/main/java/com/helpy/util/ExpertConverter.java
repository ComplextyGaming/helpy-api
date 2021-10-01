package com.helpy.util;

import com.helpy.dto.ExpertRequest;
import com.helpy.dto.ExpertResponse;
import com.helpy.dto.PlayerRequest;
import com.helpy.dto.PlayerResponse;
import com.helpy.model.Expert;
import com.helpy.model.Game;
import com.helpy.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ExpertConverter {
    @Autowired
    private ModelMapper modelMapper;

    public Expert convertExpertToEntity(ExpertRequest request){
        var expert = modelMapper.map(request, Expert.class);
        expert.setGame(Game.builder().id(request.getGameId()).build());
        return expert;
    }
    public ExpertResponse convertExpertToResponse(Expert expert){
        var expertResponse = modelMapper.map(expert, ExpertResponse.class);
        expertResponse.setGameId(expert.getGame().getId());
        return expertResponse;
    }
    public List<ExpertResponse> convertExpertToResponse(List<Expert> experts){
        return experts.stream().map(this::convertExpertToResponse)
                .collect(Collectors.toList());
    }
}
