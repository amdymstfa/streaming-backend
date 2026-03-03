package com.streaming.video.service;

import com.streaming.video.dto.VideoRequest;
import com.streaming.video.dto.VideoResponse;
import com.streaming.video.enums.VideoCategory;
import com.streaming.video.enums.VideoType;

import java.util.List;

public interface VideoService {
  VideoResponse create(VideoRequest request);
  VideoResponse getById(Long id);
  List<VideoResponse> getAll();
  VideoResponse update(Long id, VideoRequest request);
  void delete(Long id);
  List<VideoResponse> getByType(VideoType type);
  List<VideoResponse> getByCategory(VideoCategory category);
}
