package com.helpy.util;

import com.helpy.dto.TagRequest;
import com.helpy.dto.TagResponse;
import com.helpy.model.Material;
import com.helpy.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Tag convertTagToEntity(TagRequest request){
        return modelMapper.map(request, Tag.class);
    }
    public TagResponse convertTagToResponse(Tag tag){
        return modelMapper.map(tag, TagResponse.class);
    }
    public List<TagResponse> convertTagResponse(List<Tag> tags){
        return tags.stream().map(tag -> modelMapper.map(tag, TagResponse.class)).collect(Collectors.toList());
    }
}
