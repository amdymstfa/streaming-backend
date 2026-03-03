package com.streaming.user.repository;

import com.streaming.user.entity.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
  List<WatchHistory> findByUserIdOrderByWatchedAtDesc(Long userId);
  long countByUserId(Long userId);
  long countByUserIdAndCompleted(Long userId, boolean completed);
}
