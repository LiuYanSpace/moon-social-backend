package com.tothemoon.common.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "group_user")
public class GroupUser {

    @EmbeddedId
    private GroupUserId id;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("user_id")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("group_id")
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}
