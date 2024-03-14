package com.tothemoon.app.dto;
import lombok.*;

import java.util.Date;
import com.bird.dto.BasicUserInfoDTO;

@Data
public class PostDTO {
    private Long id;
    private Integer number;
    private Date createdAt;
    private BasicUserInfoDTO user;
    private String type;
    private String content;
    private Date editedAt;
    private BasicUserInfoDTO editedUser;
    private Date hiddenAt;
    private BasicUserInfoDTO hiddenUser;
    private String ipAddress;
    private Boolean isPrivate = false;
    private Boolean isApproved = true;
    private Boolean isSpam = false;
}
