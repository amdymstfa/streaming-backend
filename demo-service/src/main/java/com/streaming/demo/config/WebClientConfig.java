package com.streaming.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${video.service.url}")
    private String videoServiceUrl;

    @Bean
    public WebClient videoWebClient() {
        return WebClient.builder()
                .baseUrl(videoServiceUrl)
                .build();
    }
}
