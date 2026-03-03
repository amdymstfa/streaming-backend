package com.streaming.user.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WatchlistResponse {
  private Long id;
  private Long videoId;
  private LocalDateTime addedAt;
}
