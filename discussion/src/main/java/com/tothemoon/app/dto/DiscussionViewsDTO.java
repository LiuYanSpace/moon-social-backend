package com.tothemoon.app.dto;

import com.tothemoon.common.entity.Discussion;
import com.tothemoon.common.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class DiscussionViewsDTO {
    private String nickname;
    private String title;
    private Date visitedAt;

    public void getNickname(String nickname) {
        this.nickname = nickname;
    }
}
