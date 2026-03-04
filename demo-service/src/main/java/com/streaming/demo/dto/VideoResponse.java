package com.streaming.demo.dto;

import lombok.Data;

@Data
public class VideoResponse {
    private Long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private String trailerUrl;
    private Integer duration;
    private Integer releaseYear;
    private String type;
    private String category;
    private String rating;
    private String director;
    private String cast;
}
