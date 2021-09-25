package com.helpy.dto;

import com.helpy.model.Tag;
import lombok.Data;

import java.util.List;

@Data
public class MaterialRequest {

    private String description;
    private Double cost;
    private Double discount;
    private List<Tag> tags;
}
