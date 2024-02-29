package com.tothemoon.app.dto;

import lombok.Data;


@Data
public class RegisterDTO {
    private String nickName;
    private String username;
    private String fofDoorKey;
    private String recaptchaResponse;
    private String email;
    private String password;
}
