package com.tothemoon.app.dto;

import com.tothemoon.common.entity.PostLikeId;
import com.tothemoon.common.entity.User;
import lombok.Data;

import java.util.Date;
@Data
public class PostLikeDTO {
    private Long postId;
    private String content;
}

