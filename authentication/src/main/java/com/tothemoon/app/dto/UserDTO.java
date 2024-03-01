package com.tothemoon.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private Boolean isEmailConfirmed = false;
    private String avatarUrl;
    private byte[] preferences;
    private Date joinedAt;
    private Date lastSeenAt;
    @JsonIgnore
    private Date markedAllAsReadAt;
    @JsonIgnore
    private Date readNotificationsAt;
    private Integer discussionCount = 0;
    private Integer commentCount = 0;
    private Date readFlagsAt;
    private Date suspendedUntil;
    private String suspendReason;
    private String suspendMessage;
    @JsonIgnore
    private String inviteCode;
    @JsonIgnore
    private Integer totalCheckinCount = 0;
    @JsonIgnore
    private Integer totalContinuousCheckinCount = 0;
    @JsonIgnore
    private Date lastCheckinTime;
    private String pronouns;
    private Boolean blocksByobuPd = false;
    private Integer votes;
    private String rank;
    @JsonIgnore
    private Date lastVoteTime;
    private String newAchievements;
    private String countryCode;
    private Double money = 0.0;
    private String socialButtons;
    private String bio;
    private String signature;
}
