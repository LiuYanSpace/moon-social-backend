package com.tothemoon.app.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AuthFeignClient", url = "${feign.auth-service.url}")
public interface AuthFeignClient {

    @GetMapping("/api/public/files/profileImage")
    ResponseEntity<String> getMemberProfileImage();

    @PutMapping("/api/public/files/profileImage")
    ResponseEntity<String> getAndUpdateMemberProfileImage(@RequestParam String imageUrl);
}
