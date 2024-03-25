package com.tothemoon.app.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Value("${your.authorization.header.key}")
    private String authorizationHeaderKey;

    @Override
    public void apply(RequestTemplate template) {
        String token = getAuthHeader();
        if (token != null) {
            template.header("Authorization", "Bearer " + token);
        }
    }

    private String getAuthHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String authHeader = request.getHeader(authorizationHeaderKey);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                return authHeader.substring(7); // Remove "Bearer " prefix
            }
        }
        return null;
    }
}