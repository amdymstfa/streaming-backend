package com.streaming.user.repository;

import com.streaming.user.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
  List<Watchlist> findByUserId(Long userId);
  Optional<Watchlist> findByUserIdAndVideoId(Long userId, Long videoId);
  boolean existsByUserIdAndVideoId(Long userId, Long videoId);
  long countByUserId(Long userId);
}
