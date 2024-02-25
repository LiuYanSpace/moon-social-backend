package com.tothemoon.app.dto;

import com.bird.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginSuccessDTO {
    private String jwt;
    private String nickName;
    private String email;
    private Role role;
    private String userName;
}
