package com.streaming.user.service;

import com.streaming.user.dto.WatchHistoryRequest;
import com.streaming.user.dto.WatchHistoryResponse;
import com.streaming.user.dto.WatchStatsResponse;
import com.streaming.user.entity.User;
import com.streaming.user.entity.WatchHistory;
import com.streaming.user.exception.UserNotFoundException;
import com.streaming.user.mapper.WatchHistoryMapper;
import com.streaming.user.repository.UserRepository;
import com.streaming.user.repository.WatchHistoryRepository;
import com.streaming.user.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchHistoryServiceImpl implements WatchHistoryService {

  private final WatchHistoryRepository watchHistoryRepository;
  private final UserRepository userRepository;
  private final WatchlistRepository watchlistRepository;
  private final WatchHistoryMapper watchHistoryMapper;

  @Override
  public List<WatchHistoryResponse> getByUserId(Long userId) {
    return watchHistoryRepository.findByUserIdOrderByWatchedAtDesc(userId)
      .stream()
      .map(watchHistoryMapper::toResponse)
      .toList();
  }

  @Override
  public WatchHistoryResponse record(Long userId, WatchHistoryRequest request) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new UserNotFoundException("User not found"));

    WatchHistory history = WatchHistory.builder()
      .user(user)
      .videoId(request.getVideoId())
      .watchedDuration(request.getWatchedDuration())
      .totalDuration(request.getTotalDuration())
      .completed(request.isCompleted())
      .build();

    return watchHistoryMapper.toResponse(watchHistoryRepository.save(history));
  }

  @Override
  public WatchStatsResponse getStats(Long userId) {
    long total = watchHistoryRepository.countByUserId(userId);
    long completed = watchHistoryRepository.countByUserIdAndCompleted(userId, true);
    long inProgress = watchHistoryRepository.countByUserIdAndCompleted(userId, false);
    long watchlistCount = watchlistRepository.countByUserId(userId);
    return new WatchStatsResponse(total, completed, inProgress, watchlistCount);
  }
}
