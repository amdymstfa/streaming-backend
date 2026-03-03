package com.streaming.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchStatsResponse {
    private long totalWatched;
    private long completedVideos;
    private long inProgressVideos;
    private long watchlistCount;
}
