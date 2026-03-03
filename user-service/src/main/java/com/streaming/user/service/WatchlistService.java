package com.streaming.user.service;

import com.streaming.user.dto.WatchlistRequest;
import com.streaming.user.dto.WatchlistResponse;
import java.util.List;

public interface WatchlistService {
  List<WatchlistResponse> getByUserId(Long userId);
  WatchlistResponse add(Long userId, WatchlistRequest request);
  void remove(Long userId, Long videoId);
}
