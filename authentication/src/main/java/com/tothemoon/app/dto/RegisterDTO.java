package com.tothemoon.app.dto;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDTO {
    @NotBlank(message = "NickName cannot be blank")
    private String nickName;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Friend-of-Friend DoorKey cannot be blank")
    private String fofDoorKey;

    @NotBlank(message = "RecaptchaResponse cannot be blank")
    private String recaptchaResponse;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}