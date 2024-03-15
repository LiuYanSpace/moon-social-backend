//package com.bird.feign;
//
//import com.bird.dto.BasicUserInfoDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//@FeignClient(value = "API-AUTH-SERVICE")
//public interface UserServiceFeignApi {
//
//    @GetMapping(value = "/basic/userinfo/{userId}")
//    ResponseEntity<BasicUserInfoDTO> getBasicUserinfoByUserId(@PathVariable("userId") Long userId);
//}
