package com.tothemoon.app.dto;

import com.tothemoon.common.entity.Discussion;
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
    private List<TagDTO> children;
    private Boolean isRestricted = false;
    private Boolean isHidden = false;
    private Integer discussionCount = 0;
    private Date lastPostedAt;
    private Discussion lastPostedDiscussion;
    private User lastPostedUser;
    private String icon;
    private String template;
    private String password;
    private Integer postCount = 0;
    private Integer excerptLength;
    private Boolean richExcerpts;
}
