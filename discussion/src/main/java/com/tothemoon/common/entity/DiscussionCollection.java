package com.tothemoon.common.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "discussion_lists")
public class DiscussionCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String visibility = "private";

    private String ordering;

    @Column(name = "discussion_count", nullable = false)
    private Long discussionCount = 0L;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

}

