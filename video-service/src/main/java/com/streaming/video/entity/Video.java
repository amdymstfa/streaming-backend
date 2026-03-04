package com.streaming.video.entity;

import com.streaming.video.enums.VideoCategory;
import com.streaming.video.enums.VideoType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  private String thumbnailUrl;
  private String trailerUrl;
  private Integer duration;
  private Integer releaseYear;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private VideoType type;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private VideoCategory category;

  private String rating;
  private String director;
  @Column(name = "video_cast")
    private String cast;
}
