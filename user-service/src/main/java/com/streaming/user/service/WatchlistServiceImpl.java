package com.streaming.user.service;

import com.streaming.user.dto.WatchlistRequest;
import com.streaming.user.dto.WatchlistResponse;
import com.streaming.user.entity.User;
import com.streaming.user.entity.Watchlist;
import com.streaming.user.exception.UserNotFoundException;
import com.streaming.user.mapper.WatchlistMapper;
import com.streaming.user.repository.UserRepository;
import com.streaming.user.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService {

  private final WatchlistRepository watchlistRepository;
  private final UserRepository userRepository;
  private final WatchlistMapper watchlistMapper;

  @Override
  public List<WatchlistResponse> getByUserId(Long userId) {
    return watchlistRepository.findByUserId(userId)
      .stream()
      .map(watchlistMapper::toResponse)
      .toList();
  }

  @Override
  public WatchlistResponse add(Long userId, WatchlistRequest request) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new UserNotFoundException("User not found"));

    if (watchlistRepository.existsByUserIdAndVideoId(userId, request.getVideoId())) {
      throw new RuntimeException("Video already in watchlist");
    }

    Watchlist watchlist = Watchlist.builder()
      .user(user)
      .videoId(request.getVideoId())
      .build();

    return watchlistMapper.toResponse(watchlistRepository.save(watchlist));
  }

  @Override
  public void remove(Long userId, Long videoId) {
    Watchlist watchlist = watchlistRepository.findByUserIdAndVideoId(userId, videoId)
      .orElseThrow(() -> new RuntimeException("Video not found in watchlist"));
    watchlistRepository.delete(watchlist);
  }
}
