package com.tothemoon.app.feign;

import com.bird.dto.BasicUserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "API-AUTH-SERVICE")
public interface UserServiceFeignApi {

    @GetMapping(value = "/basic/userinfo/{userId}")
    ResponseEntity<BasicUserInfoDTO> getBasicUserinfoByUserId(@PathVariable("userId") Long userId);
}
