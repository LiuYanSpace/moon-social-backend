package com.tothemoon.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "access_tokens")
public class AccessToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String token;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_activity_at")
    private Date lastActivityAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(nullable = false, length = 100)
    private String type;

    @Column(length = 150)
    private String title;

    @Column(name = "last_ip_address", length = 45)
    private String lastIpAddress;

    @Column(name = "last_user_agent", length = 255)
    private String lastUserAgent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User user;
}
