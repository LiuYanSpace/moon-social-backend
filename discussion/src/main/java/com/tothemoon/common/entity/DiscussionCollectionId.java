package com.tothemoon.common.entity;
import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class DiscussionCollectionId implements Serializable {
    @Column(name = "discussion_id")
    private Long discussionId;

    @Column(name = "list_id")
    private Long listId;
}
