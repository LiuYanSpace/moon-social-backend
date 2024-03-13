package com.tothemoon.common.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class PostLikeId implements Serializable {
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;
}
