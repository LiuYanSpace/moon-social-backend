package com.tothemoon.app.dto;

import lombok.Data;

@Data
public class BasicUserInfoDTO {
    private Long id;
    private String username;
    private String nickname;
    private String avatarUrl;
}
