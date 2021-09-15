package com.helpy.dto;

import lombok.Data;

@Data
public class ReviewResponse {
    private Long id;
    private String description;
    private String url;
    private String comment;
}
