package com.streaming.video.dto;

import com.streaming.video.enums.VideoCategory;
import com.streaming.video.enums.VideoType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VideoRequest {

  @NotBlank(message = "Title is required")
  private String title;

  private String description;
  private String thumbnailUrl;
  private String trailerUrl;
  private Integer duration;
  private Integer releaseYear;

  @NotNull(message = "Type is required")
  private VideoType type;

  @NotNull(message = "Category is required")
  private VideoCategory category;

  private String rating;
  private String director;
  private String cast;
}
