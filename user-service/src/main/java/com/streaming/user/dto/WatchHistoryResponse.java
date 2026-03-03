package com.streaming.user.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WatchHistoryResponse {
  private Long id;
  private Long videoId;
  private Integer watchedDuration;
  private Integer totalDuration;
  private boolean completed;
  private LocalDateTime watchedAt;
}
