package com.tothemoon.app.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
public class DiscussionCollectionDTO {
    private Long id;
    private String name;
    private String ordering;
    private Long discussionCount = 0L;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

