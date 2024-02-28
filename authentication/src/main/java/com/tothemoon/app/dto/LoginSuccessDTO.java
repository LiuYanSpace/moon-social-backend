package com.tothemoon.app.dto;

import com.bird.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class LoginSuccessDTO {
    private Long id;
    private String jwt;
    private String nickName;
    private String email;
    private List<String> role;
    private String userName;

}
