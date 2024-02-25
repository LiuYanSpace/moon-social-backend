package com.tothemoon.common.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 100, unique = true)
    private String username;

    @Column(name = "nickname", length = 255)
    private String nickname;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "is_email_confirmed", nullable = false)
    private Boolean isEmailConfirmed = false;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "avatar_url", length = 100)
    private String avatarUrl;

    @Lob
    @Column(name = "preferences")
    private byte[] preferences;

    @Column(name = "joined_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedAt;

    @Column(name = "last_seen_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSeenAt;

    @Column(name = "marked_all_as_read_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date markedAllAsReadAt;

    @Column(name = "read_notifications_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date readNotificationsAt;

    @Column(name = "discussion_count", nullable = false)
    private Integer discussionCount = 0;

    @Column(name = "comment_count", nullable = false)
    private Integer commentCount = 0;

    @Column(name = "read_flags_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date readFlagsAt;

    @Column(name = "suspended_until")
    @Temporal(TemporalType.TIMESTAMP)
    private Date suspendedUntil;

    @Column(name = "suspend_reason", columnDefinition = "TEXT")
    private String suspendReason;

    @Column(name = "suspend_message", columnDefinition = "TEXT")
    private String suspendMessage;

    @Column(name = "invite_code", length = 128)
    private String inviteCode;

    @Column(name = "total_checkin_count", nullable = false)
    private Integer totalCheckinCount = 0;

    @Column(name = "total_continuous_checkin_count", nullable = false)
    private Integer totalContinuousCheckinCount = 0;

    @Column(name = "last_checkin_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCheckinTime;

    @Column(name = "pronouns", length = 20)
    private String pronouns;

    @Column(name = "blocks_byobu_pd", nullable = false)
    private Boolean blocksByobuPd = false;

    @Column(name = "votes", nullable = false)
    private Integer votes;

    @Column(name = "rank", length = 255)
    private String rank;

    @Column(name = "last_vote_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastVoteTime;

    @Column(name = "new_achievements", nullable = false, columnDefinition = "TEXT")
    private String newAchievements;

//    @Column(name = "countryCode", nullable = false, columnDefinition = "TEXT")
//    private String countryCode;

    @Column(name = "carving_contest_entry_count", nullable = false)
    private Integer carvingContestEntryCount = 0;

    @Column(name = "money", nullable = false)
    private Double money = 0.0;

    @Lob
    @Column(name = "social_buttons")
    private String socialButtons;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "signature", nullable = false, columnDefinition = "TEXT")
    private String signature;

    @Column(name = "has_pwned_password", nullable = false)
    private Boolean hasPwnedPassword = false;

}

