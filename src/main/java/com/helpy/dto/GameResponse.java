package com.helpy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResponse {
    private Long id;
    private String name;
    private String storyLine;
    private String summary;
    private String coverUrl;
    private String backgroundImageUrl;
}
