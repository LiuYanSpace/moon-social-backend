package com.tothemoon.app.dto;

import lombok.*;
import java.util.Date;
import java.util.List;
import com.bird.dto.BasicUserInfoDTO;

@Data
public class TagDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String color;
    private Integer position;
    private Long parentTagId;
    private Integer discussionCount = 0;
    private Date lastPostedAt;
    private Long lastPostedDiscussionId;
    private Long lastPostedUserId;
    private String icon;
    private Integer excerptLength;
    private Boolean richExcerpts;
    private List<TagDTO> children;
}
