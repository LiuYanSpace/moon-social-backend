package com.tothemoon.common.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "discussion_list")
public class DiscussionListMembership {
    @Id
    @ManyToOne
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;
    @Id
    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private DiscussionList discussionList;
    private Long order;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
