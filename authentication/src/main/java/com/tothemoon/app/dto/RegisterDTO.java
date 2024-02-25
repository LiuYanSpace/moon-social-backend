package com.tothemoon.app.dto;

import lombok.Data;


@Data
public class RegisterDTO {
    private Long id;
    private String doorKey;
    private String email;
    private String nickName;
    private String password;
}
