package com.streaming.video.dto;

import com.streaming.video.enums.VideoCategory;
import com.streaming.video.enums.VideoType;
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
  private VideoType type;
  private VideoCategory category;
  private String rating;
  private String director;
  private String cast;
}
