package com.helpy.util;

import com.helpy.dto.ExpertRequest;
import com.helpy.dto.ExpertResponse;
import com.helpy.dto.PlayerRequest;
import com.helpy.dto.PlayerResponse;
import com.helpy.model.Expert;
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
        return modelMapper.map(request, Expert.class);
    }
    public ExpertResponse convertExpertToResponse(Expert expert){
        return modelMapper.map(expert, ExpertResponse.class);
    }
    public List<ExpertResponse> convertExpertToResponse(List<Expert> experts){
        return experts.stream().map(expert -> modelMapper.map(expert, ExpertResponse.class))
                .collect(Collectors.toList());
    }
}
