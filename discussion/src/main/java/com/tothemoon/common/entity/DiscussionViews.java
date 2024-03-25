package com.tothemoon.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "discussion_views",
        indexes = {
                @Index(name = "discussion_views_discussion_id_foreign", columnList = "discussion_id"),
                @Index(name = "discussion_views_user_id_foreign", columnList = "user_id")}
)

public class DiscussionViews {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discussion_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Discussion discussion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @Column(name = "visited_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitedAt;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "discussion_id", nullable = false, updatable = false)
        private Long discussionId;

        @Column(name = "user_id", nullable = false, updatable = false)
        private Long userId;

        public Id() {
        }

        public Id(Long discussionId, Long userId) {
            this.discussionId = discussionId;
            this.userId = userId;
        }
    }

}