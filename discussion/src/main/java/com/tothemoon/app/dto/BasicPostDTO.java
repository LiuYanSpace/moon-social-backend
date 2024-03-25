package com.tothemoon.app.dto;

import lombok.Data;
import java.util.Date;

@Data
public class BasicPostDTO {
    private Long id;
    private Integer number;
    private Date createdAt;
    private Long userId;
    private String type;
    private String content;
    private Date editedAt;
    private Long editedUserId;
}
