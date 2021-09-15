package com.helpy.util;

import com.helpy.dto.SocialRequest;
import com.helpy.dto.SocialResponse;
import com.helpy.model.Social;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SocialConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Social convertSocialToEntity(SocialRequest request) { return modelMapper.map(request, Social.class); }
    public SocialResponse convertSocialToResponse(Social social){ return modelMapper.map(social, SocialResponse.class); }
    public List<SocialResponse> convertSocialToResponse(List<Social> socials){
        return socials.stream().map(social -> modelMapper.map(social, SocialResponse.class)).collect(Collectors.toList());
    }
}
