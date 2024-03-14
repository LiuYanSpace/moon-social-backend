package com.tothemoon.common.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

/**
 * @ClassName:PostLike
 * @Auther: yyj
 * @Description:
 * @Date: 26/02/2024 19:56
 * @Version: v1.0
 */


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "post_likes")
public class PostLike {

    @EmbeddedId
    private PostLikeId id;
    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}
