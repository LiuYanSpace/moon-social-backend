package com.tothemoon.app.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String password;
    private String resetKey;
}
