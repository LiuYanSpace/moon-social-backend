package com.tothemoon.app.feign;
import feign.Headers;
import com.tothemoon.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "DiscussionAuthFeignClient", url = "${feign.auth-service.url}")
public interface DiscussionAuthFeignClient {

    @GetMapping("/api/public/users/{userId}")
    ResponseEntity<User> getUserByUserId(@PathVariable Long userId);

}
