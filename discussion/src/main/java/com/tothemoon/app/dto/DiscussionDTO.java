package com.tothemoon.app.dto;

import com.bird.dto.BasicUserInfoDTO;
import lombok.*;

import java.util.Date;

@Data
public class DiscussionDTO {
    private Long id;
    private String title;
    private Integer commentCount;
    private Integer participantCount;
    private Integer postNumberIndex;
    private Long userId;
    private Long firstPostId;
    private Date lastPostedAt;
    private Long lastPostedUserId;
    private Long lastPostId;
    private Integer lastPostNumber;
    private String slug;
    private Boolean isPrivate;
    private Boolean isApproved;
    private Boolean isSticky;
    private Boolean isLocked;
    private Boolean isPopular;
    private Integer viewCount;
    private Integer votes;
    private Date createdAt;
}
