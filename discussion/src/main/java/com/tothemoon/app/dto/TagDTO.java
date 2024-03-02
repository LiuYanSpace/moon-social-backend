package com.tothemoon.app.dto;

import com.tothemoon.common.entity.Discussion;
import com.tothemoon.common.entity.Tag;
import com.tothemoon.common.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class TagDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String color;
    private Integer position;
    private TagDTO parentTag;
    private Integer discussionCount = 0;
    private Date lastPostedAt;
    private BasicDiscussionDTO lastPostedDiscussion;
    private BasicUserInfoDTO lastPostedUser;
    private String icon;
    private Integer excerptLength;
    private Boolean richExcerpts;
}
