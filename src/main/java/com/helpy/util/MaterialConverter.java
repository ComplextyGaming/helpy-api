package com.helpy.util;

import com.helpy.dto.ListTagRequest;
import com.helpy.dto.MaterialRequest;
import com.helpy.dto.MaterialResponse;
import com.helpy.dto.TagResponse;
import com.helpy.model.Material;
import com.helpy.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaterialConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Material convertMaterialToEntity(MaterialRequest request){
        return modelMapper.map(request, Material.class);
    }
    public MaterialResponse convertMaterialToResponse(Material material){
        return modelMapper.map(material, MaterialResponse.class);
    }
    public List<MaterialResponse> convertMaterialToResponse(List<Material> materials){
        return materials.stream().map(material -> modelMapper.map(material, MaterialResponse.class)).collect(Collectors.toList());
    }
    public Tag convertListTagToEntity(ListTagRequest request){
        return modelMapper.map(request, Tag.class);
    }
}
