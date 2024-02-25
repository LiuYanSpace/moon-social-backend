package com.tothemoon.common.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "discussion_tag",
        indexes = {
                @Index(name = "discussion_tag_tag_id_foreign", columnList = "tag_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"discussion_id", "tag_id"})
        }
)
public class DiscussionTag {

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "discussion_id", nullable = false, updatable = false)
        private Long discussionId;

        @Column(name = "tag_id", nullable = false, updatable = false)
        private Long tagId;
        public Id() {}
        public Id(Long discussionId, Long tagId) {
            this.discussionId = discussionId;
            this.tagId = tagId;
        }

    }

    @EmbeddedId
    private Id id = new Id();

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discussion_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Discussion discussion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Tag tag;

}
