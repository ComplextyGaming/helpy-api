package com.helpy.util;

import com.helpy.dto.MaterialRequest;
import com.helpy.dto.MaterialResponse;
import com.helpy.model.Material;
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
}
