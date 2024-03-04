package com.tothemoon.common.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "discussion_list")
public class DiscussionCollectionItem {
    @EmbeddedId
    private DiscussionCollectionId id;
    @ManyToOne
    @MapsId("discussion_id")
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;
    @ManyToOne
    @MapsId("list_id")
    @JoinColumn(name = "list_id", nullable = false)
    private DiscussionCollection discussionList;
    private Long order;
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;
}
