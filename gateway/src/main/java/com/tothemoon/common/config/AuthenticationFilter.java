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
                log.info("Applying AuthenticationFilter to request: {}", request.getPath());

                if (routerValidator.isSecured.test(request)) {
                    if (this.isAuthMissing(request)) {
                        return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                    }
                    final String token = this.getAuthHeader(request);
                    if (jwtUtils.getUserNameFromJwtToken(token) == null) {
                        return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                    }
                    this.populateRequestWithHeaders(exchange, token);
                }

                return chain.filter(exchange);
            }


            private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
//        throw new ForbiddenRequestException(errorReasonCode);

                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(httpStatus);
                return response.setComplete();
            }

            private String getAuthHeader(ServerHttpRequest request) {
                return request.getHeaders().getOrEmpty("Authorization").get(0);
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
