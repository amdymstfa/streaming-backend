package com.streaming.demo.controller;

import com.streaming.demo.client.VideoFeignClient;
import com.streaming.demo.dto.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class CommunicationDemoController {

    private final RestTemplate restTemplate;
    private final WebClient videoWebClient;
    private final VideoFeignClient videoFeignClient;

    @Value("${video.service.url}")
    private String videoServiceUrl;

    // ============================================
    // 1. RestTemplate — Synchrone, Bloquant
    // ============================================
    @GetMapping("/resttemplate/videos/{id}")
    public ResponseEntity<Map<String, Object>> getByRestTemplate(@PathVariable Long id) {
        long start = System.currentTimeMillis();

        VideoResponse video = restTemplate.getForObject(
                videoServiceUrl + "/api/videos/" + id,
                VideoResponse.class
        );

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("method", "RestTemplate");
        result.put("type", "Synchrone - Bloquant");
        result.put("description", "Méthode classique Spring, dépréciée depuis Spring 5.3");
        result.put("duration", (System.currentTimeMillis() - start) + "ms");
        result.put("video", video);

        return ResponseEntity.ok(result);
    }

    // ============================================
    // 2. WebClient — Non-bloquant, Réactif
    // ============================================
    @GetMapping("/webclient/videos/{id}")
    public ResponseEntity<Map<String, Object>> getByWebClient(@PathVariable Long id) {
        long start = System.currentTimeMillis();

        VideoResponse video = videoWebClient.get()
                .uri("/api/videos/{id}", id)
                .retrieve()
                .bodyToMono(VideoResponse.class)
                .block();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("method", "WebClient");
        result.put("type", "Non-bloquant - Réactif");
        result.put("description", "Méthode moderne Spring, supporte Mono et Flux");
        result.put("duration", (System.currentTimeMillis() - start) + "ms");
        result.put("video", video);

        return ResponseEntity.ok(result);
    }

    // ============================================
    // 3. OpenFeign — Synchrone, Déclaratif
    // ============================================
    @GetMapping("/feign/videos/{id}")
    public ResponseEntity<Map<String, Object>> getByFeign(@PathVariable Long id) {
        long start = System.currentTimeMillis();

        VideoResponse video = videoFeignClient.getVideoById(id);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("method", "OpenFeign");
        result.put("type", "Synchrone - Déclaratif");
        result.put("description", "Juste une interface, Spring génère tout le code HTTP");
        result.put("duration", (System.currentTimeMillis() - start) + "ms");
        result.put("video", video);

        return ResponseEntity.ok(result);
    }

    // ============================================
    // 4. Comparaison des 3
    // ============================================
    @GetMapping("/compare/videos/{id}")
    public ResponseEntity<Map<String, Object>> compare(@PathVariable Long id) {
        Map<String, Object> results = new LinkedHashMap<>();

        long t1 = System.currentTimeMillis();
        VideoResponse v1 = restTemplate.getForObject(
                videoServiceUrl + "/api/videos/" + id, VideoResponse.class);
        results.put("restTemplate", Map.of(
                "type", "Synchrone - Bloquant",
                "description", "Classique, déprécié",
                "duration", (System.currentTimeMillis() - t1) + "ms",
                "video", v1));

        long t2 = System.currentTimeMillis();
        VideoResponse v2 = videoWebClient.get()
                .uri("/api/videos/{id}", id)
                .retrieve()
                .bodyToMono(VideoResponse.class)
                .block();
        results.put("webClient", Map.of(
                "type", "Non-bloquant - Réactif",
                "description", "Moderne, haute performance",
                "duration", (System.currentTimeMillis() - t2) + "ms",
                "video", v2));

        long t3 = System.currentTimeMillis();
        VideoResponse v3 = videoFeignClient.getVideoById(id);
        results.put("openFeign", Map.of(
                "type", "Synchrone - Déclaratif",
                "description", "Le plus simple, juste une interface",
                "duration", (System.currentTimeMillis() - t3) + "ms",
                "video", v3));

        return ResponseEntity.ok(results);
    }
}
