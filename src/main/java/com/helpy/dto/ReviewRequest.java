package com.helpy.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private String description;
    private String url;
    private String comment;
}
