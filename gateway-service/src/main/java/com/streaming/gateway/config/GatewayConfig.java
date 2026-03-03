package com.streaming.gateway.config;

import com.streaming.gateway.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  public RouterFunction<ServerResponse> authRoutes() {
    return GatewayRouterFunctions.route("auth-service")
      .route(RequestPredicates.path("/api/auth/**"),
        HandlerFunctions.http("http://localhost:8084"))
      .build();
  }

  @Bean
  public RouterFunction<ServerResponse> videoRoutes() {
    return GatewayRouterFunctions.route("video-service")
      .route(RequestPredicates.path("/api/videos/**"),
        HandlerFunctions.http("http://localhost:8081"))
      .build();
  }

  @Bean
  public RouterFunction<ServerResponse> userRoutes() {
    return GatewayRouterFunctions.route("user-service")
      .route(RequestPredicates.path("/api/users/**"),
        HandlerFunctions.http("http://localhost:8082"))
      .build();
  }
}
