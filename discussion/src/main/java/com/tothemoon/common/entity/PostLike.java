package com.tothemoon.common.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}
