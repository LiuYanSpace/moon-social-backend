package com.tothemoon.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class BasicDiscussionDTO {
    private DiscussionDTO discussion;
    private List<BasicTagDTO> tags;

}
