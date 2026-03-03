package com.streaming.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WatchHistoryRequest {
    @NotNull
    private Long videoId;
    private Integer watchedDuration;
    private Integer totalDuration;
    private boolean completed;
}
