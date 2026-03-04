package com.streaming.demo.client;

import com.streaming.demo.dto.VideoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "video-feign-client", url = "${video.service.url}")
public interface VideoFeignClient {

    @GetMapping("/api/videos/{id}")
    VideoResponse getVideoById(@PathVariable Long id);
}
