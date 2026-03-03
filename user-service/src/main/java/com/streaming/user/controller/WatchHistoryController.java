package com.streaming.user.controller;

import com.streaming.user.dto.WatchHistoryRequest;
import com.streaming.user.dto.WatchHistoryResponse;
import com.streaming.user.dto.WatchStatsResponse;
import com.streaming.user.service.WatchHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class WatchHistoryController {

  private final WatchHistoryService watchHistoryService;

  @GetMapping("/history")
  public ResponseEntity<List<WatchHistoryResponse>> getHistory(@PathVariable Long userId) {
    return ResponseEntity.ok(watchHistoryService.getByUserId(userId));
  }

  @PostMapping("/history")
  public ResponseEntity<WatchHistoryResponse> record(@PathVariable Long userId,
                                                     @Valid @RequestBody WatchHistoryRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(watchHistoryService.record(userId, request));
  }

  @GetMapping("/stats")
  public ResponseEntity<WatchStatsResponse> getStats(@PathVariable Long userId) {
    return ResponseEntity.ok(watchHistoryService.getStats(userId));
  }
}
