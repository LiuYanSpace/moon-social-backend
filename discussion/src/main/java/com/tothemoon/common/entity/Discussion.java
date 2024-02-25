package com.tothemoon.common.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "discussions")
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "comment_count", nullable = false)
    private Integer commentCount = 1;

    @Column(name = "participant_count", nullable = false)
    private Integer participantCount = 0;

    @Column(name = "post_number_index", nullable = false)
    private Integer postNumberIndex = 0;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER ,cascade = ALL)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_post_id")
    private Post firstPost;

    @Column(name = "last_posted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPostedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_posted_user_id")
    private User lastPostedUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_post_id")
    private Post lastPost;

    @Column(name = "last_post_number")
    private Integer lastPostNumber;

    @Column(name = "hidden_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiddenAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hidden_user_id")
    private User hiddenUser;

    @Column(name = "slug", nullable = false, length = 255)
    private String slug;

    @Column(name = "is_private", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean isPrivate ;

    @Column(name = "is_approved", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean isApproved;

    @Column(name = "is_sticky", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean isSticky ;

    @Column(name = "is_locked", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean isLocked ;

    @Column(name = "is_popular", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean isPopular;


    @Column(name = "view_count", nullable = false)
    private Integer viewCount = 0;

    @Column(name = "votes", nullable = false)
    private Integer votes;

    @Column(name = "hotness", nullable = false)
    private Double hotness;

    @Column(name = "frontpage", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean frontpage;

    @Column(name = "frontdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frontdate;

    @Column(name = "is_stickiest", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean isStickiest ;

    @Column(name = "is_tag_sticky", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean isTagSticky ;

}
