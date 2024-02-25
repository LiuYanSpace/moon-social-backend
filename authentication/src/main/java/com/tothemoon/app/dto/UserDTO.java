package com.tothemoon.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String nickname;

    private String email;

    private Boolean isEmailConfirmed = false;

    private String password;

    private String avatarUrl;

    private byte[] preferences;

    private Date joinedAt;

    private Date lastSeenAt;

    private Date markedAllAsReadAt;

    private Date readNotificationsAt;

    private Integer discussionCount = 0;

    private Integer commentCount = 0;

    private Date readFlagsAt;

    private Date suspendedUntil;

    private String suspendReason;

    private String suspendMessage;

    private String inviteCode;

    private Integer totalCheckinCount = 0;

    private Integer totalContinuousCheckinCount = 0;

    private Date lastCheckinTime;

    private String pronouns;

    private Boolean blocksByobuPd = false;

    private Integer votes;

    private String rank;

    private Date lastVoteTime;

    private String newAchievements;

    private String countryCode;

    private Integer carvingContestEntryCount = 0;

    private Double money = 0.0;
    private String socialButtons;

    private String bio;
    private String signature;

    private Boolean hasPwnedPassword = false;
}
