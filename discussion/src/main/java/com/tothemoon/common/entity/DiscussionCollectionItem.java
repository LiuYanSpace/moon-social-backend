package com.tothemoon.common.entity;

import jakarta.persistence.*;
import lombok.*;
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
    @MapsId("discussionId")
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;
    @ManyToOne
    @MapsId("listId")
    @JoinColumn(name = "list_id", nullable = false)
    private DiscussionCollection discussionList;
    private Long order;
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;
}
