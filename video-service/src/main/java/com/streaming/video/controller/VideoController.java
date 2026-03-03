package com.streaming.video.controller;

import com.streaming.video.dto.VideoRequest;
import com.streaming.video.dto.VideoResponse;
import com.streaming.video.enums.VideoCategory;
import com.streaming.video.enums.VideoType;
import com.streaming.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;

  @PostMapping
  public ResponseEntity<VideoResponse> create(@Valid @RequestBody VideoRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(videoService.create(request));
  }

  @GetMapping
  public ResponseEntity<List<VideoResponse>> getAll() {
    return ResponseEntity.ok(videoService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<VideoResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(videoService.getById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<VideoResponse> update(@PathVariable Long id,
                                              @Valid @RequestBody VideoRequest request) {
    return ResponseEntity.ok(videoService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    videoService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/type/{type}")
  public ResponseEntity<List<VideoResponse>> getByType(@PathVariable VideoType type) {
    return ResponseEntity.ok(videoService.getByType(type));
  }

  @GetMapping("/category/{category}")
  public ResponseEntity<List<VideoResponse>> getByCategory(@PathVariable VideoCategory category) {
    return ResponseEntity.ok(videoService.getByCategory(category));
  }
}
