package com.streaming.user.service;

import com.streaming.user.dto.WatchHistoryRequest;
import com.streaming.user.dto.WatchHistoryResponse;
import com.streaming.user.dto.WatchStatsResponse;
import java.util.List;

public interface WatchHistoryService {
  List<WatchHistoryResponse> getByUserId(Long userId);
  WatchHistoryResponse record(Long userId, WatchHistoryRequest request);
  WatchStatsResponse getStats(Long userId);
}
