package com.tothemoon.app.controller;

import com.tothemoon.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
@Slf4j
public class PublicController {

    private final UserService userService;

    @GetMapping("/files/profileImage")
    public ResponseEntity<String> getMemberProfileImage(){
        return new ResponseEntity<>(userService.getMemberProfileImage(), HttpStatus.OK);
    }
    @PutMapping("/files/profileImage")
    public ResponseEntity<?> getAndUpdateMemberProfileImage(@RequestParam String imageUrl){
        return new ResponseEntity< >( userService.getAndUpdateMemberProfileImage(imageUrl), HttpStatus.OK);
    }

}
