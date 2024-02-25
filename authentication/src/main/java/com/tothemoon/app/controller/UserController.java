package com.tothemoon.app.controller;

import com.tothemoon.app.dto.UserDTO;
import com.tothemoon.app.mapper.UserMapper;
import com.tothemoon.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    //1.fetch
    //2.update
    //3.follow // unfollow
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(value = "")
    public ResponseEntity<UserDTO> fetchMyProfile() {
        return new ResponseEntity<>(userMapper.toDTO(userService.getUser()), HttpStatus.OK);
    }
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userMapper.toDTO(userService.getUserById(userId)), HttpStatus.OK);
    }
    @PutMapping(value = "")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userMapper.toDTO(userService.updateUser(userDTO)), HttpStatus.OK);
    }


}
