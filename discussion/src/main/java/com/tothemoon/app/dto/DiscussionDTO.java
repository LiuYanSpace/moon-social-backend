package com.tothemoon.app.dto;

import lombok.*;

import java.util.Date;

@Data
public class DiscussionDTO {
    private Long id;
    private String title;
    private Integer commentCount = 1;
    private Integer participantCount = 0;
    private Integer postNumberIndex = 0;
    private Date createdAt;
    private BasicUserInfoDTO user;
    private BasicPostDTO firstPost;
    private Date lastPostedAt;
    private BasicUserInfoDTO lastPostedUser;
    private BasicPostDTO lastPost;
    private Integer lastPostNumber;
    private String slug;
    private Boolean isPrivate ;
    private Boolean isApproved;
    private Boolean isSticky;
    private Boolean isLocked;
    private Boolean isPopular;
    private Integer viewCount;
    private Integer votes;
}
