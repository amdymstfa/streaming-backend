package com.streaming.video.mapper;

import com.streaming.video.dto.VideoRequest;
import com.streaming.video.dto.VideoResponse;
import com.streaming.video.entity.Video;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VideoMapper {
  Video toEntity(VideoRequest request);
  VideoResponse toResponse(Video video);
  void updateEntity(VideoRequest request, @MappingTarget Video video);
}
