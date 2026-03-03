package com.streaming.video.service;

import com.streaming.video.dto.VideoRequest;
import com.streaming.video.dto.VideoResponse;
import com.streaming.video.entity.Video;
import com.streaming.video.enums.VideoCategory;
import com.streaming.video.enums.VideoType;
import com.streaming.video.exception.VideoNotFoundException;
import com.streaming.video.mapper.VideoMapper;
import com.streaming.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

  private final VideoRepository videoRepository;
  private final VideoMapper videoMapper;

  @Override
  public VideoResponse create(VideoRequest request) {
    Video video = videoMapper.toEntity(request);
    return videoMapper.toResponse(videoRepository.save(video));
  }

  @Override
  public VideoResponse getById(Long id) {
    return videoMapper.toResponse(findById(id));
  }

  @Override
  public List<VideoResponse> getAll() {
    return videoRepository.findAll()
      .stream()
      .map(videoMapper::toResponse)
      .toList();
  }

  @Override
  public VideoResponse update(Long id, VideoRequest request) {
    Video video = findById(id);
    videoMapper.updateEntity(request, video);
    return videoMapper.toResponse(videoRepository.save(video));
  }

  @Override
  public void delete(Long id) {
    findById(id);
    videoRepository.deleteById(id);
  }

  @Override
  public List<VideoResponse> getByType(VideoType type) {
    return videoRepository.findByType(type)
      .stream()
      .map(videoMapper::toResponse)
      .toList();
  }

  @Override
  public List<VideoResponse> getByCategory(VideoCategory category) {
    return videoRepository.findByCategory(category)
      .stream()
      .map(videoMapper::toResponse)
      .toList();
  }

  private Video findById(Long id) {
    return videoRepository.findById(id)
      .orElseThrow(() -> new VideoNotFoundException("Video not found with id: " + id));
  }
}
