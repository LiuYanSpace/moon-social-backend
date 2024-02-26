package com.tothemoon.app.dto;
import com.tothemoon.common.entity.User;
import lombok.*;

import java.util.Date;

@Data
public class PostDTO {
    private Long id;
    private Integer number;
    private Date createdAt;
    private User user;
    private String type;
    private String content;
    private Date editedAt;
    private User editedUser;
    private Date hiddenAt;
    private User hiddenUser;
    private String ipAddress;
    private Boolean isPrivate = false;
    private Boolean isApproved = true;
    private Boolean isSpam = false;
}
