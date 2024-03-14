package com.tothemoon.common.entity;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;

    @Column(name = "number")
    private Integer number;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "type", length = 100)
    private String type;

    @Column(name = "content", columnDefinition = "MEDIUMTEXT")
    private String content;

    @Column(name = "edited_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedAt;

    @Column(name = "edited_user_id")
    private Long editedUserId;
    @Column(name = "hidden_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiddenAt;

    @Column(name = "edited_user_id")
    private Long hiddenUserId;
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate = false;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved = true;

    @Column(name = "is_spam", nullable = false)
    private Boolean isSpam = false;

    // Getters and setters omitted for brevity
}
