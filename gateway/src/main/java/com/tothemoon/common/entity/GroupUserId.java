package com.tothemoon.common.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GroupUserId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "group_id")
    private Long groupId;
}
