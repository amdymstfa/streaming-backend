package com.streaming.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "video-service", url = "http://localhost:8081")
public interface VideoClient {

  @GetMapping("/api/videos/{id}")
  Object getVideoById(@PathVariable Long id);
}
