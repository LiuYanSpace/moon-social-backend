package com.tothemoon.common.config;

//import com.bird.exception.BadRequestException;
//import com.bird.exception.ErrorReasonCode;
//import com.bird.exception.ForbiddenRequestException;

import io.jsonwebtoken.Claims;

import com.tothemoon.common.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouterValidator routerValidator;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public GatewayFilter apply(AuthenticationFilter.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                log.info("request: {}", request.getPath());

                if (routerValidator.isSecured.test(request)) {
                    if (this.isAuthMissing(request)) {
                        return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                    }
                    final String token = this.getAuthHeader(request);
                    log.error("token: {}", token);
                    if (jwtUtils.getUserNameFromJwtToken(token) == null) {
                        return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                    }
                    Long userId = jwtUtils.getUserIdFromJwtToken(token);
                    if (userId == null) {
                        return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                    }
                    log.error("UserId: {}", userId);

                    ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                            .header("X-UserId", String.valueOf(userId))
                            .build();
//                    this.populateRequestWithHeaders(exchange, token);
                    return chain.filter(exchange.mutate().request(modifiedRequest).build());
                }

                return chain.filter(exchange);
            }
            private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(httpStatus);
                return response.setComplete();
            }

            private String getAuthHeader(ServerHttpRequest request) {
                HttpHeaders headers = request.getHeaders();
                if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                    String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        return authHeader.substring(7); // Remove "Bearer " prefix
                    }
                }
                return null;
//                return request.getHeaders().getOrEmpty("Authorization").get(0);
            }

            private boolean isAuthMissing(ServerHttpRequest request) {
                return !request.getHeaders().containsKey("Authorization");
            }

            private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
                Claims claims = jwtUtils.getAllClaimsFromToken(token);
                exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).header("role", String.valueOf(claims.get("role"))).build();
            }

        };
    }


    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {

    }

}
