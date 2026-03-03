package com.streaming.user.controller;

import com.streaming.user.dto.WatchlistRequest;
import com.streaming.user.dto.WatchlistResponse;
import com.streaming.user.service.WatchlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

  private final WatchlistService watchlistService;

  @GetMapping
  public ResponseEntity<List<WatchlistResponse>> getAll(@PathVariable Long userId) {
    return ResponseEntity.ok(watchlistService.getByUserId(userId));
  }

  @PostMapping
  public ResponseEntity<WatchlistResponse> add(@PathVariable Long userId,
                                               @Valid @RequestBody WatchlistRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(watchlistService.add(userId, request));
  }

  @DeleteMapping("/{videoId}")
  public ResponseEntity<Void> remove(@PathVariable Long userId,
                                     @PathVariable Long videoId) {
    watchlistService.remove(userId, videoId);
    return ResponseEntity.noContent().build();
  }
}
