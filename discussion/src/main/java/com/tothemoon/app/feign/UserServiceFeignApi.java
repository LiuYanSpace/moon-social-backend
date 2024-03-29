package com.tothemoon.app.feign;

import com.bird.dto.BasicUserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(value = "API-AUTH-SERVICE")
public interface UserServiceFeignApi {

    @GetMapping(value = "/api/auth/users/basic/userinfo/{userId}")
    ResponseEntity<BasicUserInfoDTO> getBasicUserinfoByUserId(@PathVariable("userId") Long userId
    );
}
