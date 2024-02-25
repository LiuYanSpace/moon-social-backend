package com.tothemoon.common.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "background_path")
    private String backgroundPath;

    @Column(name = "background_mode")
    private String backgroundMode;

    @Column(name = "position")
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Tag parentTag;

    @Column(name = "default_sort")
    private String defaultSort;

    @Column(name = "is_restricted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isRestricted = false;

    @Column(name = "is_hidden", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isHidden = false;

    @Column(name = "discussion_count", nullable = false)
    private Integer discussionCount = 0;

    @Column(name = "last_posted_at")
    private Date lastPostedAt;

    @ManyToOne
    @JoinColumn(name = "last_posted_discussion_id")
    private Discussion lastPostedDiscussion;

    @ManyToOne
    @JoinColumn(name = "last_posted_user_id")
    private User lastPostedUser;

    @Column(name = "icon")
    private String icon;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    @Column(name = "template", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String template;

    @Column(name = "password")
    private String password;

    @Column(name = "post_count", nullable = false)
    private Integer postCount = 0;

    @Column(name = "excerpt_length")
    private Integer excerptLength;

    @Column(name = "rich_excerpts")
    private Boolean richExcerpts;

}
