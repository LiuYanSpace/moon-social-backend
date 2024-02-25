package com.tothemoon.app.dto;
import com.tothemoon.common.entity.Discussion;
import com.tothemoon.common.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
public class PostDTO {
    private Long id;
    private Integer number;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type", length = 100)
    private String type;

    @Column(name = "content", columnDefinition = "MEDIUMTEXT")
    private String content;

    @Column(name = "edited_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edited_user_id")
    private User editedUser;

    @Column(name = "hidden_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiddenAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hidden_user_id")
    private User hiddenUser;

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
