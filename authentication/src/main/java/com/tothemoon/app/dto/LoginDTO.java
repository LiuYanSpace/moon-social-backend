package com.tothemoon.app.dto;
import lombok.Data;

@Data
public class LoginDTO {
    private String identification;
    private String gRecaptchaResponse;
    private String password;
}
